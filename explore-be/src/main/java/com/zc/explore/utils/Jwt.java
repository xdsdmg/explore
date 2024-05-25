/**
 * Reference:
 * https://github.com/jwtk/jjwt?tab=readme-ov-file#jwt-encrypted-with-ecdh-es
 */

package com.zc.explore.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class Jwt {
  @Value("${app.jwt.expiration}")
  private int EXPIRATION;
  @Value("${app.jwt.private_key_path}")
  private String PRIVATE_KEY_PATH;
  @Value("${app.jwt.public_key_path}")
  private String PUBLIC_KEY_PATH;
  @Value("${app.jwt.alg}")
  private String ALG;
  @Value("${app.jwt.key}")
  private String KEY;

  /**
   * init initializes the key files, this method will be called when the project
   * starts. Can not use the private member variable (will be null), because this
   * method is static.
   * 
   * @param priKeyPath
   * @param pubKeyPath
   */
  public static void init(String priKeyPath, String pubKeyPath) throws Exception {
    /*
     * Check whether the files of private key and public key exist.
     * If the files exist, return directly.
     */
    File privateKeyFile = new File(priKeyPath);
    File publicKeyFile = new File(pubKeyPath);
    if (privateKeyFile.isFile() && publicKeyFile.isFile()) {
      return;
    }

    /*
     * Generate private key and public key, and write them to files.
     */
    privateKeyFile.createNewFile();
    publicKeyFile.createNewFile();
    try (FileOutputStream priKeyFileStream = new FileOutputStream(privateKeyFile);
        FileOutputStream pubKeyFileStream = new FileOutputStream(publicKeyFile);) {
      // Create a KeyPair suitable for the desired EC key algorithm:
      KeyPair pair = Jwts.SIG.ES512.keyPair().build();
      // Write the keys to files.
      priKeyFileStream.write(pair.getPrivate().getEncoded());
      pubKeyFileStream.write(pair.getPublic().getEncoded());
    } catch (Exception e) {
      // If error occurs, delete the private key and public key file.
      privateKeyFile.delete();
      publicKeyFile.delete();
      throw e;
    }
  }

  public String generate(String email, int expiration) throws Exception {
    /*
     * Get public key from file.
     */
    KeyFactory kf = KeyFactory.getInstance(ALG);
    byte[] rawPubKey = Files.readAllBytes(Paths.get(PUBLIC_KEY_PATH));
    PublicKey pubKey = kf.generatePublic(new X509EncodedKeySpec(rawPubKey));

    if (expiration <= 0) {
      expiration = EXPIRATION;
    }

    /*
     * Generate jwe.
     */
    String jwe = Jwts.builder()
        .claim(KEY, email)
        .expiration(new Date(System.currentTimeMillis() + expiration))
        .encryptWith(pubKey, Jwts.KEY.ECDH_ES_A256KW, Jwts.ENC.A256GCM)
        .compact();

    return jwe;
  }

  public String parse(String jwe) throws Exception {
    /*
     * Get private key from file.
     */
    KeyFactory kf = KeyFactory.getInstance(ALG);
    byte[] rawPriKey = Files.readAllBytes(Paths.get(PRIVATE_KEY_PATH));
    PrivateKey priKey = kf.generatePrivate(new PKCS8EncodedKeySpec(rawPriKey));

    /*
     * Parse jwe.
     */
    Claims claims = Jwts.parser()
        .decryptWith(priKey)
        .build()
        .parseEncryptedClaims(jwe)
        .getPayload();

    return (String) claims.get(KEY);
  }
}
