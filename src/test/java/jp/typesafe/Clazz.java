package jp.typesafe;

import javax.annotation.Nonnull;

public class Clazz {
    public static void main(String[] args){
        Clazz clazz = new Clazz();

        // this line raises a complaint with the IDE (IntelliJ 11)
        clazz.directPathToA(null);

        // this line does not
        clazz.indirectPathToA(null);
    }

    public void indirectPathToA(Integer y){
        directPathToA(y);
    }

    public void directPathToA(@Nonnull Integer x){
        x.toString(); // do stuff to x
    }
}