package com.leetcode.vadim;

import java.util.ArrayList;
import java.util.List;

public class Amazon {
    // Demo 1
    public static List<Integer> cellCompete(int[] states, int days) {
        int[] n = new int[states.length + 2];
        System.arraycopy(states, 0, n, 1, states.length);
        states = n;
        int[] curr = new int[states.length + 2];
        for (int i = 0; i < days; i++) {
            curr = new int[states.length];
            for (int j = 1; j <= states.length; j++) {
                if (j > 0 && j < states.length - 1) {
                    if (states[j - 1] == 1 ^ states[j + 1] == 1) curr[j] = 1;
                    if (states[j - 1] == 1 && states[j + 1] == 1) curr[j] = 0;
                }
            }
            states = curr;
        }
        List<Integer> intList = new ArrayList<Integer>(curr.length - 2);
        for (int i = 1; i < curr.length - 1; i++) {
            intList.add(curr[i]);
        }
        return intList;
    }


    // Demo 2
    public static int generalizedGCD(int num, int[] arr) {
        int res = arr[0];
        for (int i = 0; i < num; i++) {
            int curr = gcd(res, arr[i]);
            if (curr < res) res = curr;
        }
        return Math.max(res, 1);
    }

    public static int gcd(int a, int b) {
        if (b == 0) return Math.abs(a);
        return gcd(b, a % b);
    }


    // 21. Merge Two Sorted Lists
    // https://leetcode.com/problems/merge-two-sorted-lists/
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode curr;
        if (l1.val <= l2.val) { curr = new ListNode(l1.val); l1 = l1.next; }
        else { curr = new ListNode(l2.val); l2 = l2.next; }
        ListNode res = curr;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) { curr.next = new ListNode(l1.val); l1 = l1.next; }
            else { curr.next = new ListNode(l2.val); l2 = l2.next; }
            curr = curr.next;
        }

        while (l1 != null) {
            curr.next = new ListNode(l1.val);
            l1 = l1.next;
            curr = curr.next;
        }

        while (l2 != null) {
            curr.next = new ListNode(l2.val);
            l2 = l2.next;
            curr = curr.next;
        }

        return res;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {

        ListNode l1 = new ListNode(1); l1.next = new ListNode(2); l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1); l2.next = new ListNode(3); l2.next.next = new ListNode(4);
        mergeTwoLists(l1,l2);

        /*System.out.println(generalizedGCD(1, new int[]{4}));
        System.out.println(generalizedGCD(1, new int[]{1}));
        System.out.println(generalizedGCD(5, new int[]{8, 4, 10, 6, 2}));
        System.out.println(generalizedGCD(5, new int[]{2, 3, 4, 5, 6}));


        System.out.println(cellCompete(new int[]{1, 0, 0, 0, 0, 1, 0, 0}, 1));
        System.out.println(cellCompete(new int[]{1, 1, 1, 0, 1, 1, 1, 1}, 2));*/
    }
}
