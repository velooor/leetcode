package com.leetcode.vadim.problem.challenge;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
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


    // 94. Binary Tree Inorder Traversal
    // https://leetcode.com/problems/binary-tree-inorder-traversal/

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

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        trav(root, result);
        return result;
    }

    private static void trav(TreeNode root, List<Integer> result) {
        if (root == null) return;
        if (root.left != null) trav(root.left, result);
        result.add(root.val);
        if (root.right != null) trav(root.right, result);
    }

    public static List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) return result;

        while (true) {
            if (root.left != null) {
                stack.push(root);
                root = root.left;
                continue;
            }
            if (root.val != Integer.MIN_VALUE) {
                result.add(root.val);
                root.val = Integer.MIN_VALUE;
            }
            if (root.right != null) {
                stack.push(root);
                root = root.right;
            } else {
                if (root.left == null && stack.isEmpty()) return result;
                if (!stack.isEmpty()) {
                    TreeNode parent = stack.pop();
                    if (root == parent.right) parent.right = null;
                    if (root == parent.left) parent.left = null;
                    root = parent;
                }
            }
        }
    }

    public List < Integer > inorderTraversal2(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        Stack < TreeNode > stack = new Stack < > ();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(isHappy1(19));
        //System.out.println(singleNumber(new int[]{2, 2, 1}));
    }
}
