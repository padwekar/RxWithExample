package observable.combining.concatenation;

import io.reactivex.Observable;

public class ConcatMap {
    /*
       ConcatMap : Just as there is flatMap(), which dynamically merges Observables derived off each emission,
                   there is a concatenation counterpart called concatMap().

                   More specifically, concatMap() will merge each mapped Observable sequentially and fire it one at a time.
    */


    public static void main(String... args) {
        exConcatMapOne();
    }

    private static void exConcatMapOne() {
        /*
            Here ConcatMap makes sures the merging and emission happens sequentially.
        */

        Observable.just("Alpha","Beta","Gamma")
                .concatMap(string -> Observable.fromArray(string.split("")))
                .subscribe(System.out::println);

    }



}