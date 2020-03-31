package com.leetcode.vadim.problem.random;

public class Atoi {
    int myAtoi(String str)
    {
        int res = 0, one = 1, j = 0;
        for(int i = 0; i < str.length(); i++)
        {
            if ((int)(str.charAt(i)) >= 1 && (int)(str.charAt(i)) <= 9)
            {
                if(i != 0 && str.charAt(i-1) == '-') one  = -1;
                for(j = 0; j < str.length(); j++)
                    if ((int)(str.charAt(i)) > 9) break;
                String num = str.substring(i, j+1);
                res = Integer.valueOf(num)*one;
            }
        }
        return res;
    }
}
