package com.aston;

import java.util.Comparator;

public class ArrayListQuickSort<T> {
    public static <T> void quickSort(MyArrayList<T> list, int low, int high, Comparator<? super T> comparator) {
        if (list.size()<=1)
            return;
        if (low < high) {
            int partitionIndex = partition(list, low, high, comparator);
            quickSort(list, low, partitionIndex - 1, comparator);
            quickSort(list, partitionIndex + 1, high, comparator);
        }
    }

    private static <T> int partition(MyArrayList<T> list, int low, int high, Comparator<? super T> comparator) {
        T pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(list.get(j), pivot) <= 0) {
                i++;
                list.swap(i, j);
            }
        }
        list.swap(i + 1, high);
        return i + 1;
    }
}
