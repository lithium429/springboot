package com.li.frame.spring.fibonacci;

import java.util.Arrays;

public class FibonacciDemo {

    public static int[] getFibonacci(int maxSize) {
        int[] fibonacci = new int[maxSize];
        fibonacci[0] = 0;
        fibonacci[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }
        return fibonacci;
    }

    public static int fibonacciSearch(int[] arr, int findValue) {
        int i = 0;
        int mid;
        int left = 0;
        int right = arr.length - 1;

        int[] fibonacci = getFibonacci(20);
        while (fibonacci[i] < arr.length) {
            i++;
        }
        int[] temp = Arrays.copyOf(arr, fibonacci[i]);
        for (int j = arr.length; j < temp.length; j++) {
            temp[j] = arr[arr.length - 1];
        }
        while (left <= right) {
            mid = left + fibonacci[i - 1] - 1;
            if (temp[mid] < findValue) {
                left = mid + 1;
                i -= 2;
            } else if (temp[mid] > findValue) {
                right = mid - 1;
                i -= 1;
            } else {
                if (mid <= right) {
                    return mid;
                } else {
                    return right;
                }
            }
        }
        return -1;
    }
}
