package com.example.SortingApp.services;

import com.example.SortingApp.model.SortingRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Collections;

@Service
public class SortingService {

    
    public List<Integer> sort(SortingRequest request) {
        List<Integer> numbers = request.getNumbers();
        String algorithm = request.getAlgorithm();

        if (numbers == null || numbers.isEmpty()) {
            return numbers;
        }

        switch (algorithm.toLowerCase()) {
            case "selection":
                selectionSort(numbers);
                break;
            case "bubble":
                bubbleSort(numbers);
                break;
            case "insertion":
                insertionSort(numbers);
                break;
            case "merge":
                numbers = mergeSort(numbers);
                break;
            case "quick":
                quickSort(numbers, 0, numbers.size() - 1);
                break;
            default:
                Collections.sort(numbers);
        }
        return numbers;
    }

    
    private void selectionSort(List<Integer> numbers) {
        int n = numbers.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (numbers.get(j) < numbers.get(minIndex)) {
                    minIndex = j;
                }
            }
            Collections.swap(numbers, i, minIndex);
        }
    }


    private void bubbleSort(List<Integer> numbers) {
        int n = numbers.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (numbers.get(j) > numbers.get(j + 1)) {
                    Collections.swap(numbers, j, j + 1);
                }
            }
        }
    }

    private void insertionSort(List<Integer> numbers) {
        int n = numbers.size();
        for (int i = 1; i < n; i++) {
            int key = numbers.get(i);
            int j = i - 1;
            while (j >= 0 && numbers.get(j) > key) {
                numbers.set(j + 1, numbers.get(j));
                j--;
            }
            numbers.set(j + 1, key);
        }
    }

  
    private List<Integer> mergeSort(List<Integer> numbers) {
        if (numbers.size() <= 1) {
            return numbers;
        }
        int mid = numbers.size() / 2;
        List<Integer> left = mergeSort(numbers.subList(0, mid));
        List<Integer> right = mergeSort(numbers.subList(mid, numbers.size()));
        return merge(left, right);
    }

    private List<Integer> merge(List<Integer> left, List<Integer> right) {
        int i = 0, j = 0;
        List<Integer> merged = new java.util.ArrayList<>();
        while (i < left.size() && j < right.size()) {
            if (left.get(i) < right.get(j)) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }
        merged.addAll(left.subList(i, left.size()));
        merged.addAll(right.subList(j, right.size()));
        return merged;
    }

  
    private void quickSort(List<Integer> numbers, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(numbers, low, high);
            quickSort(numbers, low, pivotIndex - 1);
            quickSort(numbers, pivotIndex + 1, high);
        }
    }

    private int partition(List<Integer> numbers, int low, int high) {
        int pivot = numbers.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (numbers.get(j) < pivot) {
                i++;
                Collections.swap(numbers, i, j);
            }
        }
        Collections.swap(numbers, i + 1, high);
        return i + 1;
    }
}
