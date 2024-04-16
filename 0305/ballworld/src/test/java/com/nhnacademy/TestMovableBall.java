package com.nhnacademy;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.awt.Color;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestMovableBall {
    @Test
    void testConstructor() {
        assertDoesNotThrow(() -> {
            MovableBall ball = new MovableBall(1, 1, 1, Color.RED);

            assertEquals(MovableBall.DEFAULT_DX, ball.getDx());
            assertEquals(MovableBall.DEFAULT_DY, ball.getDy());
        });
    }

    @ParameterizedTest
    @MethodSource("deltaProvider")
    void testDeltaXY(int x, int y, int radius, int dx, int dy) {
        assertDoesNotThrow(() -> {
            MovableBall ball = new MovableBall(x, y , radius, Color.RED);

            ball.setDx(dx);
            ball.setDy(dy);
            assertEquals(dx, ball.getDx());
            assertEquals(dy, ball.getDy());
        });
    }

    static Stream<Arguments> deltaProvier() {
        return Stream.of(
            Arguments.arguments(0, 0, 10, 0, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("moveProvider")
    void testMove(int x, int y, int radius, int dx, int dy, int count) {
        assertDoesNotThrow(() -> {
            MovableBall ball = new MovableBall(x, y, radius, Color.BLUE);

            ball.setDx(dx);
            ball.setDy(dy);
                
            int curX = x;
            int curY = y;

            for(int i=0; i<count; i++) {
                ball.move();
                curX += dx;
            }
        });
    }
}
