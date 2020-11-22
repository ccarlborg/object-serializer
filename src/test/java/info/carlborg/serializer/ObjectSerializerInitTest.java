package info.carlborg.serializer;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import info.carlborg.serializer.ObjectSerializer;
import info.carlborg.serializer.compress.PassthroughCompressStrategy;
import info.carlborg.serializer.encode.Base64EncodeStrategy;
import info.carlborg.serializer.encrypt.PassthroughEncryptStrategy;
import info.carlborg.serializer.serialize.JavaSerializeStrategy;

public class ObjectSerializerInitTest {
  @Test
  void badParamsTest() {
    assertThrows(NullPointerException.class, () -> {
      new ObjectSerializer(null, new PassthroughCompressStrategy(),
          new PassthroughEncryptStrategy(), new Base64EncodeStrategy());
    });
    assertThrows(NullPointerException.class, () -> {
      new ObjectSerializer(new JavaSerializeStrategy(), null, new PassthroughEncryptStrategy(),
          new Base64EncodeStrategy());
    });
    assertThrows(NullPointerException.class, () -> {
      new ObjectSerializer(new JavaSerializeStrategy(), new PassthroughCompressStrategy(), null,
          new Base64EncodeStrategy());
    });
    assertThrows(NullPointerException.class, () -> {
      new ObjectSerializer(new JavaSerializeStrategy(), new PassthroughCompressStrategy(),
          new PassthroughEncryptStrategy(), null);
    });
  }
}
