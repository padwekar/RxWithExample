package operator.reducing;

import io.reactivex.Observable;

public class Count {
    /*
       Count : Simplest operator to consolidate emissions into a single one is count.
       It will emit through a single once all complete is called.
    */
    public static void main(String... args) {
        egCountTwo();
    }

    private static void egCountOne() {
        /*
            Count returns a single. which has no OnComplete only success and error.
        */

        Observable.just(1,9,9,4,5,6,7,8,9).count().subscribe(System.out::println,Throwable::printStackTrace);
    }

    private static void egCountTwo() {
        /*
            Here we are counting the total empty emissions.
        */
        Observable.just("Healer","","RockOn","","HelloWorld").filter(String::isEmpty).count().subscribe(System.out::println,Throwable::printStackTrace);
    }

}