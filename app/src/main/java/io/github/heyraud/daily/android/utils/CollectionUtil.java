package io.github.heyraud.daily.android.utils;

import java.util.Collection;

/**
 * 描述
 *
 * @author aero.tang
 * @version 2018/8/3 00:28
 */
public class CollectionUtil {

    public static int size(Collection collection) {
        return collection == null ? 0 : collection.size();
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }
}
