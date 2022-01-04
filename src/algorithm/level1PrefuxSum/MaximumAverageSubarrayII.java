package algorithm.level1PrefuxSum;

public class MaximumAverageSubarrayII {
    /**
     * @param nums: an array with positive and negative numbers
     * @param k: an integer
     * @return: the maximum average
     */
    public double maxAverage(int[] nums, int k) {
        if (nums == null) {
            return 0;
        }
        double start = Double.MAX_VALUE, end = Double.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            start = Math.min(start, nums[i]);
            end = Math.max(end, nums[i]);
        }
        while (start + (1e-5) < end) {
            // avg is between start and end, use binary search to find (binary search on answers set)
            double mid = start + (end - start) / 2;
            if (canFind(nums, k, mid)) {
                // mid <= avg, make it bigger
                start = mid;
            }
            else {
                // mid > avg, make it smaller
                end = mid;
            }
        }
        return start;
    }

    private boolean canFind(int[] nums, int k, double avg) {
        double minLeftSums = 0;
        double[] prefixSumsAvg = new double[nums.length + 1];
        prefixSumsAvg[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSumsAvg[i + 1] = prefixSumsAvg[i] + nums[i] - avg;
        }
        for (int i = k; i < prefixSumsAvg.length; i++) {
            // find a subarray which has avg >= mid and len >= k
            if (prefixSumsAvg[i] - minLeftSums >= 0) {
                return true;
            }
            minLeftSums = Math.min(minLeftSums, prefixSumsAvg[i - k + 1]);
        }
        return false;
    }
}
