package com.leetcode.vadim.problem.top;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    // https://leetcode.com/problems/add-two-numbers/
    /*public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        ListNode temp = l1, temp1 = l2, temp2 = res;
        int t, ost = 0, s1 = 0, s2 = 0;
        while (temp != null || temp1 != null) {
            if (temp == null) s1 = 0;
            else s1 = temp.val;
            if (temp1 == null) s2 = 0;
            else s2 = temp1.val;
            t = s1 + s2;
            if (t >= 10) {
                temp2.next = new ListNode(ost + t - 10);
                temp2 = temp2.next;
                ost = 1;
            } else {
                if (ost + t < 10) {
                    temp2.next = new ListNode(ost + t);
                    temp2 = temp2.next;
                    ost = 0;
                } else {
                    temp2.next = new ListNode(ost + t - 10);
                    temp2 = temp2.next;
                    ost = 1;
                }
            }
            if (temp != null) temp = temp.next;
            if (temp1 != null) temp1 = temp1.next;
        }

        if (ost > 0) temp2.next = new ListNode(1);
        res = res.next;
        return res;
    }*/

    // 3. Longest Substring Without Repeating Characters
    //https://leetcode.com/problems/longest-substring-without-repeating-characters/
    public int lengthOfLongestSubstring(String s) {
        char[] temp = new char[s.length()];
        int l = 0, k = 0, ii = 1;
        for (int i = 0; i < s.length(); i++) {
            if (k == 0) k++;
            for (int j = 0; j < l; j++) {
                if (s.charAt(i) == temp[j]) {
                    for (int z = 0; z < l; z++) temp[z] = ' ';
                    if (k < l) k = l;
                    l = 0;
                    i = ii;
                    ii++;
                    break;
                }

            }
            temp[l] = s.charAt(i);
            l++;
        }
        if (k < l) k = l;
        return k;
    }

    // 5. Longest Palindromic Substring
    // https://leetcode.com/problems/longest-palindromic-substring/
    public String longestPalindrome(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    // 8. String to Integer (atoi)
    // https://leetcode.com/problems/string-to-integer-atoi/
    public int myAtoi(String str) {
        int index = 0, sign = 1, total = 0;

        str = str.trim();
        //1. Empty string
        if (str.length() == 0) return 0;

        //2. Remove Spaces
        while (str.charAt(index) == ' ')
            index++;

        //3. Handle signs
        if (str.charAt(index) == '+' || str.charAt(index) == '-') {
            sign = str.charAt(index) == '+' ? 1 : -1;
            index++;
        }

        //4. Convert number and avoid overflow
        while (index < str.length()) {
            int digit = str.charAt(index) - '0';
            if (digit < 0 || digit > 9) break;

            //check if total will be overflow after 10 times and add digit
            if (Integer.MAX_VALUE / 10 < total || Integer.MAX_VALUE / 10 == total && Integer.MAX_VALUE % 10 < digit)
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            total = 10 * total + digit;
            index++;
        }
        return total * sign;
    }


    // 11. Container With Most Water
    // https://leetcode.com/problems/container-with-most-water/
    public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }

    // 15. 3Sum
    // https://leetcode.com/problems/3sum/
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        if (nums.length == 0) return new ArrayList<>(res);
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) res.add(Arrays.asList(nums[i], nums[j++], nums[k--]));
                else if (sum > 0) k--;
                else if (sum < 0) j++;
            }

        }
        return new ArrayList<>(res);
    }


    // 17. Letter Combinations of a Phone Number
    // https://leetcode.com/problems/letter-combinations-of-a-phone-number/
    public List<String> letterCombinations(String digits) {
        char[][] arr = new char[8][4];
        arr[0][0] = 'a';
        arr[0][1] = 'b';
        arr[0][2] = 'c';                    // 2
        arr[1][0] = 'd';
        arr[1][1] = 'e';
        arr[1][2] = 'f';                    // 3
        arr[2][0] = 'g';
        arr[2][1] = 'h';
        arr[2][2] = 'i';                    // 4
        arr[3][0] = 'j';
        arr[3][1] = 'k';
        arr[3][2] = 'l';                    // 5
        arr[4][0] = 'm';
        arr[4][1] = 'n';
        arr[4][2] = 'o';                    // 6
        arr[5][0] = 'p';
        arr[5][1] = 'q';
        arr[5][2] = 'r';
        arr[5][3] = 's';    // 7
        arr[6][0] = 't';
        arr[6][1] = 'u';
        arr[6][2] = 'v';                    // 8
        arr[7][0] = 'w';
        arr[7][1] = 'x';
        arr[7][2] = 'y';
        arr[7][3] = 'z';    // 9

        List<String> list = new ArrayList<>();
        if (digits.equals("")) {
            return list;
        }
        gen(arr, 0, digits, list, "");
        return list;
    }

    private static void gen(final char[][] arr, int i, final String digits, List<String> list, String curr) {
        if (curr.length() == digits.length()) {
            list.add(curr);
            return;
        }
        int ind = Character.getNumericValue(digits.charAt(i)) - 2;
        for (int k = 0; k < arr[ind].length; k++) {
            if (arr[ind][k] == '\u0000') continue;
            gen(arr, i + 1, digits, list, curr + arr[ind][k]);
        }
    }


    //

    // 19..73 todo: add

    //


    // 75. Sort Colors
    // https://leetcode.com/problems/sort-colors/
    public static void sortColors(int[] nums) {
        int n = nums.length;

        if (n <= 1) {
            return;
        }

        int second = n - 1, zero = 0;
        for (int i = 0; i <= second; i++) {
            while (nums[i] == 2 && i < second) {
                nums[i] = nums[i] + nums[second];
                nums[second] = nums[i] - nums[second];
                nums[i] = nums[i] - nums[second];
                second--;
            }
            while (nums[i] == 0 && i > zero) {
                //swap(nums[i], nums[zero++]);
                nums[i] = nums[i] + nums[zero];
                nums[zero] = nums[i] - nums[zero];
                nums[i] = nums[i] - nums[zero];
                zero++;
            }
        }
    }

    // 78. Subsets
    // https://leetcode.com/problems/subsets/
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        gen(new ArrayList<>(), res, nums, 0);
        return res;
    }

    public static void gen(List<Integer> curr, List<List<Integer>> res, int[] nums, int k) {
        res.add(new ArrayList<>(curr));
        for (int i = k; i < nums.length; i++) {
            curr.add(nums[i]);
            gen(curr, res, nums, i + 1);
            curr.remove(curr.size() - 1);
        }

    }


    // 79. Word Search
    // https://leetcode.com/problems/word-search/
    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (existGen(board, new int[board.length][board[i].length], word, 0, i, j))
                    return true;
            }
        }
        return false;

    }

    private static boolean existGen(char[][] board, int[][] flag, String word, int w, int i, int j) {
        if (flag[i][j] == 1) return false;
        if (w >= word.length() || board[i][j] != word.charAt(w)) return false;
        if (board[i][j] == word.charAt(w) && w == word.length() - 1)
            return true;


        int[][] newFlag = new int[flag.length][flag[i].length];
        for (int k = 0; k < flag.length; k++) newFlag[k] = Arrays.copyOf(flag[k], flag[k].length);
        newFlag[i][j] = 1;

        if (j + 1 < board[i].length && existGen(board, newFlag, word, w + 1, i, j + 1)) return true;
        if (j - 1 >= 0 && existGen(board, newFlag, word, w + 1, i, j - 1)) return true;
        if (i + 1 < board.length && existGen(board, newFlag, word, w + 1, i + 1, j)) return true;
        if (i - 1 >= 0 && existGen(board, newFlag, word, w + 1, i - 1, j)) return true;

        return false;
    }

    public boolean existSecondSolution(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (exist(board, y, x, w, 0)) return true;
            }
        }
        return false;
    }

    private boolean exist(char[][] board, int y, int x, char[] word, int i) {
        if (i == word.length) return true;
        if (y < 0 || x < 0 || y == board.length || x == board[y].length) return false;
        if (board[y][x] != word[i]) return false;
        board[y][x] ^= 256;
        boolean exist = exist(board, y, x + 1, word, i + 1)
                || exist(board, y, x - 1, word, i + 1)
                || exist(board, y + 1, x, word, i + 1)
                || exist(board, y - 1, x, word, i + 1);
        board[y][x] ^= 256;
        return exist;
    }


    // 91. Decode Ways
    // https://leetcode.com/problems/decode-ways/
    public static int numDecodings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for(int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i-1, i));
            int second = Integer.valueOf(s.substring(i-2, i));
            if(first >= 1 && first <= 9) {
                dp[i] += dp[i-1];
            }
            if(second >= 10 && second <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }


    public static void main(String[] args) {
        System.out.println(numDecodings("12120"));

        //subsets(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0});
        /*System.out.println(exist(new char[][]{
                {'F', 'Y', 'C', 'E', 'N', 'R', 'D'},
                {'K', 'L', 'N', 'F', 'I', 'N', 'U'},
                {'A', 'A', 'A', 'R', 'A', 'H', 'R'},
                {'N', 'D', 'K', 'L', 'P', 'N', 'E'},
                {'A', 'L', 'A', 'N', 'S', 'A', 'P'},
                {'O', 'O', 'G', 'O', 'T', 'P', 'N'},
                {'H', 'P', 'O', 'L', 'A', 'N', 'O'}}, "poland"));*/


        //System.out.println(exist(new char[][]{{'a', 'a', 'a', 'a'}, {'a', 'a', 'a', 'a'}, {'a', 'a', 'a', 'a'}}, "aaaaaaaaaaaaa"));
    }


}
