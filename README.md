# object-serializer

Serialize Java objects into strings using pluggable strategies for serialization, compression, encryption and encoding.

Straightforward strategy interfaces make it easy to implement custom strategies for your needs.

Compatible with Java 8 and newer.

## Serialization and deserialization flow

![serialize-deserialize-flow.png](/doc/serialize-deserialize-flow.png)

## Example code

### Example 1: Default serializer using base64 encoding and no compression or encryption

```java
ObjectSerializer serializer = new ObjectSerializer();

HashMap<String, String> map = new HashMap<>();
map.put("first_item", "Serialize");
map.put("second_item", "me!");

String serialized = serializer.serialize(map);

// Do whatever you want with the serialized object at this point.
// Save it to a file, store it in a browser cookie or whatever.

HashMap<String, String> deserialized = (HashMap<String, String>)serializer.deserialize(serialized);
```

### Example 2: Gzip compression and AES-GCM encryption

```java
// Random bytes for the encryption key. A 256 bit block size requires a 32 bytes long encryption key.
byte[] encryptionKey256Bits = Base64.getDecoder().decode("hMICYUBp47LxliF9nzmPrxwCTGk1PNDi18IsA78MyLrw=");

ObjectSerializer serializer = new ObjectSerializer(
        new JavaSerializeStrategy(),
		new GzipCompressStrategy(),
		new AesGcmEncryptStrategy(encryptionKey256Bits),
		new Base64EncodeStrategy()
);

HashMap<String, String> map = new HashMap<>();
map.put("first_item", "Serialize");
map.put("second_item", "me!");

String serialized = serializer.serialize(map);

// Do whatever you want with the serialized object at this point.
// Save it to a file, store it in a browser cookie or whatever.

HashMap<String, String> deserialized = (HashMap<String, String>)serializer.deserialize(serialized);
```

### Further examples

Take a look at the test cases under ![src/test/java](/src/test/java) for further examples.

## Built-in strategies

The core library provides a set of strategies that only depend on the Java standard library.

### Serialize

| Name | Description |
|------|-------------|
| ![JavaSerializeStrategy](/src/main/java/info/carlborg/serializer/serialize/JavaSerializeStrategy.java) | Serialize the object into a byte array using the standard [Java object serializer](https://docs.oracle.com/javase/8/docs/api/java/io/ObjectOutputStream.html). |

### Compress

| Name | Description |
|------|-------------|
| ![GzipCompressStrategy](/src/main/java/info/carlborg/serializer/compress/GzipCompressStrategy.java) | Compress the serialized byte array using the gzip compression algorithm. |
| ![PassthroughCompressStrategy](/src/main/java/info/carlborg/serializer/compress/PassthroughCompressStrategy.java) | Apply no compression. |

### Encrypt

| Name                       | Description |
|----------------------------|-------------|
| ![AesGcmEncryptStrategy](/src/main/java/info/carlborg/serializer/encrypt/AesGcmEncryptStrategy.java) | Encrypt the compressed byte array using the AES-GCM cipher. Supports 128, 192 or 256 bit block size. |
| ![PassthroughEncryptStrategy](/src/main/java/info/carlborg/serializer/encrypt/PassthroughEncryptStrategy.java) | Apply no encryption. |

### Encode

| Name | Description |
|------|-------------|
| ![Base64EncodeStrategy](/src/main/java/info/carlborg/serializer/encode/Base64EncodeStrategy.java) | Encode the encrypted byte array into a base64 encoded string. |

## Other available strategies

| Name                 | Description |
|----------------------|-------------|
| ![FstSerializeStrategy](https://github.com/ccarlborg/object-serializer-fst) | Serialize the object into a byte array using the fast and efficient [FST object serializer](https://github.com/RuedigerMoeller/fast-serialization). Recommended. |

## Implementing your own strategy

Implement the respective strategy interface and pass the new strategy as an argument to the ObjectSerializer constructor.

## Building from source

Run `mvn test` to run the test suite

Run `mvn package` to generate a jarfile
