package rxk164330;

/** Sample starter code for SP9.
 *  @author
 */

import java.util.Random;

public class SP9 {

    public static Random random = new Random();
    public static int numTrials = 100;


    public static void main(String[] args) {

        int n = 10;  int choice = 1 + random.nextInt(4);
        if(args.length > 0) { n = Integer.parseInt(args[0]); }
        if(args.length > 1) { choice = Integer.parseInt(args[1]); }
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = i;
        }
        Timer timer = new Timer();
        switch(choice) {
            case 1:
                Shuffle.shuffle(arr);
                //Shuffle.printArray(arr,"Original Array");
                //for(int i=0;i<)
                //printArray(arr);
                System.out.println(arr.length);
                numTrials = 1;
                insertionSort(arr);
                System.out.println(arr.length);
                printArray(arr);
                break;
            case 2:
                for(int i=0; i<numTrials; i++) {
                    Shuffle.shuffle(arr);
                    mergeSort1(arr);
                }
                printArray(arr);
                break;// etc
            case 3:
                for(int i=0; i<numTrials; i++) {
                    Shuffle.shuffle(arr);
                    mergeSort2(arr);
                }
                printArray(arr);
                break;
            case 4:
                for(int i=0; i<numTrials; i++) {
                    Shuffle.shuffle(arr);
                    mergeSort3(arr);
                }
                printArray(arr);
                break;
        }
        timer.end();
        timer.scale(numTrials);

        System.out.println("Choice: " + choice + "\n" + timer);
    }

    /**
     * Static method to perform Insertion sort
     * @param arr input array to be sorted
     */
    public static void insertionSort(int[] arr) {
        insertionSort(arr,0,arr.length-1);
    }

    /**
     * Helper method for insertion sort, with starting and ending indices
     * @param arr input array
     * @param start staring index
     * @param end ending index
     */
    static void insertionSort(int[] arr, int start, int end){
        for(int i=start+1; i<=end; i++){
            int temp = arr[i];
            int j=i-1;

            //finding the correct position to insert the element
            while(j>=start && temp<arr[j])
            {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = temp;
        }
    }

    /**
     * Static method to perform merge sort 1
     * @param arr input array
     */
    public static void mergeSort1(int[] arr) {
        mergeSort1(arr,0, arr.length-1);
    }

    /**
     * Helper method to perform the sorting for merge sort 1
     * @param arr input array
     * @param start starting index
     * @param end ending index
     */
    static void mergeSort1(int[] arr, int start, int end){
        if(start<end) {
            int mid = (start + end) / 2;
            mergeSort1(arr, start, mid);
            mergeSort1(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    /**
     * Helper method to merge two sorted arrays
     * @param arr input array
     * @param start start index
     * @param mid mid index
     * @param end ending index
     */
    static void merge(int[] arr, int start, int mid, int end){
         int left[] = new int[mid-start+1];
         int right[] = new int[end-mid];
         System.arraycopy(arr,start,left,0,mid-start+1);
         System.arraycopy(arr,mid+1,right,0,end-mid);

         int i=0,j=0;

         for(int k=start;k<=end;k++){
             if(j>=right.length ||(i<left.length && left[i]<=right[j])){
                 arr[k]=left[i++];
             }
             else{
                 arr[k]=right[j++];
             }
         }
    }

    /**
     * Static method to perform merge sort take 3
     * @param arr input array
     */
    public static void mergeSort3(int[] arr){
        int[] temp = new int[arr.length];
        System.arraycopy(arr, 0, temp, 0, arr.length);
        mergeSort2(arr, temp,0, arr.length);
    }

    /**
     * Static method to perform merge sort take 2
     * @param arr input array
     */
    public static void mergeSort2(int[] arr){
        mergeSort2(arr, new int[arr.length],0, arr.length);
    }

    /**
     * Helper method to perform sorting for merge sort 2
     * @param arr input array
     * @param temp new array of the same size as input array
     * @param left first index
     * @param n length of the array
     */
    static void mergeSort2(int[] arr, int[] temp, int left, int n){
        //nt T = 10;
        if(n<50){
            insertionSort(arr,left,left+n-1);
        }
        else{
            int len=n/2;
            mergeSort2(arr,temp,left,len);
            mergeSort2(arr,temp,left+len,n-len);
            merge2(arr,temp,left, left+len-1,left+n-1);

        }
    }

    /**
     * Helper method to merge two sorted arrays
     * @param arr input array
     * @param temp temporary storage for the input array
     * @param start start index
     * @param mid mid index
     * @param end ending index
     */
    static void merge2(int[] arr, int[] temp, int start, int mid, int end) {
        System.arraycopy(arr, start, temp, start, end-start+1);
        int i=start, j=mid+1;
        for(int k=start;k<=end;k++){
            if (j>end || (i<=mid && temp[i]<=temp[j])) {
                arr[k] = temp[i++];
            } else {
                arr[k] = temp[j++];
            }
        }
    }

    /**
     * Helper method to perform sorting for merge sort 3
     * @param arr input array
     * @param temp new array of the same size as input array
     * @param left first index
     * @param n length of the array
     */
    static void mergeSort3(int[] arr, int[] temp, int left, int n){
        //nt T = 10;
        if(n<50){
            insertionSort(arr,left,left+n-1);
        }
        else{
            int len=n/2;
            mergeSort3(temp,arr,left,len);
            mergeSort3(temp,arr,left+len,n-len);
            merge3(arr,temp,left, left+len-1,left+n-1);
        }
    }

    /**
     * Helper method to merge two sorted arrays
     * @param arr input array
     * @param temp copy of the input array
     * @param start start index
     * @param mid mid index
     * @param end ending index
     */
    static void merge3(int[] arr, int[] temp, int start, int mid, int end) {

        int i=start, j=mid+1,k=start;
        while(i<=mid && j<=end){
            if(temp[i]<temp[j]){
                arr[k++]=temp[i++];
            }
            else{
                arr[k++]=temp[j++];
            }
        }
        while(i<=mid){
            arr[k++]=temp[i++];
        }
        while(j<=end){
            arr[k++]=temp[j++];
        }
    }

    /**
    * Helper method to print the array elements
    * @param arr to be printed
    */
    public static void printArray(int[] arr){
        System.out.println("Array:\n");
        for(int i:arr){
            System.out.print(i+"\t");
        }
    }

    /** Timer class for roughly calculating running time of programs
     *  @author rbk
     *  Usage:  Timer timer = new Timer();
     *          timer.start();
     *          timer.end();
     *          System.out.println(timer);  // output statistics
     */

    public static class Timer {
        long startTime, endTime, elapsedTime, memAvailable, memUsed;
        boolean ready;

        public Timer() {
            startTime = System.currentTimeMillis();
            ready = false;
        }

        public void start() {
            startTime = System.currentTimeMillis();
            ready = false;
        }

        public Timer end() {
            endTime = System.currentTimeMillis();
            elapsedTime = endTime-startTime;
            memAvailable = Runtime.getRuntime().totalMemory();
            memUsed = memAvailable - Runtime.getRuntime().freeMemory();
            ready = true;
            return this;
        }

        public long duration() { if(!ready) { end(); }  return elapsedTime; }

        public long memory()   { if(!ready) { end(); }  return memUsed; }

        public void scale(int num) { elapsedTime /= num; }

        public String toString() {
            if(!ready) { end(); }
            return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed/1048576) + " MB / " + (memAvailable/1048576) + " MB.";
        }
    }

    /** @author rbk : based on algorithm described in a book
    /* Shuffle the elements of an array arr[from..to] randomly */
    public static class Shuffle {

        public static void shuffle(int[] arr) {
            shuffle(arr, 0, arr.length-1);
        }

        public static<T> void shuffle(T[] arr) {
            shuffle(arr, 0, arr.length-1);
        }

        public static void shuffle(int[] arr, int from, int to) {
            int n = to - from  + 1;
            for(int i=1; i<n; i++) {
                int j = random.nextInt(i);
                swap(arr, i+from, j+from);
            }
        }

        public static<T> void shuffle(T[] arr, int from, int to) {
            int n = to - from  + 1;
            Random random = new Random();
            for(int i=1; i<n; i++) {
                int j = random.nextInt(i);
                swap(arr, i+from, j+from);
            }
        }

        static void swap(int[] arr, int x, int y) {
            int tmp = arr[x];
            arr[x] = arr[y];
            arr[y] = tmp;
        }

        static<T> void swap(T[] arr, int x, int y) {
            T tmp = arr[x];
            arr[x] = arr[y];
            arr[y] = tmp;
        }

        public static<T> void printArray(T[] arr, String message) {
            printArray(arr, 0, arr.length-1, message);
        }

        public static<T> void printArray(T[] arr, int from, int to, String message) {
            System.out.print(message);
            for(int i=from; i<=to; i++) {
                System.out.print(" " + arr[i]);
            }
            System.out.println();
        }
    }
}
