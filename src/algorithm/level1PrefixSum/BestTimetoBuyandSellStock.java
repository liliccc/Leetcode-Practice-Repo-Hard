package algorithm.level1PrefixSum;

public class BestTimetoBuyandSellStock {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    // greedy algorithm - O(n) - which one is the best, choose that one immediately
    // another solution is dp, which is slower
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int profit = 0, minPrice = Integer.MAX_VALUE;
        for (int price : prices) {
            // save the prefix smallest price
            minPrice = Math.min(price, minPrice);
            profit = Math.max(price - minPrice, profit);
        }
        return profit;
    }
}
