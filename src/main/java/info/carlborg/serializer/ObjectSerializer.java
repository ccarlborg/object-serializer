package info.carlborg.serializer;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import info.carlborg.serializer.compress.CompressStrategy;
import info.carlborg.serializer.compress.PassthroughCompressStrategy;
import info.carlborg.serializer.encode.Base64EncodeStrategy;
import info.carlborg.serializer.encode.EncodeStrategy;
import info.carlborg.serializer.encrypt.EncryptStrategy;
import info.carlborg.serializer.encrypt.PassthroughEncryptStrategy;
import info.carlborg.serializer.serialize.JavaSerializeStrategy;
import info.carlborg.serializer.serialize.SerializeStrategy;

/**
 * A generic Java object serializer that serializes objects into strings using the selected
 * strategies for serialization, compression, encryption and encoding
 * 
 * The order of execution is: serialization, compression, encryption, encoding
 */
public class ObjectSerializer {
  /**
   * Initialize a new Java object serializer
   *
   * @param serializer Java object serialization method to use
   * @param compressor Compression method to use
   * @param encryptor Encryption method to use
   * @param encoder String encoding method to use
   * 
   * @throws NullPointerException If any argument is null
   */
  public ObjectSerializer(final SerializeStrategy serializer, final CompressStrategy compressor,
      final EncryptStrategy encryptor, final EncodeStrategy encoder) {
    this.serializer = Objects.requireNonNull(serializer);
    this.compressor = Objects.requireNonNull(compressor);
    this.encryptor = Objects.requireNonNull(encryptor);
    this.encoder = Objects.requireNonNull(encoder);
  }

  /**
   * Initialize a new default Java object serializer using base64 encoding and no compression or
   * encryption
   */
  public ObjectSerializer() {
    this.serializer = new JavaSerializeStrategy();
    this.compressor = new PassthroughCompressStrategy();
    this.encryptor = new PassthroughEncryptStrategy();
    this.encoder = new Base64EncodeStrategy();
  }

  /**
   * Serialize a Java object into a string
   * 
   * @param object The object to serialize
   * 
   * @return A serialized string representation of the object
   * 
   * @throws IOException If the serialization fails
   */
  public String serialize(final Serializable object) throws IOException {
    final byte[] serialized = serializer.serialize(object);
    final byte[] compressed = compressor.compress(serialized);
    final byte[] encrypted = encryptor.encrypt(compressed);
    final String encoded = encoder.encode(encrypted);
    return encoded;
  }

  /**
   * Deserialize a string back into the original Java object
   * 
   * @param str The string to deserialize
   * 
   * @return The original object
   * 
   * @throws IOException If the deserialization fails
   */
  public Serializable deserialize(final String str) throws IOException {
    final byte[] decoded = encoder.decode(str);
    final byte[] decrypted = encryptor.decrypt(decoded);
    final byte[] decompressed = compressor.decompress(decrypted);
    final Serializable deserialized = serializer.deserialize(decompressed);
    return deserialized;
  }

  private final SerializeStrategy serializer;
  private final CompressStrategy compressor;
  private final EncryptStrategy encryptor;
  private final EncodeStrategy encoder;
}
