package cn.eartech.framework.common;

import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Dozer工具
 * @author shanfa
 */
public class DozerUtils {
    public static <T, U> List<U> map(final Mapper mapper, final List<T> source, final Class<U> destType) {

        final List<U> dest = new ArrayList<>();
        for (T element : source) {
            if (element == null) {
                continue;
            }
            dest.add(mapper.map(element, destType));
        }
        Set<Object> singleton = Collections.singleton(null);
        dest.removeAll(singleton);
        return dest;
    }
}
