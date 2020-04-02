package com.leetcode.vadim.problem.challenge;

import java.util.HashMap;
import java.util.Map;

public class Solution {

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



    //
    //

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{2, 2, 1}));
    }
}
