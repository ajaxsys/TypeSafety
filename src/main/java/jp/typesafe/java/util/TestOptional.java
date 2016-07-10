package jp.typesafe.java.util;

public class TestOptional {

	public static void main(String[] args) {

        test(Optional.of("a"), Optional.of("b"));
        test(Optional.of("a"), Optional.of(1));
        test(Optional.of(1), Optional.of(2));
	}

    private static
    <T extends Comparable<?>,S extends Comparable<?>>
    void
    test(
        Optional<T> a,
        Optional<S> b) {

        a.ifPresent_(
            v->
                System.out.println(v),
            ()->
                System.out.println("nothing"));

		b.ifPresent_(
				v->
					System.out.println(v),
				()->
				    System.out.println("nothing"));

		Optionals.if2Way(a,b).eval_(
		    (v1,v2)->
		        System.out.println("a,b both not null"),
	        (v1)->
                System.out.println("a not null"),
            (v2)->
                System.out.println("b not null"),
            ()->
                System.out.println("both null"));

		System.out.println(
	      Optionals.if2Way(a,b).eval(
	            (v1,v2)->
	                tryCalcAsNumber(v1, v2),
	            (v1)->
	                v1,
	            (v2)->
	                v2,
	            ()->
	                0));
    }


    @SuppressWarnings("deprecation")
    private static
    <T extends Comparable<?>,S extends Comparable<?>>
    double
    tryCalcAsNumber(
        T v1,
        S v2) {

        Optionals.if2Way(
            Numbers.isNumeric(v1.toString()),
            Numbers.isNumeric(v2.toString())).
        eval_(
            (l,r)->
                System.out.println("both true"),
            (l)->
                System.out.println("l true"),
            (r)->
                System.out.println("r true"),
            ()->
                System.out.println("both false"));

            return
                Optionals.if2Way(
                    Numbers.parseDouble(v1.toString()),
                    Numbers.parseDouble(v2.toString())).
                eval(
                    (l,r)->
                        l + r,
                    (l)->
                        l,
                    (r)->
                        r,
                    ()->
                        0.0);
    }

}
