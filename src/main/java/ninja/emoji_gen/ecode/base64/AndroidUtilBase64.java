package ninja.emoji_gen.ecode.base64;

import java.lang.reflect.Method;

public class AndroidUtilBase64 implements Base64 {
    public AndroidUtilBase64() throws ClassNotFoundException, NoSuchMethodException {
        Class<?> clazz = Class.forName("android.util.Base64");
        Method encodeToString = clazz.getMethod("encodeToString", int.class);

    }

    @Override
    public String encodeBase64URLSafeString(byte[] binaryData) {
        return null;
    }
}
