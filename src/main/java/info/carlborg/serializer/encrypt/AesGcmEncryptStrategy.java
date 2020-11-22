package info.carlborg.serializer.encrypt;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Encrypt using the AES-GCM cipher
 * 
 * Tag length 128, IV size 64 bits. Provide a 16, 24 or 32 byte long encryption key for 128, 192 or
 * 256 bit block size respectively
 */
public class AesGcmEncryptStrategy implements EncryptStrategy {
  /**
   * Construct a new instance
   * 
   * @param encryptionKey 16, 24 or 32 bytes long encryption key
   * 
   * @throws IllegalArgumentException The key is null or empty
   * @throws InvalidKeyException The key is not of length 16, 24 or 32 bytes
   */
  public AesGcmEncryptStrategy(final byte[] encryptionKey) throws InvalidKeyException {
    this.key = new SecretKeySpec(encryptionKey, KEY_CIPHER);
    if (encryptionKey.length != 16 && encryptionKey.length != 24 && encryptionKey.length != 32) {
      throw new InvalidKeyException("encryption key must be 16, 24 or 32 bytes long");
    }
  }

  @Override
  public byte[] encrypt(final byte[] bytes) throws IOException {
    try {
      final byte[] iv = getRandomBytes(IV_LENGTH_BITS / 8);
      final Cipher cipher = Cipher.getInstance(ENCRYPT_CIPHER);
      cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(TAG_LENGTH_BITS, iv));

      final byte[] cipherText = cipher.doFinal(bytes);
      final byte[] cipherTextWithIv =
          ByteBuffer.allocate(iv.length + cipherText.length).put(iv).put(cipherText).array();

      return cipherTextWithIv;
    } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidAlgorithmParameterException
        | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException
        | BufferUnderflowException e) {
      throw new IOException(e);
    }
  }

  @Override
  public byte[] decrypt(final byte[] bytes) throws IOException {
    try {
      final ByteBuffer bb = ByteBuffer.wrap(bytes);

      final byte[] iv = new byte[IV_LENGTH_BITS / 8];
      bb.get(iv);

      final byte[] cipherText = new byte[bb.remaining()];
      bb.get(cipherText);

      final Cipher cipher = Cipher.getInstance(ENCRYPT_CIPHER);
      cipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(TAG_LENGTH_BITS, iv));
      final byte[] plainText = cipher.doFinal(cipherText);

      return plainText;
    } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidAlgorithmParameterException
        | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException
        | BufferUnderflowException e) {
      throw new IOException(e);
    }
  }

  /**
   * Generate a byte array with numBytes random bytes
   * 
   * @param numBytes Non-negative number of random bytes to generate
   * 
   * @throws IllegalArgumentException If numBytes is negative
   * 
   * @return An array of random bytes
   */
  public static byte[] getRandomBytes(final int numBytes) {
    if (numBytes < 0) {
      throw new IllegalArgumentException("the number of bytes must be non-negative");
    }
    final byte[] bytes = new byte[numBytes];
    secureRandom.nextBytes(bytes);
    return bytes;
  }

  /**
   * Initializing SecureRandom is expensive, so using a static instance is advisable
   */
  private static final SecureRandom secureRandom;
  static {
    secureRandom = new SecureRandom();
  }

  private static final String KEY_CIPHER = "AES";
  private static final String ENCRYPT_CIPHER = "AES/GCM/NoPadding";
  private static final int IV_LENGTH_BITS = 64;
  private static final int TAG_LENGTH_BITS = 128;

  private final SecretKey key;
}
