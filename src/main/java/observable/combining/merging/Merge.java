package observable.combining.merging;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import operator.suppressing.Take;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Merge {
    /*
       Merge : If we have only two to four Observable<T> sources to merge,
       you can pass each one as an argument to the Observable.merge() factory.
    */
    public static void main(String... args) {
        exMergeFour();
    }

    private static void exMergeOne() {
        /*
            Here Merge i have merged the response of two observables using the merge operator.
            Merge Operator doesn't guarantee the emission will happen sequentially.
        */

        Observable<String> stringObservable = Observable.just("Saurabh","Padwekar","Healer");

        Observable<Integer> integerObservable = Observable.just(1,2,3,4,5);

        Observable.merge(stringObservable,integerObservable).subscribe(System.out::println);

    }

    private static void exMergeOnePointOne() {
        /*
            You can also use mergeWith to merge two observables.
            Merge with is likely to fire the emissions in order.
            But if the order is priority make sure you use concat instead of merge.
            it requires both the observable of the same type.
        */


        Observable<String> stringObservable = Observable.just("Saurabh","Padwekar","Healer");

        Observable<Integer> integerObservable = Observable.just(1,2,3,4,5);

        stringObservable.mergeWith(stringObservable).subscribe(System.out::println);
    }

    private static void exMergeTwo() {
        /*
            With Merge you can combine multiple (upto 4) observable
        */

        Observable<String> names = Observable.just("Saurabh","Padwekar","Healer");
        Observable<Integer> numbers = Observable.just(1,2,3,4,5);
        Observable<String> weekends = Observable.just("Sunday","Saturday","Friday");
        Observable<String> surnames = Observable.just("Saurabh","Padwekar","Healer");
        Observable<Integer> bigNumbers = Observable.just(96,97,98,99,100);
        Observable<String> weekdays = Observable.just("Monday","Tuesday","Wednesday");

        Observable.merge(names,numbers,weekends,surnames).subscribe(System.out::println);

    }

    private static void exMergeThree() {
        /*
            With Merge you can combine multiple (more than 4) observable
        */

        Observable<String> names = Observable.just("Saurabh","Padwekar","Healer");
        Observable<Integer> numbers = Observable.just(1,2,3,4,5);
        Observable<String> weekends = Observable.just("Sunday","Saturday","Friday");
        Observable<String> surnames = Observable.just("Saurabh","Padwekar","Healer");
        Observable<Integer> bigNumbers = Observable.just(96,97,98,99,100);
        Observable<String> weekdays = Observable.just("Monday","Tuesday","Wednesday");

        Observable.merge(Arrays.asList(names,numbers,weekends,surnames,bigNumbers,weekdays)).subscribe(System.out::println);

    }


    private static void exMergeFour() {
        /*
            Merge with Infinite Observables.
        */

        Observable<Long> secondObservable = Observable.interval(1, TimeUnit.SECONDS);

        Observable<Long> milliSecondObservable = Observable.interval(300,TimeUnit.MILLISECONDS)
                .map(num ->  (num+1)*300);

        secondObservable.mergeWith(milliSecondObservable).subscribe(System.out::println);

        Take.sleep(5000);

    }



}