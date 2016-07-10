package jp.typesafe.collection;

import java.util.List;
import java.util.stream.Stream;

import jp.typesafe.FuncIF.IKeyExtractor;

public class DistinctedList<T,R extends Comparable<? super R>> extends SortedList<T,R> {


    DistinctedList(
        List<T> mustDistincted_Sorted_NotEmpty,
        IKeyExtractor<T,R> c) {

        super(
            mustDistincted_Sorted_NotEmpty, c);
    }

    public Stream<T> stream() {
        return super.stream();
    }

}
