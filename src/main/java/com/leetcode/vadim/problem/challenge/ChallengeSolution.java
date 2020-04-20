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

    // 238. Product of Array Except Self
    // https://leetcode.com/problems/product-of-array-except-self/solution/
    public int[] productExceptSelf(int[] nums) {
        // The length of the input array
        int length = nums.length;

        // The left and right arrays as described in the algorithm
        int[] L = new int[length];
        int[] R = new int[length];

        // Final answer array to be returned
        int[] answer = new int[length];

        // L[i] contains the product of all the elements to the left
        // Note: for the element at index '0', there are no elements to the left,
        // so L[0] would be 1
        L[0] = 1;
        for (int i = 1; i < length; i++) {

            // L[i - 1] already contains the product of elements to the left of 'i - 1'
            // Simply multiplying it with nums[i - 1] would give the product of all
            // elements to the left of index 'i'
            L[i] = nums[i - 1] * L[i - 1];
        }

        // R[i] contains the product of all the elements to the right
        // Note: for the element at index 'length - 1', there are no elements to the right,
        // so the R[length - 1] would be 1
        R[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {

            // R[i + 1] already contains the product of elements to the right of 'i + 1'
            // Simply multiplying it with nums[i + 1] would give the product of all
            // elements to the right of index 'i'
            R[i] = nums[i + 1] * R[i + 1];
        }

        // Constructing the answer array
        for (int i = 0; i < length; i++) {
            // For the first element, R[i] would be product except self
            // For the last element of the array, product except self would be L[i]
            // Else, multiple product of all elements to the left and to the right
            answer[i] = L[i] * R[i];
        }

        return answer;
    }


    // 678. Valid Parenthesis String
    // https://leetcode.com/problems/valid-parenthesis-string/submissions/
    public boolean checkValidString(String s) {
        int n = s.length();
        if (n == 0) return true;
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '*') dp[i][i] = true;
            if (i < n-1 &&
                    (s.charAt(i) == '(' || s.charAt(i) == '*') &&
                    (s.charAt(i+1) == ')' || s.charAt(i+1) == '*')) {
                dp[i][i+1] = true;
            }
        }

        for (int size = 2; size < n; size++) {
            for (int i = 0; i + size < n; i++) {
                if (s.charAt(i) == '*' && dp[i+1][i+size] == true) {
                    dp[i][i+size] = true;
                } else if (s.charAt(i) == '(' || s.charAt(i) == '*') {
                    for (int k = i+1; k <= i+size; k++) {
                        if ((s.charAt(k) == ')' || s.charAt(k) == '*') &&
                                (k == i+1 || dp[i+1][k-1]) &&
                                (k == i+size || dp[k+1][i+size])) {
                            dp[i][i+size] = true;
                        }
                    }
                }
            }
        }
        return dp[0][n-1];
    }



    // 200. Number of Islands
    // https://leetcode.com/problems/number-of-islands/
    private int n;
    private int m;

    public int numIslands(char[][] grid) {
        int count = 0;
        n = grid.length;
        if (n == 0) return 0;
        m = grid[0].length;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++)
                if (grid[i][j] == '1') {
                    DFSMarking(grid, i, j);
                    ++count;
                }
        }
        return count;
    }

    private void DFSMarking(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] != '1') return;
        grid[i][j] = '0';
        DFSMarking(grid, i + 1, j);
        DFSMarking(grid, i - 1, j);
        DFSMarking(grid, i, j + 1);
        DFSMarking(grid, i, j - 1);
    }


    // 33. Search in Rotated Sorted Array
    // https://leetcode.com/problems/search-in-rotated-sorted-array/
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end){
            int mid = (start + end) / 2;
            if (nums[mid] == target)
                return mid;

            if (nums[start] <= nums[mid]){
                if (target < nums[mid] && target >= nums[start])
                    end = mid - 1;
                else
                    start = mid + 1;
            }

            if (nums[mid] <= nums[end]){
                if (target > nums[mid] && target <= nums[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;
    }

    // 1008. Construct Binary Search Tree from Preorder Traversal
    // https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
    int i = 0;
    public TreeNode bstFromPreorder(int[] A) {
        return bstFromPreorder(A, Integer.MAX_VALUE);
    }

    public TreeNode bstFromPreorder(int[] A, int bound) {
        if (i == A.length || A[i] > bound) return null;
        TreeNode root = new TreeNode(A[i++]);
        root.left = bstFromPreorder(A, root.val);
        root.right = bstFromPreorder(A, bound);
        return root;
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
