package com.leetcode.vadim.problem.random;

/**
 * Created by Linaelias Velore on 27.09.2016.
 */
public class LongestPalindromicString {
    public static String longestPalindrome1(String s) {
        String temp, max = new String();
        int k  = 1, max_l = 1;

        for(int i = 0; i < s.length(); i++)
        {
            k = 1;
            while(i+k <= s.length())
            {
                temp = s.substring(i, i+k); k++;
                if (isPalindrom(temp))
                    if (temp.length() >= max_l)
                    {
                        max_l = temp.length();
                        max = new String(temp);
                    }
            }
        }
        return max;
    }
    static boolean isPalindrom(String s)
    {
        boolean result = true;
        for(int i = 0; i < s.length()/2; i++)
            if(s.charAt(i) != s.charAt(s.length()-1-i)) result = false;
        return result;
    }
    static String searching(String s, int i)
    {
        if ((i == 0) || (i == s.length()-1)){

            return "" + s.charAt(i);
        }
        int k = 1;
        String temp, temp_prev = new String(""+s.charAt(i));
        while((i + k) < s.length() && (i-k) >= 0)
        {
            temp = new String(s.substring(i-k, i+k+1));
            if(!isPalindrom(temp)) return temp_prev;

            temp_prev = new String(temp);
            k++;
        }
        return temp_prev;
    }
    static String searching(String s, int i, int j)
    {
        if ((i == 0) || (j == s.length()-1)){

            return isPalindrom("" + s.charAt(i) + s.charAt(j)) ? "" + s.charAt(i) + s.charAt(j) : "" + s.charAt(i);
        }
        int k = 0;
        String temp, temp_prev = new String(""+s.charAt(i)+s.charAt(j));
        while((j + k) < s.length() && (i-k) >= 0)
        {
            temp = s.substring(i-k, j+k+1);
            if(!isPalindrom(temp)) return temp_prev;

            temp_prev = new String(temp);
            k++;
        }
        return temp_prev;
    }

    public static String longestPalindrome(String s)
    {
        String temp = new String(), max = new String();
        int  max_l = 1;
        if (s.length() == 1) return s;
        for(int i = 0; i < s.length()-1; i++)
        {
            temp = searching(s, i);
            if(temp.length() >= max_l)
            {
                max_l = temp.length();
                max = temp;
            }
            temp = searching(s, i, i+1);
            if(temp.length() >= max_l)
            {
                max_l = temp.length();
                max = temp;
            }
        }
        return max;
    }
    public static void run()
    {
        System.out.println(longestPalindrome("ccd"));
    }
}
