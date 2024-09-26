package org.example.workWithLists;
import java.util.*;
public class task2 {
    public static void main(String[] args) {
        int n = getSize();
        ArrayList<Double> list = randomNumbers(n);
        System.out.println("Start list: " + list);
        insertionSort(list);
        System.out.println("Sorted list: " + list);
    }

    public static ArrayList<Double> randomNumbers(int n) {
        ArrayList<Double> list = new ArrayList<>();
        Random random = new Random();
        int max = 100, min = -100;
        for (int i = 0; i < n; i++) {
            list.add((double) (random.nextInt(max - min + 1) + min));
        }
        return list;
    }
    private static int getSize() {
        System.out.print("Enter the size of the list: ");
        return new Scanner(System.in).nextInt();
    }
    public static void insertionSort(ArrayList<Double> list) {
        int n = list.size();
        for (int i = 1; i < n; i++) {
            double key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }
}
