package io.github.mikeiansky.utils;

import java.util.function.Function;

/**
 * @author mike ian
 * @date 2024/12/3
 * @desc
 **/
public class ObjKit {

    public static <T, R> R safeGet(T target, Function<T, R> apply) {
        if (target == null) {
            return null;
        }
        return apply.apply(target);
    }

}
