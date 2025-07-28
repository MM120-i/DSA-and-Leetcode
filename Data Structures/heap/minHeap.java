package Heap;

import java.util.*;

public class minHeap {
	
	private ArrayList<Integer> heap;
	
	public minHeap() {
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
}
