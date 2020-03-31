package com.leetcode.vadim.problem.random;

/**
 * Created by Linaelias Velore on 25.09.2016.
 */
public class MedianOfTwoSortedArrays {
    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        double[] nums3 = new double[4];
        int i = 0;
        if(nums2.length == 0)
            return nums1.length % 2 == 0 ?  (double)(nums1[nums1.length/2-1] + nums1[nums1.length/2])/2 :  nums1[nums1.length/2];

        if(nums1.length == 0)
            return nums2.length % 2 == 0 ?  (double)(nums2[nums2.length/2-1] + nums2[nums2.length/2])/2 :  nums2[nums2.length/2];

        if (nums1.length % 2 == 0)
        {
            nums3[i] = nums1[nums1.length/2-1]; i++;
            nums3[i] = nums1[nums1.length/2 ]; i++;
        }
        else
        {
            nums3[i] = nums1[nums1.length/2]; i++;
        }
        if (nums2.length % 2 == 0)
        {
            nums3[i] = nums2[nums2.length/2-1]; i++;
            nums3[i] = nums2[nums2.length/2]; i++;
        }
        else
        {
            nums3[i] = nums2[nums2.length/2]; i++;
        }

        if(i == 2) return (nums3[0] + nums3[1])/2;

        boolean shake = true; double temp;
        while(shake)
        {
            shake = false;
            for(int z = 0; z < nums3.length - 1; z++)
            {
                if(nums3[z] > nums3[z+1])
                {
                    temp = nums3[z]; nums3[z] = nums3[z+1]; nums3[z+1] = temp;
                    shake = true;
                }
            }
        }
        if(i == 2) return (nums3[0] + nums3[1])/2;
        if(i == 3) return nums3[2];
        if(i == 4) return (nums3[1] + nums3[2])/2;

        return 0;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2)
    {
        if(nums2.length == 0)
            return nums1.length % 2 == 0 ? (double)(nums1[nums1.length/2-1] + nums1[nums1.length/2])/2 : nums1[nums1.length/2];

        if(nums1.length == 0)
            return nums2.length % 2 == 0 ? (double)(nums2[nums2.length/2-1] + nums2[nums2.length/2])/2 : nums2[nums2.length/2];

        /*int	counter1 = 0,
                counter2 = 0,
                counter3 = 0;

        for (int counter = 0; counter < (nums1.length + nums2.length) / 2; counter++)
        {
            if (nums1[counter1] <= nums2[counter2]) {
                counter3 = nums1[counter1];
                counter1++;
            }
            else {
                counter3 = nums2[counter2];
                counter2++;
            }
        }*/

        return temp(nums1, nums2);
    }

    public static double temp(int[] arr1, int[] arr2)
    {
        int counter1 = 0;
        int counter2 = 0;
        int counter3 = 0;
        int counter4 = 0;
        double counter5 = (arr1.length + arr2.length) / 2;
        double endCounter = 0;
        double totalCounter = 0;
        for(; totalCounter < Math.round(counter5); totalCounter++){
            counter4 = counter3;
            if(arr1[counter1] <= arr2[counter2]) {
                counter3 = arr1[counter1];
                counter1++;
            } else {
                counter3 = arr2[counter2];
                counter2++;
            };
        };
        if(counter5 != Math.round(counter5)){
            endCounter = (counter3 + counter4) / 2;
        } else {
            endCounter = counter3;
        };
        return endCounter;
    }

    public static void run()
    {
        int[] n1 = {1,2}, n2 = {3,4,5};
        System.out.println(findMedianSortedArrays(n1,n2));
    }
}
