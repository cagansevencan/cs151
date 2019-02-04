
import java.util.*;

public class Encoding {

    public static Set<String> morseCodes(int m, int n) {
        String first = ".";
        String sec = "-";
        Set<String> result = new TreeSet<>();
        if (m == 0 & n == 0) {
            result.add("");
        } else if (m == 0) {
            String r = "";
            for (int i = 0; i < n; i++) {
                r += sec;
            }
            result.add(r);
        } else if (n == 0) {
            String r = "";
            for (int i = 0; i < m; i++) {
                r += first;
            }
            result.add(r);
        } else {
            Set<String> subset = morseCodes(m, n - 1);
            Set<String> subset2 = morseCodes(m - 1, n);

            for (String s1 : subset) {
                result.add(s1 + sec);
            }
            for (String s2 : subset2) {
                result.add(s2 + first);
            }

        }
        return result;
    }
}

