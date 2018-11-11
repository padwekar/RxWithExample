package operator.transforming;

import io.reactivex.Observable;

public class DefaultIfEmpty {
    /*
       DefaultIfEmpty : Provides an default emission if the dataset is empty
    */
    public static void main(String... args) {
        egDefaultIfEmptyTwo();
    }

    private static void egDefaultIfEmptyOne() {
        /*
            Here DefaultIfEmpty
        */

        Observable.empty().defaultIfEmpty("Data set is empty !!!").subscribe(System.out::println);
    }

    private static void egDefaultIfEmptyTwo() {
        /*
            Here DefaultIfEmpty skip returns empty data sets show an default value is provided to the user, Instead of empty.
        */

        Observable<String> stringObservable = Observable.just("One","Two","Three","Four","Five")
                .skip(10).defaultIfEmpty("Nothing to display");

        stringObservable.subscribe(System.out::println);
    }

}