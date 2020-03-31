package com.leetcode.vadim.problem.random;

public class LongestSubstring {


    public static int lengthOfLongestSubstring(String s) {
        char[] temp = new char[s.length()]; int l = 0, k = 0, ii = 1;
        for(int i = 0; i < s.length(); i++)
        {
            if( k == 0) k++;
            for(int j = 0; j < l; j++)
            {
                if (s.charAt(i) == temp[j]){
                    for(int z = 0; z < l; z++) temp[z] = ' ';
                    if(k < l) k = l;
                    l = 0;
                    i = ii; ii++;
                    break;
                }

            }
            temp[l] = s.charAt(i);  l++;
        }
        if(k < l) k = l;
        return k;
    }

    public static void run()
    {
        System.out.println(lengthOfLongestSubstring("dvdf"));
    }
}
