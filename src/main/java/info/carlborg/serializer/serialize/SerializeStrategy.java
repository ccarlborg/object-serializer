package info.carlborg.serializer.serialize;

import java.io.IOException;
import java.io.Serializable;

/**
 * A generic interface to serialize Java objects into byte arrays
 *
 * All implementations of this interface must be thread safe
 * 
 * The only exception that is allowed to be thrown by the serialize and deserialize methods is
 * IOException
 * 
 * The incoming byte array to the deserialize method can be assumed to be non-null
 */
public interface SerializeStrategy {
  /**
   * Serialize a Serializable into a byte array
   * 
   * @param object Any serializable object
   * 
   * @return A byte array representation of the object
   * 
   * @throws IOException If the serialization fails
   */
  public byte[] serialize(final Serializable object) throws IOException;

  /**
   * Deserialize a byte array into a Serializable
   * 
   * @param bytes A byte array that was previously created by the same SerializeStrategy
   *        implementation
   * 
   * @return The original object
   * 
   * @throws IOException If the deserialization fails
   */
  public Serializable deserialize(final byte[] bytes) throws IOException;
}
