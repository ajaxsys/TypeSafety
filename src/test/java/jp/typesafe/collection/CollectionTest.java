package jp.typesafe.collection;

import static org.junit.Assert.assertEquals;

import jp.typesafe.collection.*;
import jp.typesafe.collection.DistinctedList;
import jp.typesafe.collection.MayEmptyList;
import jp.typesafe.collection.NotEmptyList;
import jp.typesafe.collection.SortedList;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CollectionTest {

    @Test
    public void testFromMayEmpty() {

        MayEmptyList<Integer> testData = MayEmptyList.of(1, 3, 2, 3);
        assertRawList("Origin data:", testData, 1, 3, 2, 3);

        testData.ifPresent(
                this::doSortThenDistinct,
                ()->
                        "NG");
    }

    @SafeVarargs
    private static <E> void assertRawList(String msg, BaseTypeSafeList<E> testData, E... expecteds) {

        System.out.println(msg + testData);

        Assert.assertArrayEquals(
                expecteds,
                testData.getRawList().toArray());
    }

    private String doSortThenDistinct(NotEmptyList<Integer> notEmpty) {

        assertRawList("Before sort:", notEmpty, 1,3,2,3);


        SortedList<Integer, Integer> sort = notEmpty.sort(i -> i);
        assertRawList("After sort:", sort, 1,2,3,3);


        DistinctedList<Integer, Integer> distinct = sort.distinct();
        assertRawList("After distinct:", distinct, 1,2,3);

        return "OK";

    }
}
