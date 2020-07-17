package org.tempuri.pojotests;

import java.util.Random;

public final class IntegerValuesGenerator {
    private IntegerValuesGenerator() {
    }

    private static final Random RANDOM = new Random();

    public static int getRandomPositiveInteger() {
        return RANDOM.nextInt(Integer.MAX_VALUE / 2);
    }

    public static int getRandomNegativeInteger() {
        return -RANDOM.nextInt(Integer.MAX_VALUE / 2);
    }

    public static int getRandomPositiveNotZeroValue() {
        int randomInt = 0;
        do {
            randomInt = getRandomPositiveInteger();
        } while (randomInt == 0);
        return randomInt;
    }

    public static int getRandomNegativeNotZeroValue() {
        return -getRandomPositiveNotZeroValue();
    }
}
