# ecode-java
[![Build Status](https://travis-ci.com/emoji-gen/ecode-java.svg?branch=master)](https://travis-ci.com/emoji-gen/ecode-java)
[![codecov](https://codecov.io/gh/emoji-gen/ecode-java/branch/master/graph/badge.svg)](https://codecov.io/gh/emoji-gen/ecode-java)
[![Maintainability](https://api.codeclimate.com/v1/badges/4d68b7f773c84b4f04a9/maintainability)](https://codeclimate.com/github/emoji-gen/ecode-java/maintainability)
[![Download](https://api.bintray.com/packages/pinemz/maven/ecode/images/download.svg)](https://bintray.com/pinemz/maven/ecode)

> :musical_score: The emoji code utilities for Java

## Requirements
- Java 1.8 or later

### Android
The library also works on Android 4.4 (API level 19) or later.

## Getting started
The library is published on jcenter.

```gradle
repositories {
    jcenter()
}

depepdencies {
    implementation 'ninja.emojigen:ecode:0.1.1'
}
```

## Usage
### Encoding

```java
import ninja.emojigen.ecode.*;

class Main {
    public static void main(String ...args) {
        final EcodeV1 ecode = new EcodeV1Builder()
            .locale(EcodeLocale.EN)
            .flags(EnumSet.of(EcodeFlag.SIZE_FIXED, EcodeFlag.STRETCH))
            .align(EcodeAlign.CENTER)
            .size(EcodeSize.XHDPI)
            .format(EcodeFormat.WEBP)
            .fontId(0xcf)
            .foregroundColor(0x12345678)
            .backgroundColor(0x9abcdef0)
            .text("ab\nc")
            .build();

        final String code = new EcodeEncoder().encodeV1(ecode);
        System.out.println(code); // => "BA0hzxI0VniavN7wYWIKYw"
    }
}
```

## Decoding

```java
import ninja.emojigen.ecode.*;

class Main {
    public static void main(String ...args) {
        final EcodeV1 ecode = new EcodeDecoder().decodeV1("BA0hzxI0VniavN7wYWIKYw");
        System.out.println(ecode.getText()); // => "ab\nc"
    }
}
```

## Development
### Test

```bash
$ ./gradlew clean test
```

### Upload Bintray

```bash
$ export BINTRAY_USER=username
$ export BINTRAY_KEY=apiKey
$ ./gradlew clean assemble bintrayUpload
```

## Ported projects

|Name|Language|
|----|--------|
|[ecode-js](https://github.com/emoji-gen/ecode-js)|JavaScript|
|[ecode-java](https://github.com/emoji-gen/ecode-java)|Java|

## License
MIT &copy; [Emoji Generator](https://emoji-gen.ninja)
