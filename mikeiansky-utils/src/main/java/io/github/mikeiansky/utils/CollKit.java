package io.github.mikeiansky.utils;

import cn.hutool.core.lang.Pair;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * @author mike ian
 * @date 2024/8/26
 * @desc 集合工具
 **/
public class CollKit {

    public static <T, K, V> Map<K, V> listToMap(List<T> list,
                                                Function<? super T, ? extends K> keyMapper,
                                                Function<? super T, ? extends V> valueMapper) {
        return Optional.ofNullable(list).orElse(List.of()).stream().collect(Collectors.toMap(keyMapper, valueMapper));
    }

    public static <T> List<T> listFilter(List<T> list,
                                         Predicate<T> filter) {
        return Optional.ofNullable(list).orElse(List.of()).stream().filter(filter).toList();
    }

    public static <T, R> List<R> toList(List<T> list, Function<T, R> mapper) {
        return Optional.ofNullable(list).orElse(List.of()).stream().map(mapper).filter(Objects::nonNull).toList();
    }

    public static <T, K, V> void setPropertiesForList(List<T> list, Map<K, V> map,
                                                      Function<T, K> keyMapper, BiConsumer<T, V> valueConsumer) {
        Optional.ofNullable(list).orElse(List.of()).forEach(item -> {
            V v = map.get(keyMapper.apply(item));
            if (v != null) {
                valueConsumer.accept(item, v);
            }
        });
    }

    public static <T, K, V> void setPropertiesForList(List<T> list, Map<K, V> map,
                                                      List<Pair<Function<T, K>, BiConsumer<T, V>>> mapperList) {
        Optional.ofNullable(list).orElse(List.of()).forEach(item -> mapperList.forEach(pair -> {
            V v = map.get(pair.getKey().apply(item));
            if (v != null) {
                pair.getValue().accept(item, v);
            }
        }));
    }

    public static <T, K, V> void setPropertiesForObj(T t, Map<K, V> map,
                                                     List<Pair<Function<T, K>, BiConsumer<T, V>>> mapperList) {
        mapperList.forEach(pair -> {
            V v = map.get(pair.getKey().apply(t));
            if (v != null) {
                pair.getValue().accept(t, v);
            }
        });
    }

    public static <T, K, V> void setPropertiesForObj(T t, Map<K, V> map,
                                                     Function<T, K> keyMapper, BiConsumer<T, V> valueConsumer) {
        V value = map.get(keyMapper.apply(t));
        if (value != null) {
            valueConsumer.accept(t, value);
        }
    }

    public static <T, R> List<R> toDistinctList(List<T> list, Function<T, R> mapper) {
        return Optional.ofNullable(list).orElse(List.of()).stream().filter(Objects::nonNull).map(mapper).filter(Objects::nonNull).distinct().toList();
    }

    public static <T, R> List<R> toFlatDistinctList(List<T> list, Function<T, List<R>> mapper) {
        return Optional.ofNullable(list).orElse(List.of()).stream().filter(Objects::nonNull)
                .map(mapper)
                .flatMap(List::stream).distinct().toList();
    }

    public static <T, R> List<R> listFlatMapToDistinctList(List<T> list, List<Function<T, R>> mapperList) {
        return Optional.ofNullable(list).orElse(List.of()).stream()
                .flatMap(t -> mapperList.stream().map(mapper -> mapper.apply(t)).filter(Objects::nonNull))
                .collect(Collectors.toSet())
                .stream()
                .toList();
    }

    public static <K, V> List<V> mapFlatToDistinctList(Map<K, List<V>> map) {
        return Optional.of(map).orElse(Map.of()).values().stream().flatMap(Collection::stream).distinct().toList();
    }

    public static <T, R> List<R> objToDistinctList(T obj, List<Function<T, R>> mapperList) {
        return mapperList.stream().map(mapper -> mapper.apply(obj)).filter(Objects::nonNull).distinct().toList();
    }

    public static <T, K> Map<K, List<T>> listGroup(List<T> list,
                                                   Function<? super T, ? extends K> classifier) {
        return Optional.ofNullable(list).orElse(List.of()).stream()
                .collect(Collectors.groupingBy(classifier, Collectors.toList()));
    }

    public static <T, K, V> Map<K, List<V>> listGroup(List<T> list,
                                                      Function<? super T, ? extends K> classifier,
                                                      Function<? super T, ? extends V> valueMapper) {
        return Optional.ofNullable(list).orElse(List.of()).stream()
                .collect(Collectors.groupingBy(classifier, Collectors.mapping(valueMapper, Collectors.toList())));
    }

}
