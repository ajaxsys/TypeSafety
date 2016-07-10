package jp.typesafe.collection;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import jp.typesafe.FuncIF.IKeyExtractor;

public class SortedList<T, R extends Comparable<? super R>> extends NotEmptyList<T> {

    private final IKeyExtractor<T, ?> keyExtractor;

    SortedList(
        List<T> mustNotEmpty,
        IKeyExtractor<T, R> c) {

        super(mustNotEmpty);
        this.keyExtractor = c;
    }

    @SuppressWarnings("unchecked")
    IKeyExtractor<T, R>
    getComparator() {
        return (IKeyExtractor<T, R>)keyExtractor;
    }

    public
    DistinctedList<T,R> distinct() {

        List<T> newList = this.stream()
            .filter(
                distinctByKey(
                    getComparator()))
            .collect(Collectors.toList());

        return new DistinctedList<T,R>(
            newList,
            getComparator());
    }

    private static
    <T>
    Predicate<T>
    distinctByKey(IKeyExtractor<T,?> keyExtractor) {

        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t ->
                seen.putIfAbsent(
                    keyExtractor.eval(t),
                    Boolean.TRUE) == null;
    }
}
