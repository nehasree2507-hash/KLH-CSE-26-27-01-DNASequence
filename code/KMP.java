import java.util.*;

public class KMP {

    public static List<Integer> search(String text, String pat) {
        List<Integer> ans = new ArrayList<>();
        int[] lps = new int[pat.length()];

        for (int i = 1, j = 0; i < pat.length();) {
            if (pat.charAt(i) == pat.charAt(j))
                lps[i++] = ++j;
            else if (j > 0)
                j = lps[j - 1];
            else
                i++;
        }

        for (int i = 0, j = 0; i < text.length();) {
            if (text.charAt(i) == pat.charAt(j)) {
                i++;
                j++;

                if (j == pat.length()) {
                    ans.add(i - j + 1);
                    j = lps[j - 1];
                }
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }

        return ans;
    }
}