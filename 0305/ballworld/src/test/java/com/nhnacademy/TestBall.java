package com.nhnacademy;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestBall {
    @Test
    void testConstructor(int x, int y, int radius) {
         
        assertDoesNotThrow(()->{
            new Ball(1,2,3);
        });
    }

    @ParameterizedTest
    @MethodSource("illegalArgumentsExceptionProvider")
    void testConstructorWidthIllegalArgumentsException(int x, int y, int radius) {
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            new Ball(x,y,radius);
        });
    }

    static Stream<Arguments> illegalArgumentsExceptionProvider() {
        return Stream.of(
            Arguments.arguments(1,2, 0),
            Arguments.arguments(1, -1, Integer.MAX_VALUE),
            Arguments.arguments(Integer.MAX_VALUE, Integer.MIN_VALUE, 1));
    }
}
