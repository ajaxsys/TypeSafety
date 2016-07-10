package jp.typesafe.collection;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

abstract class BaseTypeSafeList<E> {

    abstract List<E> getRawList();

    @SafeVarargs
    static <E> E[] mergeArray(E e, E... es) {

        int length;
        if (es == null || es.length == 0) {
            length = 1;
        } else {
            length = es.length + 1;
        }

        @SuppressWarnings("unchecked")
        final E[] arr = (E[]) Array.newInstance(e.getClass(), length);
        arr[0] = e;
        if (length > 1) {
            System.arraycopy(es, 0, arr, 1, arr.length);
        }
        return arr;
    }


    @SafeVarargs
    static <E> E[] newArray(int length, E... array) {
        return Arrays.copyOf(array, length);
    }
}
