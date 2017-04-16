package edu.osu.cs362;

        import java.util.concurrent.ThreadLocalRandom;

// Heavily modified code based off of Lars Vogel's Java MergeSort implementation
// Repo @ https://github.com/vogellacompany/codeexamples-java/blob/master/de.vogella.algorithms.sort.mergesort/src/de/vogella/algorithms/sort/mergesort/Mergesort.java

public class MergeSort {
    private int[] numbers;	// Array to hold numbers
    private int arrSize;

    public MergeSort() {
        this.numbers = new int[10];
        this.arrSize = 10;

        for(int i = 0; i < arrSize; i++) {
            int random = ThreadLocalRandom.current().nextInt(0, 51);
            numbers[i] = random;
        }
    }

    public void printNumbers() {
        for(int i = 0; i < arrSize; i++) {
            System.out.print(this.numbers[i]);
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        MergeSort ms = new MergeSort();

        ms.printNumbers();
    }
}
