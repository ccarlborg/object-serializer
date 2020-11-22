package info.carlborg.serializer.encrypt;

import java.io.IOException;

/**
 * Encrypt a byte array into an encrypted byte array
 *
 * All implementations of this interface must be thread safe
 * 
 * The only exception that is allowed to be thrown by the encrypt and decrypt methods is IOException
 * 
 * The incoming byte array can be assumed to be non-null
 */
public interface EncryptStrategy {
  /**
   * Encrypt a byte array
   * 
   * @param bytes The byte array to encrypt
   * 
   * @return The encrypted byte array
   * 
   * @throws IOException If anything goes wrong while encrypting
   */
  public byte[] encrypt(final byte[] bytes) throws IOException;

  /**
   * Decrypt an encrypted byte array
   * 
   * @param bytes An encrypted byte array
   * 
   * @return The decrypted byte array
   * 
   * @throws IOException If anything goes wrong while decrypting
   */
  public byte[] decrypt(final byte[] bytes) throws IOException;
}
