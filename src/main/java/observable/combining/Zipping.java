package observable.combining;

import io.reactivex.Observable;
import operator.suppressing.Take;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Zipping {
    /*
       Zipping : Zipping will combine emission from each observable in an alternate manner.
    */
    public static void main(String... args) {
        exZippingTwo();
    }

    private static void exZippingOne() {
        /*
            Here Zipping
        */

        List<String> stringList = Arrays.asList("Savi","Healer","Padwekar");

        Observable<String> stringObservable = Observable.fromIterable(stringList);

        Observable<Integer> integerObservable = Observable.range(1,stringList.size());

        Observable.zip(integerObservable,stringObservable, (s1,s2) -> s1 +" : "+ s2)
                .subscribe(System.out::println);
    }

    private static void exZippingTwo() {
        /*
            Here Zipping will wait for another observer to complete before pairing the emissions.
        */

        Observable<Long> longObservable = Observable.interval(1, TimeUnit.SECONDS);

        Observable<String> stringObservable = Observable.just("Saurabh","Padwekar","Savi","Healer");

        Observable.zip(stringObservable,longObservable,(s,l) -> (l + 1)+" : "+(s))
                .subscribe(System.out::println,Throwable::printStackTrace,() -> System.out.println("Done"));

        Take.sleep(7000);
    }

}