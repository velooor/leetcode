package com.leetcode.vadim.problem.challenge;

import com.leetcode.vadim.problem.top.Solution;

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
        for (int num : nums) {
            if (num != 0) nums[insertPos++] = num;
        }

        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }


    // 122. Best Time to Buy and Sell Stock II
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int n = prices.length;

        boolean bought = false;
        int bi = 0, profit = 0;
        for (int i = 1; i < n; i++) {
            if (!bought && prices[i] > prices[i - 1]) {
                bought = true;
                bi = i - 1;
            } else if (bought && prices[i] < prices[i - 1]) {
                bought = false;
                profit += prices[i - 1] - prices[bi];
            }
        }
        if (bought) profit += prices[n - 1] - prices[bi];

        return profit;

        /*
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
         */
    }


    // 49. Group Anagrams
    // https://leetcode.com/problems/group-anagrams/
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> ans = new HashMap<>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!ans.containsKey(key)) ans.put(key, new ArrayList<>());
            ans.get(key).add(s);
        }
        return new ArrayList<>(ans.values());
    }

    // Counting Elements
    public int countElements(int[] arr) {
        int res = 0, currDup = 1;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            int t = arr[i + 1] - arr[i];
            if (t == 0) {
                currDup++;
            }

            if (t == 1) {
                res += currDup;
                currDup = 1;
            }
            if (t > 1) {
                currDup = 1;
            }
        }
        return res;
    }

    // todo add


    // 543. Diameter of Binary Tree
    // https://leetcode.com/problems/diameter-of-binary-tree/solution/
    int ans;

    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }

    public int depth(TreeNode node) {
        if (node == null) return 0;
        int L = depth(node.left);
        int R = depth(node.right);
        ans = Math.max(ans, L + R + 1);
        return Math.max(L, R) + 1;
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    // Perform String Shifts
    public String stringShift(String s, int[][] shift) {
        if (s.length() <= 1) return s;
        int left = 0, right = 0;
        for (int[] ints : shift) {
            if (ints[0] == 0) left+=ints[1];
            else right+=ints[1];
        }

        if (left - right > 0) {
            for (int i = 0; i < left - right; i++) {
                s = s.substring(1) + s.charAt(0);
            }
        } else {
            for (int i = 0; i < right - left; i++) {
                s = s.charAt(s.length() - 1) + s.substring(0, s.length() - 1);
            }
        }
        return s;
    }


    public static void main(String[] args) {
        System.out.println(7 == maxProfit(new int[]{7, 1, 5, 3, 6, 4})); // 7
        System.out.println(3 == maxProfit(new int[]{1, 2, 3, 4})); // 3
        System.out.println(0 == maxProfit(new int[]{4, 3, 2, 1})); // 0
        System.out.println(5 == maxProfit(new int[]{0, 5, 0})); // 5
        System.out.println(5 == maxProfit(new int[]{5, 0, 5})); // 5
        System.out.println(99 == maxProfit(new int[]{11, 1, 1, 100})); // 99
        System.out.println(0 == maxProfit(new int[]{1})); // 0
        System.out.println(0 == maxProfit(new int[]{})); // 0
        System.out.println(0 == maxProfit(null)); // 0


        /*int[] res;

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
        System.out.println(Arrays.toString(res));*/

        //System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        //System.out.println(isHappy1(19));
        //System.out.println(singleNumber(new int[]{2, 2, 1}));
    }
}
