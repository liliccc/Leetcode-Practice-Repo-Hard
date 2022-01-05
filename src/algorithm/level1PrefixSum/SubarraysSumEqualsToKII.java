package algorithm.level1PrefixSum;

import java.util.HashMap;
import java.util.Map;

public class SubarraysSumEqualsToKII {
    /**
     * @param nums: a list of integer
     * @param k: an integer
     * @return: return an integer, denote the minimum length of continuous subarrays whose sum equals to k
     */
    public int subarraySumEqualsKII(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Map<Long, Integer> sumToIndex = new HashMap<>();
        long prefixSum = 0;
        sumToIndex.put(prefixSum, 0);
        int minimumSize = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (sumToIndex.containsKey(prefixSum - k)) {
                int index = sumToIndex.get(prefixSum - k);
                int len = i + 1 - index;
                minimumSize = Math.min(minimumSize, len);
            }
            sumToIndex.put(prefixSum, i + 1);
        }
        return (minimumSize == Integer.MAX_VALUE) ? -1 : minimumSize;
    }
}
