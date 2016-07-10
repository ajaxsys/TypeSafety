package jp.typesafe.java.util;

import java.util.Optional;

public class TestOptional_Java {

	public static void main(String[] args) {

		Optional<String>  a = Optional.of("a");

		System.out.println(a.get());

		Optional<String>  b = Optional.ofNullable(null);

//		System.out.println(b.get());

		b.ifPresent(
				v->
					System.out.println(v));
	}

}
