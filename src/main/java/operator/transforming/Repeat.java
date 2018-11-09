package operator.transforming;

import io.reactivex.Observable;

public class Repeat {
    /*
       Repeat : It will repeat the emission before the onComplete is called for specified amount of times.
       If you don't specify the number of repetition it will repeat infinitely.
    */
    public static void main(String... args) {
        exRepeatTwo();
    }

    private static void exRepeatOne() {
        /*
            Here Repeat will repeat its emission 2 times before the done is called.
        */

        Observable.just(1,2,3,4,5).repeat(2).subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Done"));

    }

    private static void exRepeatTwo() {
        /*
            Repeating infinite times.
        */
        Observable.just(1,2,3,4,5).repeat().subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Done"));

    }

    private static void exRepeatThree() {
        /*
            Repeating infinite times.
        */
        int count = 0;
        Observable.just(1,2,3,4,5).subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Done"));

    }

}