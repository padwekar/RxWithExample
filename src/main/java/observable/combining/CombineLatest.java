package observable.combining;

import io.reactivex.Observable;
import operator.suppressing.Take;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class CombineLatest {
    /*
       CombineLatest : Is similar to the zip operator with major difference, Every emission from any source will be
       Coupled up immediately with the latest emission from the another source.
    */

    public static void main(String... args) {
        exCombineLatestOne();
    }

    private static void exCombineLatestOne() {
        /*
            Here CombineLatest
        */

        Observable<String> longObservable = Observable.interval(1, TimeUnit.SECONDS).map(num -> (num)+"s");

        Observable<String> shortObservable = Observable.interval(300,TimeUnit.MILLISECONDS).map(num -> ((num)*300)+"ms");

        // with regular zip

        //longObservable.zipWith(shortObservable,(l,s) -> l +" : "+ s).subscribe(System.out::println);

        //with combine latest

        Observable.combineLatest(longObservable,shortObservable,(l,s) -> l +":"+s).subscribe(System.out::println);
        Take.sleep(2000);


    }

    private static void exCombineLatestTwo() {
        /*
            Here CombineLatest
        */
    }

}