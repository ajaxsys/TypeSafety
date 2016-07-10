package jp.typesafe.java.util;

import java.util.ArrayList;
import java.util.List;

import jp.typesafe.Err.ListRequireNotEmptyException;
import jp.typesafe.FuncIF.IEach;

public class Lists {

    public static
    void
    requireNotEmpty(
        List<?> list) {

        if (list.isEmpty())
            throw new ListRequireNotEmptyException();
    }


    public static
    <F,T>
    List<T>
    map(
        List<F> froms,
        IEach<F,T> convert) {

        List<T> tos = new ArrayList<>(froms.size());
        for (int i = 0; i < froms.size(); i++) {
            tos.set(i, convert.eval(froms.get(i)));
        }
        return tos;
    }

}
