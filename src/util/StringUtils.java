package util;

/**
 * Created by Zed on 4/10/2015.
 */
public class StringUtils {
    public static boolean isAnyNullOrEmpty(String... strs) {
        for (String str : strs) {
            if (isNullOrEmpty(str)) return true;
        }

        return false;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
