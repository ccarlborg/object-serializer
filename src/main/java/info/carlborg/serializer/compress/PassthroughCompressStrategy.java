package info.carlborg.serializer.compress;

import java.io.IOException;

/**
 * Passthrough implementation that does nothing
 *
 * Refer to the CompressStrategy interface for more details
 */
public class PassthroughCompressStrategy implements CompressStrategy {
  @Override
  public byte[] compress(final byte[] bytes) throws IOException {
    return bytes;
  }

  @Override
  public byte[] decompress(final byte[] bytes) throws IOException {
    return bytes;
  }
}
