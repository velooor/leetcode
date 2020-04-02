package com.leetcode.vadim.problem.challenge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        } while(slow != fast);
        if (slow == 1) return true;
        else return false;
    }

    public static int digitSquareSum(int n) {
        int sum = 0, tmp;
        while (n!=0) {
            tmp = n % 10;
            sum += tmp * tmp;
            n /= 10;
        }
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(isHappy1(19));
        //System.out.println(singleNumber(new int[]{2, 2, 1}));
    }
}
