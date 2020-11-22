package info.carlborg.serializer.compress;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Compress using the gzip compression algorithm
 *
 * Refer to the CompressStrategy interface for more details
 */
public class GzipCompressStrategy implements CompressStrategy {
  @Override
  public byte[] compress(final byte[] bytes) throws IOException {
    final ByteArrayOutputStream bos = new ByteArrayOutputStream(bytes.length);
    final GZIPOutputStream gos = new GZIPOutputStream(bos);
    gos.write(bytes);
    gos.close();
    final byte[] compressed = bos.toByteArray();
    bos.close();
    return compressed;
  }

  @Override
  public byte[] decompress(final byte[] compressed) throws IOException {
    ByteArrayInputStream bis = new ByteArrayInputStream(compressed);
    GZIPInputStream gis = new GZIPInputStream(bis);
    byte[] decompressed = toByteArray(gis);
    bis.close();
    gis.close();
    return decompressed;
  }

  private byte[] toByteArray(GZIPInputStream gis) throws IOException {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    int bytesRead;
    byte[] dataChunk = new byte[1024];
    while ((bytesRead = gis.read(dataChunk, 0, dataChunk.length)) != -1) {
      bos.write(dataChunk, 0, bytesRead);
    }
    return bos.toByteArray();
  }
}
