package jp.typesafe.java.util;

public class Optionals {

    @FunctionalInterface
    public static interface IBothGiven<T,S,R> {
        R eval(T left,S right);
    }
    @FunctionalInterface
    public static interface ILeftGiven<T,R> {
        R eval(T left);
    }
    @FunctionalInterface
    public static interface IRightGiven<S,R> {
        R eval(S right);
    }
    @FunctionalInterface
    public static interface INotGiven<R> {
        R eval();
    }

    @FunctionalInterface
    public static interface IVoidBothGiven<T,S> {
        void eval(T left,S right);
    }
    @FunctionalInterface
    public static interface IVoidLeftGiven<T> {
        void eval(T left);
    }
    @FunctionalInterface
    public static interface IVoidRightGiven<S> {
        void eval(S right);
    }
    @FunctionalInterface
    public static interface IVoidNotGiven {
        void eval();
    }

    public static
    <T,S>
    Optional2Way<T,S>
    if2Way(Optional<T> left, Optional<S> rigth) {

        return new Optional2Way<>(left, rigth);
    }

    @Deprecated
    public static
    Optional2Way<?,?>
    if2Way(boolean left, boolean right) {

        return new Optional2Way<>(
           left ? Optional.present() : Optional.empty(),
           right ? Optional.present() : Optional.empty());
    }

    public static class Optional2Way<T,S> {

        private Optional<T> theLeft;
        private Optional<S> theRight;

        private Optional2Way(Optional<T> left, Optional<S> right) {
            this.theLeft = left;
            this.theRight = right;
        }

        public
        <R>
        R
        eval(
            IBothGiven<T,S,R>  bothNotNull,
            ILeftGiven<T,R>  leftNotNull,
            IRightGiven<S,R> rightNotNull,
            INotGiven<R>   bothNull) {

            return this.theLeft.ifPresent(
                    (left)->
                        this.theRight.ifPresent(
                            (right)->
                                bothNotNull.
                                    eval(
                                        left,
                                        right),
                            ()->
                                leftNotNull.eval(left)),
                    ()->
                        this.theRight.ifPresent(
                            (right)->
                                rightNotNull.
                                    eval(
                                        right),
                            ()->
                                bothNull.eval()));
        }

        public
        void
        eval_(
            IVoidBothGiven<T,S>  bothNotNull,
            IVoidLeftGiven<T>    leftNotNull,
            IVoidRightGiven<S>   rightNotNull,
            IVoidNotGiven        bothNull) {

            this.theLeft.ifPresent_(
                (left)->
                    this.theRight.ifPresent_(
                        (right)->
                            bothNotNull.
                                eval(
                                    left,
                                    right),
                        ()->
                            leftNotNull.eval(left)),
                ()->
                    this.theRight.ifPresent_(
                        (right)->
                            rightNotNull.
                                eval(
                                    right),
                        ()->
                            bothNull.eval()));
        }
    }

}
