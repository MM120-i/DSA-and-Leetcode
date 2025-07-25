package Heap;

import java.util.*;

public class Heap {
	
	private ArrayList<Integer> heap;
	
	public Heap() {
		heap = new ArrayList<Integer>();
	}
	
	public int pop() {
		
		if(heap.isEmpty()) {
			throw new IllegalStateException("Heap is empty");
		}
		
		// get min (root)
		int min = heap.get(0);
		int last = heap.remove(heap.size() - 1);	// remove last element
		
		if(!heap.isEmpty()) {
			heap.set(0, last);	// move last node to root node
			bubbleDown(0);
		}
		
		return min;
	}
	
	private void bubbleDown(int i) {
		
		final int n = heap.size();
	
		while(true) {
			
			int left = 2 * i + 1;
			int right = 2 * i + 2;
			int smallest = i;
			
			if(left < n && heap.get(left) < heap.get(smallest)) {
				smallest = left;
			}
			
			if(right < n && heap.get(right) < heap.get(smallest)) {
				smallest = right;
			}
			
			if(smallest != i) {
				swap(i, smallest);
				i = smallest;	// keep bubbling down
			}
			else {
				break;
			}
		}
	}
	
	public void push(int value) {
		
		heap.add(value);	// add at the end
		int i = heap.size() - 1;
		
		// bubble up
		while(i > 0) {
			
			int parent = (i - 1) / 2;
			
			if(heap.get(i) < heap.get(parent)) {
				swap(i, parent);
				i = parent;
			}
			else {
				break;
			}
		}
	}
	
	public void heapify(List<Integer> elements) {
		
		heap = new ArrayList<>(elements);
		final int n = heap.size();
		
		// starting from the last non leaf node and bubble down
		for(int i = n / 2; i >= 0; i--) {
			bubbleDown(i);
		}
	}
	
	public void remove(int value) {
		
		int index = heap.indexOf(value);
		
		if(index == -1) {
			return;
		}
		
		int last = heap.size() - 1;
		swap(index, last);
		heap.remove(last);
		
		if(index < heap.size()) {
			bubbleDown(index);
			bubbleDown(index);
		}
	}
	
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	
	private void swap(int i, int j) {
		int temp = heap.get(i);
		heap.set(i, heap.get(i));
		heap.set(j, temp);
	}
	
	public int peek() {
		
		if(heap.isEmpty()) {
			throw new IllegalStateException("Heap is empty");
		}
		
		return heap.get(0);
	}
	
	public void printHeap() {
		System.out.println(heap);
	}

	public static void main(String[] args) {
		
		Heap minHeap = new Heap();
		minHeap.push(5);
		minHeap.push(7);
		minHeap.push(3);
		minHeap.push(9);
		minHeap.push(1);
		minHeap.printHeap();
		
		System.out.println("Popped: " + minHeap.pop());
		minHeap.printHeap();
		
		List<Integer> elements = Arrays.asList(4, 6, 9, 3, 2, 8, 3);
		minHeap.heapify(elements);
		minHeap.printHeap();
	}
}

class MaxHeap{
	
	private List<Integer> heap = new ArrayList<>();
	
	// Push a new value
	public void push(int value) {
		heap.add(value);
		bubbleUp(heap.size() - 1);
	}
	
	// pop the maximum value
	public int pop() {
		
		if(heap.isEmpty()) {
			throw new NoSuchElementException("Heap is empty");
		}
		
		int max = heap.get(0);
		int last = heap.size() - 1;
		swap(0, last);
		heap.remove(last);
		bubbleDown(0);
		
		return max;
	}
	
	// Peek the maximum value
	public int peek() {
		
		if(heap.isEmpty()) {
			throw new NoSuchElementException("Heap is empty");
		}
		
		return heap.get(0);
	}
	
	// check if heap is empty
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	
	// remove a specific value
	public void remove(int value) {
		
		int index = heap.indexOf(value);
		
		if(index == -1) {
			return;
		}
		
		int last = heap.size() - 1;
		swap(index, last);
		heap.remove(last);
		
		if(index < heap.size()) {
			bubbleDown(index);
			bubbleUp(index);
		}
	}
	
	// Heapify from a list of elements
	public void heapify(List<Integer> elements) {
		
		heap = new ArrayList<>(elements);
		
		for(int i = heap.size() / 2 - 1; i >= 0; i--) {
			bubbleDown(i);
		}
	}
	
	public void printHeap() {
		System.out.println(heap);
	}

	private void bubbleDown(int i) {
		
		final int n = heap.size();
		
		while(true) {
			
			int left = 2 * i + 1;
			int right = 2 * i + 2;
			int largest = i;
			
			if(left < n && heap.get(left) > heap.get(largest)) {
				largest = left;
			}
			
			if(left < n && heap.get(right) > heap.get(largest)) {
				largest = right;
			}
			
			if(largest != i) {
				swap(i, largest);
				i = largest;
			}
			else {
				break;
			}
		}
	}

	private void swap(int i, int j) {
		int temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(i, temp);
	}

	private void bubbleUp(int i) {
		
		while(i > 0) {
			
			int parent = (i - 1) / 2;
			
			if(heap.get(i) > heap.get(parent)) {
				swap(i, parent);
				i = parent;
			}
			else {
				break;
			}
		}
	}
	
    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap();

        heap.push(10);
        heap.push(4);
        heap.push(15);
        heap.push(20);
        heap.push(1);

        heap.printHeap(); 

        System.out.println("Max: " + heap.pop()); 
        heap.printHeap(); 
    }
}
