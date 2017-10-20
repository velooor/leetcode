package problem;

/**
 * Created by Lagarde on 29.09.2016.
 */
public class ZigZag {
    public static  String convert(String s, int numRows) {
        if(numRows == 1) return s;
        if(numRows > s.length()) return s;
        String res[] = new String[numRows]; for(int i = 0; i < numRows; i++) res[i] = "";
        int one = 1;
        for(int i = 0, k = 0; i < s.length(); i++)
        {
            res[k] += s.charAt(i);
            if((k+one) == numRows) one *= (-1);
            if((k+one) == -1)      one *= (-1);
            k += one;
        }
        String result = new String();
        for(int i = 0; i < numRows; i++)
            result += res[i];
        return result;
    }
    public static void run()
    {
        System.out.println(convert("a", 2));
    }
}
