package com.zc.explore.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;

import org.springframework.stereotype.Component;

@Component
public class Hash {
  private MessageDigest md; // Used to calculate SHA-256 hash of password

  public Hash() throws Exception {
    this.md = MessageDigest.getInstance("SHA-256");
  }

  public String sha256(String s) {
    byte[] b = s.getBytes(StandardCharsets.UTF_8);
    String result = HexFormat.of().formatHex(md.digest(b)); // Get the hash of pwd
    return result;
  }
}
