package io.github.mikeiansky.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author mike ian
 * @date 2024/12/3
 * @desc 转换工具
 **/
public class ConvertKit {

    public static <T> T ofObj(Object source, Supplier<T> targetSupplier) {
        T target = targetSupplier.get();
        BeanUtils.copyProperties(source, target);
        return target;
    }

    public static <T, R> R convert(T source, Supplier<R> supplier) {
        if (source == null) {
            return null;
        }
        R t = supplier.get();
        BeanUtils.copyProperties(source, t);
        return t;
    }

    public static <T, R> R convert(T source, Supplier<R> supplier, String... ignoreProperties) {
        if (source == null) {
            return null;
        }
        R target = supplier.get();
        BeanUtils.copyProperties(source, target, ignoreProperties);
        return target;
    }

    public static <T, R> List<R> convertList(List<T> sourceList, Supplier<List<R>> targetListSupplier, Supplier<R> supplier, Consumer<R> check) {
        if (sourceList == null || sourceList.isEmpty()) {
            return targetListSupplier.get();
        }
        List<R> targetList = targetListSupplier.get();
        for (T source : sourceList) {
            if (source == null) {
                continue;
            }
            R target = supplier.get();
            BeanUtils.copyProperties(source, target);
            if (check != null) {
                check.accept(target);
            }
            targetList.add(target);
        }
        return targetList;
    }

    public static <T, R> List<R> convertList(List<T> sourceList, Supplier<List<R>> targetListSupplier, Supplier<R> supplier) {
        return convertList(sourceList, targetListSupplier, supplier, null);
    }

}
