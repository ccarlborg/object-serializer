package info.carlborg.serializer.encode;

import java.io.IOException;

/**
 * Encode a byte array into a string representation
 * 
 * All implementations of this interface must be thread safe
 * 
 * The only exception that is allowed to be thrown by the encode and decode methods is IOException
 * 
 * The incoming byte array to the encode method can be assumed to be non-null
 */
public interface EncodeStrategy {
  /**
   * Encode a byte array into a string
   * 
   * @param bytes The byte array to encode
   * 
   * @return The encoded string
   * 
   * @throws IOException If anything goes wrong while encoding
   */
  public String encode(final byte[] bytes) throws IOException;

  /**
   * Decode a string back into the original byte array
   * 
   * @param str A string that was previously encoded
   * 
   * @return The original byte array
   * 
   * @throws IOException If anything goes wrong while decoding
   */
  public byte[] decode(final String str) throws IOException;
}
