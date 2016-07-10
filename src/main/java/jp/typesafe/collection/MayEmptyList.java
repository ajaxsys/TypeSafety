package jp.typesafe.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import jp.typesafe.FuncIF.IEach;
import jp.typesafe.FuncIF.IEmpty;
import jp.typesafe.FuncIF.INotEmpty;
import jp.typesafe.java.util.Lists;

public class MayEmptyList<E>
        extends BaseTypeSafeList<E> {

    private List<E> unmodifiableList;

    private MayEmptyList(List<E> list) {
        this.unmodifiableList = Collections.unmodifiableList(list);
    }

    public
    <R> R
    ifPresent(
        INotEmpty<E, R> notEmpty,
        IEmpty<R> empty) {

        return
            !this.unmodifiableList.isEmpty()
            ?
            notEmpty.eval(
                new NotEmptyList<>(this.unmodifiableList))
            :
            empty.eval();
    }

    public static <T>
    MayEmptyList<T>
    of(List<T> copyFrom) {

        return new MayEmptyList<>(
            copyFrom);
    }

    @SafeVarargs
    public static <T>
    MayEmptyList<T>
    of(T... ts) {

        return new MayEmptyList<>(
                Arrays.asList(ts));
    }

    public static <C extends Comparable<? super C>, T>
    MayEmptyList<C>
    ofValue(List<T> copyFrom, IEach<T, C> convertToComparable) {

        List<C> comparables = Lists.map(copyFrom, convertToComparable);

        return new MayEmptyList<>(
            comparables);
    }

    public static <C extends Comparable<? super C>>
    MayEmptyList<C>
    ofValue(List<C> copyFrom) {

        return new MayEmptyList<>(
            copyFrom);
    }

    public Stream<E> stream() {
        return unmodifiableList.stream();
    }

    @Override
    List<E> getRawList() {
        return unmodifiableList;
    }

    @Override
    public String toString() {
        return Arrays.toString(unmodifiableList.toArray());
    }
}
