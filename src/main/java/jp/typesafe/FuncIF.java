package jp.typesafe;

import jp.typesafe.collection.NotEmptyList;

public interface FuncIF {

    @FunctionalInterface
    public interface IBoolean {
        boolean eval();
    }

    @FunctionalInterface
    public interface ITrue<P, R> {
        R eval(P param);
    }

    @FunctionalInterface
    public interface IEach<E, R> {
        R eval(E element);
    }

    @FunctionalInterface
    public interface IKeyExtractor<E, R extends Comparable<? super R>> {
        R eval(E element);
    }

    @FunctionalInterface
    public interface IFalse<R> extends IJustReturn<R> {}

    @FunctionalInterface
    public interface IVoidTrue<P> {
        void eval(P param);
    }

    @FunctionalInterface
    public interface IVoidFalse {
        void eval();
    }

    @FunctionalInterface
    public interface INotEmpty<P, R> {
        R eval(NotEmptyList<P> param);
    }

    @FunctionalInterface
    public interface IEmpty<R> extends IJustReturn<R> {}


    interface IJustReturn<R> {
        R eval();
    }

}
