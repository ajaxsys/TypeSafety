package jp.typesafe.collection;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import jp.typesafe.FuncIF.IEach;
import jp.typesafe.FuncIF.IEmpty;
import jp.typesafe.FuncIF.INotEmpty;
import jp.typesafe.java.util.Lists;

public class MayEmptyList<T> {
    private List<T> unmodifiableList;

    private MayEmptyList(List<T> list) {
        this.unmodifiableList = Collections.unmodifiableList(list);
    }

    public
    <R> R
    ifPresent(
        INotEmpty<T, R> notEmpty,
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

    public Stream<T> stream() {
        return unmodifiableList.stream();
    }
}
