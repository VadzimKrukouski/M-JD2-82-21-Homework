package by.it_academy.jd2.homeworkTests;


public class InOut {
//    /**
//     * Данный метод преобразовывает строку в массив из чисел с плавающей точкой и возвращает его
//     * @param line массив в виде строки
//     * @return возвращает массив из чисел с плавающей точкой
//     * @count количество элементов в строковом массиве
//     */
    /**
     * Method
     * @param line array
     * @return return array
     * @count count element
     */
    static double[] getArray(String line) {
        line = line.trim();
        String[] elemString = line.split(" ");
        int count = elemString.length;
        double[] array = new double[count];
        for (int i = 0; i < count; i++) {
            array[i] = Double.parseDouble(elemString[i]);
        }
        return array;
    }

    /**
     * Этот метод печает массив из чисел с плавающей точкой
     * @param array массив из чисел с плавающей точкой
     * elementArray каждый член массива array
     */

    static void printArray(double[] array) {
        for (double elementArray : array) {
            System.out.print(elementArray + " ");
        }
    }

    /**
     * Этот метод печатает массив из чисел с плавающей точкой с помощью форматирования
     * @param array массив из чисел с плавающей точкой
     * @param name имя столбца
     * @param columnQuantity количество столбцов
     * column счётчик столбцов
     */

    static void printArray(double[] array, String name, int columnQuantity) {
        int column = 0;
        for (double elementArray : array) {
            System.out.printf("%-7s%-8.2f", name, elementArray);
            column++;
            if (column % columnQuantity == 0) {
                System.out.println();
            }

        }
    }

    static void printArray(double [][] array, String name) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.printf("%4s[%1d,%1d]=%-5.2f", name, i,j, array[i][j]);
            }
            System.out.println();
        }
    }
}
