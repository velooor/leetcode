package com.leetcode.vadim.problem.challenge;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class ChallengeSolution {

    // 136. Single Number
    // https://leetcode.com/problems/single-number/
    public static int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, (o, n) -> o += n);
        }
        return map.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .mapToInt(Map.Entry::getKey)
                .findAny().orElse(0);
    }

    public int singleNumber1(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }


    // 202. Happy Number
    // https://leetcode.com/problems/happy-number/

    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (true) {
            int r = 0;
            while (n != 0) {
                r += Math.pow(n % 10, 2);
                n /= 10;
            }
            if (r == 1) return true;
            if (!set.add(r)) return false;
            n = r;
        }
    }

    // # Floyd Cycle detection algorithm
    public static boolean isHappy1(int n) {
        int slow, fast;
        slow = fast = n;
        do {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(fast);
            fast = digitSquareSum(fast);
        } while (slow != fast);
        if (slow == 1) return true;
        else return false;
    }

    public static int digitSquareSum(int n) {
        int sum = 0, tmp;
        while (n != 0) {
            tmp = n % 10;
            sum += tmp * tmp;
            n /= 10;
        }
        return sum;
    }


    // 53. Maximum Subarray
    // https://leetcode.com/problems/maximum-subarray/
    public static int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
        dp[0] = nums[0];
        int max = dp[0];

        for (int i = 1; i < n; i++) {
            dp[i] = nums[i] + (Math.max(dp[i - 1], 0));
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    // 283. Move Zeroes
    // https://leetcode.com/problems/move-zeroes/
    public static void moveZeroes(int[] nums) {
        /*int z = 0, nz = 0;
        while (nz < nums.length - 1) {
            while (z < nums.length-1) if (nums[z] == 0) break; else z++;
            while (nz < nums.length-1) if (nums[nz] != 0) break; else nz++;
            if(z > nz) { nz++; continue; }
            nums[z] = nums[nz]; nums[nz] = 0;
        }*/


        if (nums == null || nums.length == 0) return;

        int insertPos = 0;
        for (int num: nums) {
            if (num != 0) nums[insertPos++] = num;
        }

        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }


    public static void main(String[] args) {
        int[] res;

        res = new int[]{1, 0, 2};
        moveZeroes(res);
        System.out.println(Arrays.toString(res));

        res = new int[]{0, 1, 0};
        moveZeroes(res);
        System.out.println(Arrays.toString(res));

        res = new int[]{1, 0, 0};
        moveZeroes(res);
        System.out.println(Arrays.toString(res));

        res = new int[]{0, 1};
        moveZeroes(res);
        System.out.println(Arrays.toString(res));

        res = new int[]{1, 0};
        moveZeroes(res);
        System.out.println(Arrays.toString(res));

        res = new int[]{1, 2, 3};
        moveZeroes(res);
        System.out.println(Arrays.toString(res));

        res = new int[]{0, 1, 0, 3, 12};
        moveZeroes(res);
        System.out.println(Arrays.toString(res));

        res = new int[]{0};
        moveZeroes(res);
        System.out.println(Arrays.toString(res));

        res = new int[]{1};
        moveZeroes(res);
        System.out.println(Arrays.toString(res));


        res = new int[]{0, 0, 0};
        moveZeroes(res);
        System.out.println(Arrays.toString(res));

        res = new int[]{0, 0, 1, 0, 0, 2, 3};
        moveZeroes(res);
        System.out.println(Arrays.toString(res));

        //System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        //System.out.println(isHappy1(19));
        //System.out.println(singleNumber(new int[]{2, 2, 1}));
    }
}
