package com.example.product.util;

import java.security.SecureRandom;
import java.util.Random;

public class RandomStringGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int DEFAULT_LENGTH = 10;

    private static final Random random = new SecureRandom();

    // Generate a random string of default length
    public static String generateRandomString() {
        return generateRandomString(DEFAULT_LENGTH);
    }

    // Generate a random string of specified length
    public static String generateRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }

}

