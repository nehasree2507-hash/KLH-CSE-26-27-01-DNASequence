import java.util.*;

public class RabinKarp {

    static final long BASE = 31;
    static final long MOD = 1_000_000_007;

    public static List<Integer> search(String text, String pat) {

        List<Integer> ans = new ArrayList<>();
        int n = text.length(), m = pat.length();

        if (m > n) return ans;

        long ph = 0, th = 0, power = 1;

        for (int i = 0; i < m; i++) {
            ph = (ph * BASE + pat.charAt(i)) % MOD;
            th = (th * BASE + text.charAt(i)) % MOD;
            if (i < m - 1)
                power = power * BASE % MOD;
        }

        for (int i = 0; i <= n - m; i++) {

            if (ph == th && text.regionMatches(i, pat, 0, m))
                ans.add(i + 1);

            if (i < n - m) {
                th = (th - text.charAt(i) * power % MOD + MOD) % MOD;
                th = (th * BASE + text.charAt(i + m)) % MOD;
            }
        }

        return ans;
    }
}