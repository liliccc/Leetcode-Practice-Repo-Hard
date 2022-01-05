package algorithm.level1PrefuxSum;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class ShortestSubarraywithSumatLeastK {
    /**
     * @param A: the array
     * @param K: sum
     * @return: the length
     */
     // prefix sum is not the best solution
    public int shortestSubarray(int[] A, int K) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int[] prefixSums = getPrefixSum(A);
        // binary search on possible answers to find the correct answer
        // error before: start = 0
        int start = 1, end = A.length;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isValid(prefixSums, K, mid)) {
                end = mid;
            }
            else {
                start = mid;
            }
        }
        if (isValid(prefixSums, K, start)) {
            return start;
        }
        if (isValid(prefixSums, K, end)) {
            return end;
        }
        return -1;
    }

    private boolean isValid(int[] prefixSum, int k, int arrayLength) {
        Heap minheap = new Heap();
        for (int end = 0; end < prefixSum.length; end++) {
            int index = end - arrayLength - 1;
            if (index >= 0) {
                minheap.delete(index);
            }
            if (!minheap.isEmpty() && prefixSum[end] - minheap.top().value >= k) {
                return true;
            }
            minheap.push(end, prefixSum[end]);
        }
        return false;
    }

    private int[] getPrefixSum(int[] nums) {
        int size = nums.length;
        int[] prefixSums = new int[size + 1];
        prefixSums[0] = 0;
        for (int i = 0; i < size; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i];
        }
        return prefixSums;
    }
}

class ValueToIndex {
    int value;
    int index;
    public ValueToIndex(int value, int index) {
        this.value = value;
        this.index = index;
    }
}

// using heap data structure to make it faster
class Heap {
    private Queue<ValueToIndex> minheap;
    private Set<Integer> deleteSet;
    public Heap() {
        minheap = new PriorityQueue<>((p1, p2) -> (p1.value - p2.value));
        deleteSet = new HashSet<>();
    }
    // O(log(n))
    public void push(int value, int index) {
        minheap.add(new ValueToIndex(value, index));
    }

    private void lazyDeletion() {
        // error before: if
        while (minheap.size() != 0 && deleteSet.contains(minheap.peek().index)) {
            ValueToIndex root = minheap.poll();
            // O(1)
            deleteSet.remove(root.index);
        }
    }

    public ValueToIndex top() {
        lazyDeletion();
        return minheap.peek();
    }

    public void pop() {
        lazyDeletion();
        minheap.poll();
    }
    // soft delete
    public void delete(int index) {
        deleteSet.add(index);
    }

    public boolean isEmpty() {
        return minheap.size() == 0;
    }
}
