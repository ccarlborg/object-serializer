package info.carlborg.serializer.encode;

import java.io.IOException;
import java.util.Base64;

/**
 * Encode a byte array into a base64 encoded string
 * 
 * Refer to the EncodeStrategy interface for more details
 */
public class Base64EncodeStrategy implements EncodeStrategy {
  @Override
  public byte[] decode(final String str) throws IOException {
    try {
      return Base64.getDecoder().decode(str);
    } catch (IllegalArgumentException e) {
      throw new IOException(e);
    }
  }

  @Override
  public String encode(final byte[] bytes) throws IOException {
    return Base64.getEncoder().encodeToString(bytes);
  }
}
