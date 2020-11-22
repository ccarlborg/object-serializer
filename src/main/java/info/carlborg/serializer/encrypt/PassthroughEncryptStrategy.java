package info.carlborg.serializer.encrypt;

import java.io.IOException;

/**
 * Passthrough implementation that does nothing
 *
 * Refer to the EncryptStrategy interface for more details
 */
public class PassthroughEncryptStrategy implements EncryptStrategy {
  @Override
  public byte[] encrypt(final byte[] bytes) throws IOException {
    return bytes;
  }

  @Override
  public byte[] decrypt(final byte[] bytes) throws IOException {
    return bytes;
  }
}
