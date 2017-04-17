package edu.osu.cs362;

import java.util.concurrent.ThreadLocalRandom;

// Heavily modified code based off of Lars Vogel's Java MergeSort implementation
// Repo @ https://github.com/vogellacompany/codeexamples-java/blob/master/de.vogella.algorithms.sort.mergesort/src/de/vogella/algorithms/sort/mergesort/Mergesort.java

public class MergeSort {
    private int[] numbers;	// Array to hold numbers
    private int[] auxiliary;
    private int arrSize;
    private int buffer;

    private MergeSort(int size) {
        this.arrSize = size;
        this.buffer = this.arrSize;
        this.numbers = new int[arrSize];

        // Generate array of random numbers to sort
        for(int i = 0; i < arrSize; i++) {
            int random = ThreadLocalRandom.current().nextInt(0, 51);
            numbers[i] = random;
        }

        // Allocate memory for array used to help sort
        this.auxiliary = new int[arrSize];
    }

    private void mergeSort(int low, int high) {
        // Check if low value is smaller than high, if not then sort
        int middle;

        if(low < high) {
            // Get index of middle element
            middle = (low + high) / 2;
            // Sort left side
            mergeSort(low, middle);
            // Sort right side
            mergeSort(middle + 1, high);
            // Combine
            merge(low, middle, high);
        }
    }

    private void merge(int low, int middle, int high) {
        // Copy both pieces into auxiliary array
        for(int i = low; i <= high; i++) {
            auxiliary[i] = numbers[i];
        }

        int x = low;
        int y = middle + 1;
        int z = low;

        // Copy smallest values from either left or right side back to original
        while(x <= middle && y <= high) {
            if(auxiliary[x] <= auxiliary[y]) {
                numbers[z] = auxiliary[x];
                x++;
            }
            else {
                numbers[z] = auxiliary[y];
                y++;
            }

            z++;
        }

        // Copy the rest of the eft side of array into target
        while(x <= middle){
            numbers[z] = auxiliary[x];
            z++;
            x++;
        }
    }

    private void printNumbers() {
        for(int i = 0; i < arrSize; i++) {
            System.out.print(this.numbers[i]);
            System.out.print(" ");
        }

        System.out.print("\n\n");
    }

    public static void main(String[] args) {
        // Create new MergeSort object
        MergeSort ms = new MergeSort(10);

        System.out.println("Prior to MergeSort:\n");
        ms.printNumbers();

        // Sort 10 objects
        ms.mergeSort(0, ms.buffer - 1);

        System.out.println("Post MergeSort:\n");
        ms.printNumbers();

        // Now sort 20 objects
        System.out.println("Creating a new sort object...\n");
        ms = new MergeSort(20);

        System.out.println("Prior to 2nd MergeSort:\n");
        ms.printNumbers();

        // Sort again
        ms.mergeSort(0, ms.buffer - 1);

        System.out.println("Post 2nd MergeSort:\n");
        ms.printNumbers();
    }
}