package by.it_academy.jd2.homeworkTests;

public class Helper {

    /**
     * Метод осуществляет поиск минимального значения массива и возвращает его
     *
     * @param array массив из чисел с плавающей точкой
     * @return возврат минимального значения массива
     */
    static double findMin(double[] array) {
        double min = array[0];
        for (double elementArray : array) {
            if (elementArray < min) {
                min = elementArray;
            }
        }
        return min;

    }

    /**
     * Метод осуществляет поиск максимального значения массива и возвращает его
     *
     * @param array массив из чисел с плавающей точкой
     * @return возврат максимального значения массива
     */

    static double findMax(double[] array) {
        double max = array[0];
        for (double elementArray : array) {
            if (elementArray > max) {
                max = elementArray;
            }
        }
        return max;
    }

    /**
     * Метод осуществляет Bubble сортировку массива и распечатывает его
     *
     * @param array массив из чисел с плавающей точкой
     */

    static void sort(double[] array) {
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    double temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }

        }
        for (double element : array) {
            System.out.println(element + " ");

        }

    }

    /**
     * Метод перемножает матрицу на вектор и возваращает результат
     *
     * @param matrix двухмерная матрица
     * @param vector вектор
     * @return возвращает новый массив после перемножения
     */

    static double[] mul(double[][] matrix, double[] vector) {
        double[] resultMatrix = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < vector.length; j++) {
                resultMatrix[i] = resultMatrix[i] + matrix[i][j] * vector[j];
            }
        }
        return resultMatrix;
    }

    /**
     * Метод перемножает матрицу на матрицу
     *
     * @param matrixLeft  двухмерный массив
     * @param matrixRight двухмерный массив
     * @return возвращает значение нового массива
     */

    static double[][] mul(double[][] matrixLeft, double[][] matrixRight) {
        int matrixLeftLength = matrixLeft[0].length;
        int matrixRightLength = matrixRight.length;
        if (matrixLeftLength != matrixRightLength) {
            return null;
        }
        double[][] matrixResult = new double[matrixLeft.length][matrixRight[0].length];
        for (int i = 0; i < matrixLeft.length; i++) {
            for (int j = 0; j < matrixRight[0].length; j++) {
                for (int k = 0; k < matrixLeft[0].length; k++) {
                    matrixResult[i][j] = matrixResult[i][j] + matrixLeft[i][k] * matrixRight[k][j];
                }
            }
        }
        return matrixResult;

    }
}
