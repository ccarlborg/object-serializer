package info.carlborg.serializer.encrypt;

import java.security.InvalidKeyException;
import org.junit.jupiter.api.BeforeAll;
import info.carlborg.serializer.ObjectSerializer;
import info.carlborg.serializer.ObjectSerializerTest;
import info.carlborg.serializer.compress.PassthroughCompressStrategy;
import info.carlborg.serializer.encode.Base64EncodeStrategy;
import info.carlborg.serializer.encrypt.AesGcmEncryptStrategy;
import info.carlborg.serializer.serialize.JavaSerializeStrategy;

/**
 * See ObjectSerializerTest for test cases
 */
public class AesGcm128Test extends ObjectSerializerTest {
  @BeforeAll
  static void initPassthroughSerializer() throws InvalidKeyException {
    serializer =
        new ObjectSerializer(new JavaSerializeStrategy(), new PassthroughCompressStrategy(),
            new AesGcmEncryptStrategy(AesGcmEncryptStrategy.getRandomBytes(128 / 8)),
            new Base64EncodeStrategy());
  }

}
