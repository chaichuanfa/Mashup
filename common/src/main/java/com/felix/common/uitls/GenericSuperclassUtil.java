package com.felix.common.uitls;

import com.felix.common.base.NoViewModel;

import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.Nullable;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by chaichuanfa on 2019/1/15.
 */

public final class GenericSuperclassUtil {

    private GenericSuperclassUtil() {
    }

    /**
     * @return 泛型ViewModel的class对象 nullable
     */
    @Nullable
    public static <T> Class<T> getViewModelClass(Object obj) {
        Class<?> currentClass = obj.getClass();
        Class<T> tClass = getGenericClass(currentClass, AndroidViewModel.class);
        if (tClass == null || tClass == AndroidViewModel.class || tClass == NoViewModel.class) {
            return null;
        }
        return tClass;
    }

    private static <T> Class<T> getGenericClass(Class<?> klass, Class<?> filterClass) {
        Type type = klass.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] types = parameterizedType.getActualTypeArguments();
            if (types != null) {
                for (Type t : types) {
                    Class<T> tClass = (Class<T>) t;
                    if (filterClass.isAssignableFrom(tClass)) {
                        return tClass;
                    }
                }
            }
        }
        return null;
    }
}
