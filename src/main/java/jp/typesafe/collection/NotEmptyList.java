package jp.typesafe.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jp.typesafe.FuncIF.IKeyExtractor;
import jp.typesafe.java.util.Lists;

public class NotEmptyList<E>
        extends BaseTypeSafeList<E> {

    private final List<E> unmodifiableList;

    NotEmptyList(List<E> mustNotEmpty) {

        Lists.requireNotEmpty(mustNotEmpty);

        this.unmodifiableList = Collections.unmodifiableList(mustNotEmpty);
    }

    public E first() {
        return unmodifiableList.get(0);
    }
    public E last() {
        return unmodifiableList.get(unmodifiableList.size()-1);
    }

    @SafeVarargs
    public static <T>
    NotEmptyList<T>
    of(T t, T... ts) {

        return new NotEmptyList<>(
                Arrays.asList(
                    mergeArray(t, ts)));
    }

    public
    <C extends Comparable<? super C>>
    SortedList<E,C>
    sort(
        IKeyExtractor<E,C> valueForCompare) {

//        List<C> newList = Lists.map(
//            unmodifiableList,
//            e-> valueForCompare.eval(e));

        List<E> newList = unmodifiableList.stream().
            sorted(
                (a,b)->
                    valueForCompare.eval(a).
                    compareTo(
                        valueForCompare.eval(b))).
            collect(Collectors.toList());

        return new SortedList<>(
            newList,
            valueForCompare);
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
