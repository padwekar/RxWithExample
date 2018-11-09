package operator.suppressing;

import io.reactivex.Observable;

public class DistinctUntilChanged {
    /*
       DistinctUntilChanged : Will skip the consecutive repeated value until a new value is emitted.
    */
    public static void main(String... args) {
        exDistinctUntilChangedOne();
    }

    private static void exDistinctUntilChangedOne() {
        /*
            Here DistinctUntilChanged : Will skip the consecutive repeated emissions.
        */

        Observable<String> searchTextObservable = Observable.just("Time","Time","Score","Cricket","Cricket","Time","Football","Cricket");
        searchTextObservable.distinctUntilChanged().subscribe(System.out::println,Throwable::printStackTrace,() -> System.out.println("Done"));
    }

    private static void exDistinctUntilChangedTwo() {
        /*
            Here DistinctUntilChanged while the equality will be on the basis of length.
        */
        Observable<String> searchTextObservable = Observable.just("Time","Time","Score","Cricket","Cricket","Time","Football","Cricket");
        searchTextObservable.distinctUntilChanged(String::length).subscribe(System.out::println,Throwable::printStackTrace,() -> System.out.println("Done"));

    }

}