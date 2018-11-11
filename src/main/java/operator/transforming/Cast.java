package operator.transforming;

import io.reactivex.Observable;

public class Cast {
    /*
       Cast : Whenever you want to cast each emission to some different type. Use the cast operator.
    */
    public static void main(String... args) {
        egCastOne();
    }

    private static void egCastOne() {
        /*
            Cast using map;
            //Observable.just('a','b','c','d','e').map(character -> (int) character).subscribe(System.out::println);
        */


        /*
            Shorthand notation for the above code
         */

        Observable.just('a','b','c','d','e').cast(Object.class).subscribe(System.out::println);
    }

    private static void egCastTwo() {
        /*
            Here Cast
        */
    }

}