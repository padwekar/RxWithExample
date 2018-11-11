package operator.reducing;

import io.reactivex.Observable;

public class All {
    /*
       All : All operators checks if all emissions qualifies the given condition. It yes it will return TRUE.
       if it encounters any false condition it will immediately return FALSE.
    */
    public static void main(String... args) {
        egAllTwo();
    }

    private static void egAllOne() {
        /*
            Here All checks if all the numbers are prime number.
        */

        Observable.just(0, 2, 4, 6, 2, 4, 6, 8, 10, 12).all(num -> num % 2 == 0).subscribe(System.out::println);
    }

    private static void egAllTwo() {
        /*
            If you call all() on an empty Observable, it will emit true due to the principle of vacuous truth.
        */

        Observable.empty().all(o -> o.hashCode() == 1988).subscribe(System.out::println);
    }

}