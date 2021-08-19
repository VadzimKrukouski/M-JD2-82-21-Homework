package by.it_academy.jd2.homeworkTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class InOutTest {

    @ParameterizedTest
    @MethodSource("arrayProvider")
    public void getArray(String line, double[] result) {
        Assertions.assertArrayEquals(result, InOut.getArray(line));
    }

    private static Stream<Arguments> arrayProvider() {
        return Stream.of(
                Arguments.arguments("1 3 4 5", new double[]{1, 3, 4, 5}),
                Arguments.arguments("5 6 9 8", new double[]{5, 6, 9, 8})
        );
    }


}