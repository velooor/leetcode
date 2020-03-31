package com.leetcode.vadim.problem.random;

import java.io.*;
import java.util.Scanner;

public class TwoSum
{
    public static int[] twoSum(int[] nums, int target)
    {
        int[] res = new int[2];
        for(int i = 0; i < nums.length - 1; i++)
            for(int j = i + 1 ; j < nums.length; j++)
                    if(nums[i] + nums[j] == target) { res[0] = i; res[1] = j; return res; }

        return res;
    }

    public static void run(){
        double timeout = System.currentTimeMillis();       System.out.println("The program started\n");
        try
        {
            Scanner in      = new Scanner(new File("in.txt"));
            PrintWriter out = new PrintWriter(new FileWriter("out.txt"));

            int targ = in.nextInt();
            int nums[] = new int[in.nextInt()];
            for(int i = 0; in.hasNext(); i++) nums[i] = in.nextInt();
            int res[] = TwoSum.twoSum(nums, targ);
            System.out.println(res[0] + " " + res[1]);

            out.println(res[0]); out.println(res[1]);

            in.close(); out.close();
        }
        catch(FileNotFoundException q)  {   System.out.println("An FileNotFoundException was caught!");         }
        catch(IOException w)            {   System.err.println("An IOException was caught!");                   }
        System.out.println("\n\nThe program finished successfully! Time: " + ((System.currentTimeMillis() - timeout)/1000) + " second");
    }


}

