package info.carlborg.serializer.compress;

import org.junit.jupiter.api.BeforeAll;
import info.carlborg.serializer.ObjectSerializer;
import info.carlborg.serializer.ObjectSerializerTest;
import info.carlborg.serializer.compress.GzipCompressStrategy;
import info.carlborg.serializer.encode.Base64EncodeStrategy;
import info.carlborg.serializer.encrypt.PassthroughEncryptStrategy;
import info.carlborg.serializer.serialize.JavaSerializeStrategy;

/**
 * See ObjectSerializerTest for test cases
 */
public class GzipTest extends ObjectSerializerTest {
  @BeforeAll
  static void initPassthroughSerializer() {
    serializer = new ObjectSerializer(new JavaSerializeStrategy(), new GzipCompressStrategy(),
        new PassthroughEncryptStrategy(), new Base64EncodeStrategy());
  }
}
