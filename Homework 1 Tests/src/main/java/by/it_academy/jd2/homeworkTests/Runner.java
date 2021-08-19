package by.it_academy.jd2.homeworkTests;

public class Runner {
    public static void main(String[] args) {
        String arrayString = " 7.0 8.5 78.9 9.54 ";
        InOut.printArray(InOut.getArray(arrayString));
        System.out.println();
        InOut.printArray(InOut.getArray(arrayString), "number", 2);
        System.out.println();
        System.out.println(Helper.findMin(InOut.getArray(arrayString)));
        System.out.println(Helper.findMax(InOut.getArray(arrayString)));
        System.out.println();
        Helper.sort(InOut.getArray(arrayString));
        System.out.println();
        double[][] firstMatrix = {{5.2, 2.6, 8.7},
                                  {8.6, 9.4, 5.2},
                                  {1.5, 5.6, 6.5}};


        double[][] secondMatrix = {{8.7, 7.6},
                                   {5.3, 4.6},
                                   {6.7, 1.6}};
        double[] vector = {4.6, 3.8};
        System.out.println("Mul matrix and vector");
        InOut.printArray(Helper.mul(secondMatrix,vector), "", 1);
        System.out.println();
        InOut.printArray(Helper.mul(firstMatrix,secondMatrix),"m");

    }

}


