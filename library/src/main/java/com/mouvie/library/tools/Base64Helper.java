package com.mouvie.library.tools;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Base64Helper {

    /**
     * decode base64 string to bytes[]
     * @param base64String the base64 picture
     * @return the byte array of the base64 image
     * @throws UnsupportedEncodingException if the base64 format is not supported
     */
    public static byte[] decodeBase64ToBytes(String base64String) {
        if(base64String.contains(","))
            base64String = base64String.replaceFirst("^+.+,", "");
        return Base64.getDecoder().decode(base64String.getBytes(StandardCharsets.UTF_8));
    }
}
