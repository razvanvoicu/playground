import java.util.Scanner;

public class Solution {

    static int [] array = null ;
    static int count = 0;
    static Scanner in = null;

    public static void main(String[] args) {
        in = new Scanner(System.in);
        count = in.nextInt();
        array = new int[count];
        for (int i = 0 ; i < count ; i++)
            array[i] = in.nextInt();
        if (count > 1) quicksort(0,count-1);
    }

    static void quicksort(int left, int right) {
        int pivot = partition(left,right);
        if (left < pivot-1) quicksort(left,pivot-1);
        if (pivot+1 < right) quicksort(pivot+1,right);
    }

    static int partition(int left, int right) {
        int pivot = right --;
        for (int i = left ; i < pivot ; i++)
            if (array[i] < array[pivot]) swap(i,left++);
        swap(pivot, left);
        printArray();
        return left;
    }

    static void swap(int a, int b) {
        int aux = array[a];
        array[a] = array[b];
        array[b] = aux;
    }

    static void printArray() {
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");
        System.out.println();

    }
}