package info.carlborg.serializer;

import org.junit.jupiter.api.BeforeAll;
import info.carlborg.serializer.ObjectSerializer;
import info.carlborg.serializer.compress.PassthroughCompressStrategy;
import info.carlborg.serializer.encode.Base64EncodeStrategy;
import info.carlborg.serializer.encrypt.PassthroughEncryptStrategy;
import info.carlborg.serializer.serialize.JavaSerializeStrategy;

/**
 * See ObjectSerializerTest for test cases
 */
public class PassthroughTest extends ObjectSerializerTest {
  @BeforeAll
  static void initPassthroughSerializer() {
    serializer =
        new ObjectSerializer(new JavaSerializeStrategy(), new PassthroughCompressStrategy(),
            new PassthroughEncryptStrategy(), new Base64EncodeStrategy());
  }
}
