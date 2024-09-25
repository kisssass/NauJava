package org.example.workingWithArrays;
import java.util.*;

//4) for me
public class task1{
    public static void main(String[] args) {
        int n = getSize();
        int[] numbers = randomNumbers(n);
        printArray(numbers);
        int lastPositive = findLastPositive(numbers);
        System.out.println("Last positive element: " + lastPositive);
    }

    private static int getSize() {
        System.out.print("Enter the size of the array: ");
        return new Scanner(System.in).nextInt();
    }

    private static int[] randomNumbers(int n) {
        int[] arr = new int[n];
        Random random = new Random();
        int max = 100, min = -100;
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(max - min + 1) + min;
        }
        return arr;
    }


    private static void printArray(int[] arr) {
        System.out.print("Array: [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    private static int findLastPositive(int[] arr) {
        int lastPositive = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                lastPositive = arr[i];
            }
        }
        return lastPositive;
    }
}
