package info.carlborg.serializer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import info.carlborg.serializer.ObjectSerializer;

/**
 * This test class contains a collection of generic test cases that you can use to sanity check your
 * strategy implementation.
 * 
 * To reuse it for your strategy, create a class that extends this class and define a @BeforeAll
 * function that initializes a serializer that uses your strategy. See PassthroughTest for an
 * example.
 */
public abstract class ObjectSerializerTest {
  @Test
  void serializeStringTest() throws IOException {
    String str = "hello";
    String other = "goodbye";

    assertEquals(str, serializer.deserialize(serializer.serialize(str)));
    assertNotEquals(other, serializer.deserialize(serializer.serialize(str)));
  }

  @Test
  void serializeHashMapTest() throws IOException {
    HashMap<String, Integer> map = new HashMap<>();
    map.put("hello", 1);
    map.put("goodbye", 2);

    HashMap<String, Integer> otherMap = new HashMap<>();
    map.put("greetings", 1);
    map.put("farewell", 2);

    assertEquals(map, serializer.deserialize(serializer.serialize(map)));
    assertNotEquals(otherMap, serializer.deserialize(serializer.serialize(map)));
  }

  @Test
  void serializeNullTest() throws IOException {
    assertEquals(null, serializer.deserialize(serializer.serialize(null)));
  }

  @Test
  void serializeTrueTest() throws IOException {
    assertEquals(true, serializer.deserialize(serializer.serialize(true)));
    assertNotEquals(false, serializer.deserialize(serializer.serialize(true)));
  }

  @Test
  void deserializeNullTest() throws IOException {
    assertThrows(NullPointerException.class, () -> {
      serializer.deserialize(null);
    });
  }

  @Test
  void deserializeEmptyStringTest() throws IOException {
    assertThrows(IOException.class, () -> {
      serializer.deserialize("");
    });
  }

  @Test
  void deserializeRandomStringTest() throws IOException {
    assertThrows(IOException.class, () -> {
      serializer.deserialize(UUID.randomUUID().toString());
    });
  }

  protected static ObjectSerializer serializer;
}
