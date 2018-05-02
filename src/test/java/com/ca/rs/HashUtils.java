package com.ca.rs;

import com.google.common.hash.Hashing;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.UUID.randomUUID;

/**
 * Static utility functions pertaining to the creation of secure message authentication hashes.
 */
public class HashUtils {

  /**
   * Generate a HMAC SHA-1 encoded identifier for the provided data.
   *
   * @return the hexadecimal (BASE-16) encoded hash string
   */
  public static String generateHashString() {
    return generateHashString(randomUUID().toString());
  }

  /**
   * Generate a HMAC SHA-1 encoded identifier for the provided data.
   *
   * @param data the data to hash
   * @return the hexadecimal (BASE-16) encoded hash string
   */
  public static String generateHashString(String data) {
    checkNotNull(data, "data must not be null");
    return Hashing
        .hmacSha1("asdkfkdf".getBytes(UTF_8))
        .hashBytes(data.getBytes(UTF_8))
        .toString();
  }

  private HashUtils() {
  }
}
