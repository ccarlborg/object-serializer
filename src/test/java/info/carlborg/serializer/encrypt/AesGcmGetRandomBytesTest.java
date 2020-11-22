package info.carlborg.serializer.encrypt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import info.carlborg.serializer.encrypt.AesGcmEncryptStrategy;

public class AesGcmGetRandomBytesTest {
  @Test
  void uniquenessTest() {
    Set<byte[]> all = new HashSet<>();

    for (int i = 0; i < 1000; ++i) {
      all.add(AesGcmEncryptStrategy.getRandomBytes(8));
    }

    assertEquals(1000, all.size());
  }

  @Test
  void badInputTest() {
    assertThrows(IllegalArgumentException.class, () -> {
      AesGcmEncryptStrategy.getRandomBytes(-1);
    });
  }

  @Test
  void limitsTest() {
    assertEquals(0, AesGcmEncryptStrategy.getRandomBytes(0).length);
    assertEquals(1000000, AesGcmEncryptStrategy.getRandomBytes(1000000).length);
  }
}
