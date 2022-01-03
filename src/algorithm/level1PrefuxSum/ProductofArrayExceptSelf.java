package algorithm.level1PrefuxSum;

public class ProductofArrayExceptSelf {
    /**
     * @param nums: an array of integers
     * @return: the product of all the elements of nums except nums[i].
     */
    // the answer is the previous elements of the point * the later elements of the point
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int[] results = new int[nums.length];
        int prefixProduct = 1, suffixProduct = 1;
        for (int i = 0; i < results.length; i++) {
            results[i] = 1;
        }
        for (int i = 0; i < results.length; i++) {
            results[i] *= prefixProduct;
            // prefixProduct = nums[0] * ... * nums[i-1]
            prefixProduct *= nums[i];
        }
        for (int i = results.length - 1; i >= 0; i--) {
            results[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }
        return results;
    }
}
