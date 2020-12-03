package edu.epam.task.util;

import java.util.Random;

public class IdGenerator {

    public static final int BILLION = 1000000000;
    public static final int HALF_INT = 1147483648;

    private static final Random rnd = new Random();

    public static long generateId() {
        return rnd.nextInt(HALF_INT) + BILLION;
    }
}