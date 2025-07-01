import java.util.ArrayList;
import java.util.Collections;

public class PowerOfTwoMaxHeap {
    private final ArrayList<Integer> heap;
    private final int childrenPerNode;

    // Constructor with a descriptive variable name
    public PowerOfTwoMaxHeap(int exponentForChildren) {
        if (exponentForChildren < 0 || exponentForChildren > 10) {
            throw new IllegalArgumentException("Exponent must be between 0 and 10 for reasonable performance.");
        }
        this.childrenPerNode = (int) Math.pow(2, exponentForChildren);
        this.heap = new ArrayList<>();
    }

    // Insert a value into the heap
    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    // Pop the maximum (root) value from the heap
    public int popMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty.");
        }

        int maxValue = heap.get(0);
        int lastIndex = heap.size() - 1;
        Collections.swap(heap, 0, lastIndex);
        heap.remove(lastIndex);
        heapifyDown(0);
        return maxValue;
    }

    // Helper: Heapify up
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / childrenPerNode;
            if (heap.get(index) > heap.get(parentIndex)) {
                Collections.swap(heap, index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    // Helper: Heapify down
    private void heapifyDown(int index) {
        int size = heap.size();

        while (true) {
            int maxIndex = index;
            int firstChildIndex = index * childrenPerNode + 1;

            for (int i = 0; i < childrenPerNode; i++) {
                int childIndex = firstChildIndex + i;
                if (childIndex < size && heap.get(childIndex) > heap.get(maxIndex)) {
                    maxIndex = childIndex;
                }
            }

            if (maxIndex != index) {
                Collections.swap(heap, index, maxIndex);
                index = maxIndex;
            } else {
                break;
            }
        }
    }

    // Utility: Print heap (for debugging)
    public void printHeap() {
        System.out.println(heap);
    }

    // Main method for simple test
    public static void main(String[] args) {
        PowerOfTwoMaxHeap heap = new PowerOfTwoMaxHeap(2); // 2^2 = 4 children
        int[] values = {20, 15, 30, 10, 8, 25, 40, 5};

        for (int val : values) {
            heap.insert(val);
        }

        heap.printHeap(); // Should show max heap structure

        System.out.println("Max popped: " + heap.popMax());
        heap.printHeap(); // New max at root
    }
}
