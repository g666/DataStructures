/**
 * 
 */
package recursion;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;


/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class Heap<T> {

	private T[] heap;
	private boolean isMaxHeap;

	public Heap(T[] heap, boolean isMaxHeap) {
		this.heap = heap;
		this.isMaxHeap = isMaxHeap;
	}

	public Heap() {
		this.isMaxHeap = true;
	}
	
	public boolean isEmpty(){
		return heap.length==0;
	}
	
	public int size(){
		return heap.length;
	}

	public T left(int i){
		return heap[leftIndex(i)];
	}

	public T right(int i){
		return heap[rightIndex(i)];
	}

	public T parent(int i){
		return heap[parentIndex(i)];
	}

	public int leftIndex(int i){
		return (i<<1)+1;
	}

	public int rightIndex(int i){
		return (i<<1)+2;
	}

	public int parentIndex(int i){
		return ((i+1)>>1)-1;
	}

	public void heapfy(int i, Comparator<T> comp){
		heapfy(i, heap.length, comp);
	}

	private void heapfy(int i, int heapSize, Comparator<T> comp){
		int left = leftIndex(i);
		int right = rightIndex(i);
		int largest;
		if(left<heapSize && ( (isMaxHeap && comp.compare(heap[left], heap[i])>0)
				|| (!isMaxHeap && comp.compare(heap[left], heap[i])<0)
				) )
			largest=left;
		else
			largest=i;
		if(right<heapSize && ( (isMaxHeap && comp.compare(heap[right], heap[largest])>0)
				|| (!isMaxHeap && comp.compare(heap[right], heap[largest])<0)
				) )
			largest = right;
		if(largest!=i){
			T temp = heap[i];
			heap[i]=heap[largest];
			heap[largest] = temp;
			heapfy(largest, heapSize, comp);
		}
	}

	public void buildHeap(T[] h, Comparator<T> comp){
		this.heap = h;
		for(int i=heap.length/2; i>=0; i--){
			heapfy(i, comp);
		}
	}

	public T[] heapSort(T[] h, Comparator<T> comp){
		buildHeap(h, comp);
		for(int i=heap.length-1; i>=1; i--){
			T temp = heap[0];
			heap[0] = heap[i];
			heap[i] = temp;
			heapfy(0, heap.length-(heap.length-i), comp);
		}

		return heap;
	}

	public T[] heapSort(Comparator<T> comp){
		buildHeap(heap, comp);
		for(int i=heap.length-1; i>=1; i--){
			T temp = heap[0];
			heap[0] = heap[i];
			heap[i] = temp;
			heapfy(0, heap.length-(heap.length-i), comp);
		}

		return heap;
	}

	public T extractMax(Comparator<T> comp){
		if(heap.length<1)
			throw new IllegalStateException("Heap underflow.");
		else{
			T max = heap[0];
			heap[0] = heap[heap.length-1];
			heap = Arrays.copyOf(heap, heap.length-1);
			heapSort(comp);
			//			heapfy(0, heap.length-1, comp);
			return max;
		}
	}

	public T extractMin(Comparator<T> comp){
		if(heap.length<1)
			throw new IllegalStateException("Heap underflow.");
		else{
			T max = heap[0];
			heap[0] = heap[heap.length-1];
			heap = Arrays.copyOf(heap, heap.length-1);
			//			heapfy(0, heap.length-1, comp);
			heapSort(comp);
			return max;
		}
	}

	@SuppressWarnings("unchecked")
	public void insert(T t, Comparator<T> comp){
		if(heap==null){
			heap = (T[]) Array.newInstance(t.getClass(), 1);
			heap[0] = t;
		}else{
			heap = Arrays.copyOf(heap, heap.length+1);
			int i = heap.length-1;
			int pp=parentIndex(i);
			while(pp>=0 && i>=0 && comp.compare(heap[pp], t)<0){
				heap[i] = heap[parentIndex(i)];
				i = parentIndex(i);
				pp=parentIndex(i);
			}
			heap[i] = t;
		}
	}

	public void setMaxHeap(boolean isMaxHeap) {
		this.isMaxHeap = isMaxHeap;
	}

	public boolean isMaxHeap() {
		return isMaxHeap;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for(int i=0; i<heap.length; i++){
			s.append(heap[i]+" ");
		}
		return s.toString();
	}

	public T[] getHeap() {
		return heap;
	}
	
	public static void main(String[] args) {
		Comparator<Integer> comp = new Comparator<Integer>() {
			@Override
			public int compare(Integer arg0, Integer arg1) {
				return arg0.compareTo(arg1);
			}
		};
		Heap<Integer> h = new Heap<Integer>();
//		h.setMaxHeap(true);
		h.insert(4, comp);
		h.insert(3, comp);
		h.insert(2, comp);
		h.insert(5, comp);
		h.heapSort(comp);
		System.out.println(h);
		System.out.println(h.extractMin(comp));
		System.out.println(h);
		h.getHeap()[1]=0;
		h.heapSort(comp);
		System.out.println(h.extractMin(comp));
		System.out.println(h);
	}

}
