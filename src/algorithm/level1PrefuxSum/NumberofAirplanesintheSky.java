package algorithm.level1PrefuxSum;

import java.util.List;

public class NumberofAirplanesintheSky {
    /**
     * @param airplanes: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) {
        if (airplanes == null || airplanes.size() == 0) {
            return 0;
        }
        // error before: new int[] must be large enough
        int[] nums = new int[11111];
        int theLastLandingTime = Integer.MIN_VALUE;
        for (int i = 0; i < airplanes.size(); i++) {
            nums[airplanes.get(i).start] += 1;
            nums[airplanes.get(i).end] -= 1;
            theLastLandingTime = Math.max(theLastLandingTime, airplanes.get(i).end);
        }
        // calculate the prefixsum till the last landing time
        int answer = Integer.MIN_VALUE;
        int[] prefixSums = new int[nums.length];
        prefixSums[0] = 0;
        for (int i = 0; i <= theLastLandingTime; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i];
            answer = Math.max(answer, prefixSums[i + 1]);
        }
        answer = Math.max(answer, prefixSums[0]);
        return answer;
    }
}

class Interval {
    int start, end;
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}