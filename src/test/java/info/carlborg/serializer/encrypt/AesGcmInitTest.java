package info.carlborg.serializer.encrypt;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.security.InvalidKeyException;
import org.junit.jupiter.api.Test;
import info.carlborg.serializer.encrypt.AesGcmEncryptStrategy;

public class AesGcmInitTest {
  @Test
  void invalidKeyTest() {
    byte[] empty = new byte[0];

    assertThrows(IllegalArgumentException.class, () -> {
      new AesGcmEncryptStrategy(null);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new AesGcmEncryptStrategy(empty);
    });
  }

  @Test
  void wrongKeySizeTest() {
    byte[] small = AesGcmEncryptStrategy.getRandomBytes(1);
    byte[] offByOne = AesGcmEncryptStrategy.getRandomBytes(15);
    byte[] huge = AesGcmEncryptStrategy.getRandomBytes(1000000);

    assertThrows(InvalidKeyException.class, () -> {
      new AesGcmEncryptStrategy(small);
    });
    assertThrows(InvalidKeyException.class, () -> {
      new AesGcmEncryptStrategy(offByOne);
    });
    assertThrows(InvalidKeyException.class, () -> {
      new AesGcmEncryptStrategy(huge);
    });
  }

  @Test
  void correctKeySizeTest() throws InvalidKeyException {
    byte[] key128 = AesGcmEncryptStrategy.getRandomBytes(16);
    byte[] key192 = AesGcmEncryptStrategy.getRandomBytes(24);
    byte[] key256 = AesGcmEncryptStrategy.getRandomBytes(32);

    new AesGcmEncryptStrategy(key128);
    new AesGcmEncryptStrategy(key192);
    new AesGcmEncryptStrategy(key256);
  }
}
