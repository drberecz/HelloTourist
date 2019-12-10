package hu.ps.ht.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PoJoJsonConverterUtil {

    public static <T> Set<T> convertArrayToSet(T array[]) {

        Set<T> set = new HashSet<>(Arrays.asList(array));
        return set;
    }

}
