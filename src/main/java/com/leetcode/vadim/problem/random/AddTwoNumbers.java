package com.leetcode.vadim.problem.random;

public class AddTwoNumbers {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
     }

    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {

        long n1 = 0, n2 = 0, k = 0;
        ListNode temp = l1;
        while(temp != null) {
            n1 += temp.val * Math.pow(10, k);
            k++; temp = temp.next;
        }
        temp = l2; k = 0;
        while(temp != null) {
            n2 += temp.val * Math.pow(10, k);
            k++; temp = temp.next;
        }

        long sum = n1 + n2;
        int digit;
        ListNode res = new ListNode(0);
        temp = res;
        if(sum >= 10) {
            digit = (int) (sum % 10);
            temp.val = digit;
            sum /= 10;
            while (sum >= 10) {
                digit = (int)(sum % 10);
                temp.next = new ListNode(digit);
                sum /= 10;
                temp = temp.next;
            }
            temp.next = new ListNode((int)sum);
        }
        else temp.val = (int)sum;

        return res;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode res = new ListNode(-1);
        ListNode temp = l1, temp1 = l2, temp2 = res;
        int t, ost = 0, s1, s2;
        while(temp != null || temp1 != null) {
            if(temp == null) s1 = 0;  else s1 = temp.val;
            if(temp1 == null) s2 = 0; else s2 = temp1.val;
            t = s1 + s2;
            if(t >= 10){
                temp2.next = new ListNode(ost + t - 10);
                temp2 = temp2.next;
                ost = 1;
            }
            else{
                if (ost + t < 10)
                {
                    temp2.next = new ListNode(ost + t);
                    temp2 = temp2.next;
                    ost = 0;
                }
                else
                {
                    temp2.next = new ListNode(ost + t - 10);
                    temp2 = temp2.next;
                    ost = 1;
                }
            }
            if(temp != null)temp = temp.next;
            if(temp1 != null)temp1 = temp1.next;
        }

        if(ost > 0) temp2.next = new ListNode(1);
        res = res.next;
        return res;

    }
    public static void run(){
        ListNode a1 = new ListNode(9);
        //a1.next = new ListNode(4);
        //a1.next.next = new ListNode(3);

        ListNode a2 = new ListNode(1);
        a2.next = new ListNode(9);
        a2.next.next = new ListNode(9);
        a2.next.next.next = new ListNode(9);
        a2.next.next.next.next = new ListNode(9);
        /*a2.next.next.next.next.next = new ListNode(9);
        a2.next.next.next.next.next.next = new ListNode(9);
        a2.next.next.next.next.next.next.next = new ListNode(9);
        a2.next.next.next.next.next.next.next.next = new ListNode(9);
        a2.next.next.next.next.next.next.next.next.next = new ListNode(9);
*/
        addTwoNumbers(a1, a2);
    }
}
