package by.it_academy.jd2.homeworkTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class HelperTest {

    @ParameterizedTest
    @MethodSource("minProvider")
    @DisplayName("find min value in array")
    public void findMin(double[] array, double result) {
        Assertions.assertEquals(result, Helper.findMin(array));
    }

    public static Stream<Arguments> minProvider() {
        return Stream.of(
                Arguments.arguments(new double[]{1.0, 2.0, 3.0}, 1.0),
                Arguments.arguments(new double[]{4.0, 8.0, 2.0}, 2.0)
        );
    }

    @ParameterizedTest
    @MethodSource("maxProvider")
    @DisplayName("find max value in array")
    public void findMax(double[] array, double result) {
        Assertions.assertEquals(result, Helper.findMax(array));
    }

    public static Stream<Arguments> maxProvider() {
        return Stream.of(
                Arguments.arguments(new double[]{1.0, 2.0, 3.0}, 3.0),
                Arguments.arguments(new double[]{4.0, 8.0, 2.0}, 8.0)
        );
    }

    @ParameterizedTest
    @MethodSource("mulProvider")
    @DisplayName("mul matrix and vector")
    public void mul(double[][] matrix, double[] vector, double[] resultArray) {
        Assertions.assertArrayEquals(resultArray, Helper.mul(matrix, vector), 1e-10);
    }

    public static Stream<Arguments> mulProvider() {
        return Stream.of(
                Arguments.arguments(new double[][]{{8.7, 7.6}, {5.3, 4.6}, {6.7, 1.6}}, new double[]{4.6, 3.8}, new double[]{68.90, 41.86, 36.90})

        );
    }

    @ParameterizedTest
    @MethodSource("mulProvider2")
    @DisplayName("mul matrix and matrix")
    public void mul(double[][] matrix1, double[][] matrix2, double[][] resultMatrix) {
        Assertions.assertArrayEquals(resultMatrix, Helper.mul(matrix1, matrix2));
    }

    public static Stream<Arguments> mulProvider2() {
        return Stream.of(
                Arguments.arguments(
                        new double[][]{{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}, {7.0, 8.0, 9.0}},
                        new double[][]{{9.0, 8.0, 7.0}, {6.0, 5.0, 4.0}, {3.0, 2.0, 1.0}},
                        new double[][]{{30, 24, 18}, {84, 69, 54}, {138, 114, 90}}),
                Arguments.arguments(
                        new double[][]{{4.0, 2.0, 3.0}, {4.0, 5.0, 6.0}, {7.0, 8.0, 9.0}},
                        new double[][]{{9.0, 8.0, 7.0}, {6.0, 5.0, 4.0}},
                        null)
        );
    }


}