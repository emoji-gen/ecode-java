# ecode-java
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
    implementation 'ninja.emojigen:ecode:0.1.0'
}
```

## Usage

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

        final String code = new EcodeEncoder().encode(ecode);
        System.out.println(code); // => "BA0hzxI0VniavN7wYWIKYw"
    }
}
```

## License
MIT &copy; [Emoji Generator](https://emoji-gen.ninja)
