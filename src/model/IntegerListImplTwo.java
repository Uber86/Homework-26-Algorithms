package model;

import exeption.*;

import java.util.Arrays;

public class IntegerListImplTwo implements IntegerList {

    private Integer[] list;
    private Integer maxNumber;

    public IntegerListImplTwo() {
        list = new Integer[10];
    }

    public IntegerListImplTwo(int size) {
        list = new Integer[size];
    }

    @Override
    public Integer add(Integer item) {
        validateItem(item);
        growIfNeeded();
        list[maxNumber++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        growIfNeeded();
        validateItem(item);
        validateIndex(index);
        grow();
        if (index == maxNumber) {
            list[maxNumber++] = item;
            return item;
        }
        System.arraycopy(list, index,list,index + 1,maxNumber-index);
        list[index] = item;
        maxNumber++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if (index > list.length || index < 0 && item==null) {
            throw new ArrayExceedsException();
        }
        list[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new NotFoundException();
        }
        if (index != maxNumber) {
            System.arraycopy(list, index + 1, list, index, maxNumber - index);
        }
        maxNumber--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer item = list[index];

        if (index != maxNumber) {
            System.arraycopy(list, index + 1, list, index, maxNumber - index);
        }
        maxNumber--;
        return item;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = list.length -1 ; i >= 0 ; i--) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] storegCopy = toArray();
        sort(storegCopy);
        return binContains(storegCopy, item);
    }

    @Override
    public Integer get(int index) {
        if (list.length < index || index < 0) {
            throw new ArrayExceedsException();
        }
        return list[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(),otherList.toArray());
    }

    @Override
    public int size() {
        return list.length;
    }

    @Override
    public boolean isEmpty() {
        return maxNumber == 0;
    }

    @Override
    public void clear() {
        maxNumber = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(list , maxNumber);
    }




    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void growIfNeeded() {
        if (maxNumber == list.length) {
            grow();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= maxNumber) {
            throw new InvalidIndexException();

        }
    }
    // ДЗ 2 часть

    private void sort(Integer[] list) {
        quickSort(list, 0, list.length -1);
    }

    private void quickSort(Integer[] list, int begin, int end ) {
        if (begin < end) {
            int partitionIndex = partition(list, begin, end);

            quickSort(list, begin, partitionIndex - 1);
            quickSort(list, partitionIndex + 1, end);
        }
    }

    private int partition(Integer[] list, int begin, int end) {
        int pivot = list[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (list[j] <= pivot) {
                i++;

                swapElements(list, i, j);
            }
        }

        swapElements(list, i + 1, end);
        return i + 1;
    }

    private void swapElements(Integer[] list, int i1, int i2) {
        int temp = list[i1];
        list[i1] = list[i2];
        list[i2] = temp;
    }

    public static boolean binContains(Integer[] list, Integer item) {
        int min = 0;
        int max = list.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == list[mid]) {
                return true;
            }

            if (item < list[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    // ДЗ 3

    private void grow() {
        list = Arrays.copyOf(list, maxNumber + maxNumber / 2);


    }
}


