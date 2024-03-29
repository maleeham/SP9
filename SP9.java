package msk180001;

import java.util.Random;

public class SP9 {
    public static Random random = new Random();
    public static int numTrials = 100;
    public static void main(String[] args) {
	int n = 10000;  int choice = 1 + random.nextInt(2);
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
	    numTrials = 1;
	    insertionSort(arr);
	    break;
	case 2:
	    for(int i=0; i<numTrials; i++) {
		Shuffle.shuffle(arr);
		mergeSort1(arr);
	    }
	    break;  // etc
	}
	timer.end();
	timer.scale(numTrials);

	System.out.println("Choice: " + choice + "\n" + timer);
	//test
	/**int[] a = {1, 6, 2, 101, 9, 5, 45, 21, 23, 101, 24, 45, 32,1000001};
	SP9.insertionSort(a);
	for(int i = 0; i < a.length; i++){
		System.out.print(a[i] + "\t");
	}
	SP9.mergeSort1(a);
	for(int i = 0; i < a.length; i++){
		System.out.print(a[i] + "\t");
	} */
	//test 
    }

    public static void insertionSort(int[] arr) {
    	for(int i=0; i<arr.length; i++){
    		int value = arr[i];
			int j=i-1;
			/*looping through the sorted part of array to insert the value in the correct position*/
			while(j>=0 && value<arr[j])
			{
				arr[j+1] = arr[j];
				j = j-1;
				
			}
			
			arr[j+1] = value;
    	}
    }

    public static void mergeSort1(int[] arr) {
    	mergeSort1(arr, new int[arr.length],0, arr.length-1);
    }
    public static void mergeSort1(int[] arr, int[] temp, int leftStart, int rightEnd){
    	if(leftStart >= rightEnd){
    		return;
    	}
    	int middle = (leftStart + rightEnd) / 2;
    	mergeSort1(arr, temp, leftStart, middle);
    	mergeSort1(arr, temp, middle+1, rightEnd);
    	merge(arr, temp, leftStart, rightEnd);
    }
    public static void merge(int[] arr, int[] temp, int leftStart, int rightEnd){
    	int leftEnd = (rightEnd + leftStart)/2;
    	int rightStart = leftEnd + 1;
    	int size = rightEnd - leftStart + 1;
    	int left = leftStart;
    	int right = rightStart;
    	int index = leftStart;
    	
    	while(left <= leftEnd && right <= rightEnd ){
    		if(arr[left] <= arr[right]){
    			temp[index]= arr[left];
    			left++;
    		}else {
    			temp[index] = arr[right];
    			right++;
    		}
    		index++;
    	}
    	System.arraycopy(arr, left, temp, index, leftEnd-left+1);
    	System.arraycopy(arr, right, temp, index, rightEnd - right +1);
    	System.arraycopy(temp, leftStart, arr, leftStart, size);
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
     */


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

