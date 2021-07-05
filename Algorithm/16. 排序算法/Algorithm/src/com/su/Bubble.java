package com.su;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Bubble {
    public static void main(String[] args) {
        int[] a = {1, 5, 2, 8, 9, 19, 4, 3, 5};
//        bubbleSort(a);
//        selectSort(a);
//        insertSort(a);
//        shellSort(a);
//        mergeSortFromTopToDown(a, 0, a.length - 1);
//        quickSort(a, 0, a.length - 1);
//        heapSort(a);
//        countingSort(a);
//        bucketSort(a);
        radixSort(a);
        System.out.println(Arrays.toString(a));
    }

    private static void radixSort(int[] a) {
        int maxValue = Arrays.stream(a).max().getAsInt();
        int digits = (int) Math.log10(maxValue) + 1;
        for (int i = 0; i < digits; i++) {
            radixBucketSort(a, i);
        }

    }

    private static void radixBucketSort(int[] a, int i) {
        int bucketNum = 10;
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        for (int j = 0; j < bucketNum; j++) {
            buckets.add(new ArrayList<Integer>());
        }
        for (int j = 0; j < a.length; j++) {
            int idx = a[j] / (int) Math.pow(10, i) % 10;
            buckets.get(idx).add(a[j]);
        }
        int k = 0;
        for (int j = 0; j < buckets.size(); j++) {
            for (int l = 0; l < buckets.get(j).size(); l++) {
                a[k++] = buckets.get(j).get(l);
            }
        }
    }

    private static void bucketSort(int[] a) {
        int minValue = Arrays.stream(a).min().getAsInt();
        int maxValue = Arrays.stream(a).max().getAsInt();
        int bucketSize = 5;
        int bucketRange = (maxValue - minValue) / bucketSize + 1;
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < bucketSize; i++) {
            buckets.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < a.length; i++) {
            int idx = (a[i] - minValue) / bucketRange;
            ArrayList<Integer> integers = buckets.get(idx);
            integers.add(a[i]);
        }
        int idxArray = 0;
        for (int i = 0; i < bucketSize; i++) {
            insertArrayListSort(buckets.get(i));
            for (int j = 0; j < buckets.get(i).size(); j++) {
                a[idxArray++] = buckets.get(i).get(j);
            }
        }

    }

    private static void countingSort(int[] a) {
        int maxValue = Arrays.stream(a).max().getAsInt();
        int[] bucket = new int[maxValue + 1];
        int idx = 0;
        for (int i = 0; i < a.length; i++) {
            bucket[a[i]]++;
        }
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i] > 0) {
                a[idx++] = i;
                bucket[i]--;
            }
        }
    }

    private static void heapSort(int[] a) {
        // 生成堆
        for (int k = a.length / 2; k >= 1; k--) {
            heapify(a, k, a.length);
        }

        // 排序
        int m = a.length;
        while(m > 1){
            swap(a, 1, m--);
            heapify(a, 1, m);
        }
    }

    private static void heapify(int[] a, int i, int len) {
        while (2 * i < len) {
            int j = 2 * i;
            if (j < len && a[j] < a[j + 1]) j++;
            if (a[i] >= a[j]) break;
            swap(a, i, j);
            i = j;
        }
    }

    private static void swap(int[] a, int l, int f) {
        int temp = a[l];
        a[l] = a[f];
        a[f] = temp;
    }

    private static void quickSort(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(a, lo, hi);
        quickSort(a, lo, j - 1);
        quickSort(a, j + 1, hi);
    }

    private static int partition(int[] a, int lo, int hi) {
        int pivot = lo;
        int index = lo + 1;
        for (int i = index; i <= hi; i++) {
            if (a[i] < a[pivot]) {
                int temp = a[i];
                a[i] = a[index];
                a[index] = temp;
                index++;
            }
        }
        int temp = a[pivot]; a[pivot] = a[index - 1]; a[index - 1] = temp;
        return index - 1;
    }

    private static void mergeSortFromTopToDown(int[] a, int lo, int hi){
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        mergeSortFromTopToDown(a, lo, mid);
        mergeSortFromTopToDown(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    private static void merge(int[] a, int lo, int mid, int hi) {
        int[] temp = new int[hi - lo + 1];
        int i = lo; int j = mid + 1; int k = 0;
        while (i <= mid && j <= hi) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        while (j <= hi) {
            temp[k++] = a[j++];
        }

        for (int l = 0; l < temp.length; l++) {
            a[lo + l] = temp[l];
        }
    }

    private static void shellSort(int[] a) {
        for (int gap = a.length / 2; gap > 0 ; gap /= 2) {
            for (int i = gap; i < a.length; i++) {
                int pre = i - gap;
                int cur = a[i];
                while (pre >= 0 && a[pre] > cur) {
                    a[pre + gap] = a[pre];
                    pre -= gap;
                }
                a[pre + gap] = cur;
            }
        }
    }

    private static void insertArrayListSort(List<Integer> a) {
        int pre, cur;
        for (int i = 1; i < a.size(); i++) {
            pre = i - 1;
            cur = a.get(i);
            while (pre >= 0 && a.get(pre) > cur) {
                a.set(pre + 1, a.get(pre));
                pre--;
            }
            a.set(pre + 1, cur);
        }
    }

    private static void insertSort(int[] a) {
        int pre, cur;
        for (int i = 1; i < a.length; i++) {
            pre = i - 1;
            cur = a[i];
            while (pre >= 0 && a[pre] > cur) {
                a[pre + 1] = a[pre];
                pre--;
            }
            a[pre + 1] = cur;
        }
    }

    private static void selectSort(int[] a) {
        int minIndex;
        for (int i = 0; i < a.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = a[i]; a[i] = a[minIndex]; a[minIndex] = temp;

        }
    }

    private static void bubbleSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j+1]) {
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
    }
}
