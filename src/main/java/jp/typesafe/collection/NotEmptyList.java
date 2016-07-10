package jp.typesafe.collection;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jp.typesafe.FuncIF.IKeyExtractor;
import jp.typesafe.java.util.Lists;

public class NotEmptyList<T> {

    private final List<T> unmodifiableList;

    NotEmptyList(List<T> mustNotEmpty) {

        Lists.requireNotEmpty(mustNotEmpty);

        this.unmodifiableList = Collections.unmodifiableList(mustNotEmpty);
    }

    public T first() {
        return unmodifiableList.get(0);
    }
    public T last() {
        return unmodifiableList.get(unmodifiableList.size()-1);
    }

    public
    <C extends Comparable<? super C>>
    SortedList<T,C>
    sort(
        IKeyExtractor<T,C> valueForCompare) {

//        List<C> newList = Lists.map(
//            unmodifiableList,
//            e-> valueForCompare.eval(e));

        List<T> newList = unmodifiableList.stream().
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

    public Stream<T> stream() {
        return unmodifiableList.stream();
    }

    List<T> getRawList() {
        return unmodifiableList;
    }
}
