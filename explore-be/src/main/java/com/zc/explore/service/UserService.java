package com.zc.explore.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.zc.explore.dao.UserMapper;
import com.zc.explore.model.exception.EmailDupRegException;
import com.zc.explore.model.user.RegisterRequest;
import com.zc.explore.model.user.User;
import com.zc.explore.utils.Email;
import com.zc.explore.utils.Jwt;

@Service
public class UserService {
  @Autowired
  private UserMapper userDao;
  @Autowired
  private Jwt jwtUtil;
  @Autowired
  private Email emailUtil;

  private MessageDigest md; // Used to calculate SHA-256 hash of password
  private final TransactionTemplate transactionTemplate;

  @Value("${app.host}")
  private String HOST;
  @Value("${app.protocol}")
  private String PROTOCOL;
  @Value("${app.email.content_type}")
  private String CONTENT_TYPE;
  @Value("${app.email.subject}")
  private String SUBJECT;
  @Value("${app.email.activate_page_path}")
  private String ACTIVATE_PAGE_PATH;

  public UserService(PlatformTransactionManager transactionManager) throws Exception {
    this.transactionTemplate = new TransactionTemplate(transactionManager);
    this.md = MessageDigest.getInstance("SHA-256");
  }

  public void register(RegisterRequest req) throws Exception {
    Object e = transactionTemplate.execute(new TransactionCallback<Object>() {
      public Object doInTransaction(@NonNull TransactionStatus status) {
        try {
          /*
           * Check whether the email is registered
           */
          if (userDao.getUserTotalOfEmailWithWriteLock(req.getEmail()) > 0) {
            return new EmailDupRegException();
          }

          /*
           * Create new user
           */
          byte[] rawPwd = req.getPwd().getBytes(StandardCharsets.UTF_8);
          String pwd = HexFormat.of().formatHex(md.digest(rawPwd)); // Get the hash of pwd
          if (userDao.createUser(new User(req.getName(), pwd, req.getEmail())) == 0) {
            return new Exception("create new user failed");
          }

          String content = String.format(
              "<p>Welcome to Explore!</p><p>Please click the link below to activate your account.</p><a>%s</a>",
              String.format("%s://%s%s/%s", PROTOCOL, HOST, ACTIVATE_PAGE_PATH, jwtUtil.generate(req.getEmail())));
          emailUtil.send(req.getEmail(), SUBJECT, content, CONTENT_TYPE);
        } catch (Exception e) {
          status.setRollbackOnly();
          return e;
        }

        return null;
      }
    });

    if (e != null) {
      throw (Exception) e;
    }
  }

  public void activate(String token) throws Exception {
    token = jwtUtil.generate("Hello");
    System.out.printf("token: %s, username: %s\n", token, jwtUtil.parse(token));
  }
}
