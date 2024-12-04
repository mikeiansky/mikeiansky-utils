package io.github.mikeiansky.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author winson
 * @date 2022/3/15
 * @desc 数据缓存类
 **/
public class LruCache<K, V> extends LinkedHashMap<K, V> {

    private final int maxSize;

    public LruCache(int maxSize) {
        super(maxSize, 0.75F, true);
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxSize;
    }

}
