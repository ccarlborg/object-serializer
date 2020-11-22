package info.carlborg.serializer.compress;

import java.io.IOException;

/**
 * Compress a byte array.
 * 
 * All implementations of this interface must be thread safe
 * 
 * The only exception that is allowed to be thrown by the compress and decompress methods is
 * IOException
 * 
 * The incoming byte array can be assumed to be non-null
 */
public interface CompressStrategy {
  /**
   * Compress a byte array
   * 
   * @param bytes The byte array to compress
   * 
   * @return The compressed byte array
   * 
   * @throws IOException If anything goes wrong while compressing
   */
  byte[] compress(final byte[] bytes) throws IOException;

  /**
   * Decompress a compressed byte array
   * 
   * @param bytes A compressed byte array
   * 
   * @return The decompressed byte array
   * 
   * @throws IOException If anything goes wrong while decompressing
   */
  byte[] decompress(final byte[] bytes) throws IOException;
}
