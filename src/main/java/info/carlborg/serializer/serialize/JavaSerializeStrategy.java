package info.carlborg.serializer.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;

/**
 * Serialize Java objects using the standard Java object serializer
 * 
 * Refer to the SerializeStrategy interface for more details
 */
public class JavaSerializeStrategy implements SerializeStrategy {
  @Override
  public byte[] serialize(final Serializable object) throws IOException {
    final ByteArrayOutputStream baos = new ByteArrayOutputStream();

    final ObjectOutputStream oos;
    try {
      oos = new ObjectOutputStream(baos);
    } catch (SecurityException e) {
      throw new IOException(e);
    }

    try {
      oos.writeObject(object);
    } catch (InvalidClassException | NotSerializableException e) {
      throw new IOException(e);
    }

    final byte[] serialized = baos.toByteArray();
    baos.close();
    oos.close();
    return serialized;
  }

  @Override
  public Serializable deserialize(final byte[] bytes) throws IOException {
    final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);

    final ObjectInputStream ois;
    try {
      ois = new ObjectInputStream(bais);
    } catch (StreamCorruptedException | SecurityException e) {
      throw new IOException(e);
    }

    final Serializable object;
    try {
      object = (Serializable) ois.readObject();
    } catch (ClassNotFoundException e) {
      throw new IOException(e);
    }

    bais.close();
    ois.close();
    return object;
  }
}
