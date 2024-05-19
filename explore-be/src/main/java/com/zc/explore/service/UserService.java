package com.zc.explore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.zc.explore.dao.UserMapper;
import com.zc.explore.model.exception.AccountNotFoundException;
import com.zc.explore.model.exception.EmailDupRegException;
import com.zc.explore.model.user.LoginRequest;
import com.zc.explore.model.user.RegisterRequest;
import com.zc.explore.model.user.User;
import com.zc.explore.utils.Email;
import com.zc.explore.utils.Hash;
import com.zc.explore.utils.Jwt;

@Service
public class UserService {
  @Autowired
  private UserMapper userDao;
  @Autowired
  private Jwt jwtUtil;
  @Autowired
  private Email emailUtil;
  @Autowired
  private Hash hashUtil;

  private final TransactionTemplate transactionTemplate;

  @Value("${app.host}")
  private String HOST;
  @Value("${app.protocol}")
  private String PROTOCOL;
  @Value("${app.email.content_type}")
  private String CONTENT_TYPE;
  @Value("${app.email.subject}")
  private String SUBJECT;
  @Value("${app.email.activate_api_path}")
  private String ACTIVATE_API_PATH;

  public UserService(PlatformTransactionManager transactionManager) throws Exception {
    this.transactionTemplate = new TransactionTemplate(transactionManager);
  }

  // TODO: regex check
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
          String pwd = hashUtil.sha256(req.getPwd()); // Get the hash of pwd
          if (userDao.createUser(new User(req.getName(), pwd, req.getEmail())) == 0) {
            return new Exception("create new user failed");
          }

          String content = String.format(
              "<p>Welcome to Explore!</p><p>Please click the link below to activate your account.</p><a>%s</a>",
              String.format("%s://%s%s/%s", PROTOCOL, HOST, ACTIVATE_API_PATH, jwtUtil.generate(req.getEmail(), 0)));
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
    token = jwtUtil.generate("Hello", 0);
    System.out.printf("token: %s, username: %s\n", token, jwtUtil.parse(token));
  }

  public String login(LoginRequest req) throws Exception {
    String pwd = hashUtil.sha256(req.getPwd());

    if (userDao.checkAccountExist(req.getEmail(), pwd) <= 0) {
      throw new AccountNotFoundException();
    }

    String jweToken = jwtUtil.generate(req.getEmail(), 7 * 24 * 60 * 60);

    return jweToken;
  }
}
