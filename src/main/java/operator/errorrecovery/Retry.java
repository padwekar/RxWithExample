package operator.errorrecovery;

import io.reactivex.Observable;

public class Retry {
    /*
       Retry : this operator will retry as long as the operation is successfully completed.
    */
    public static void main(String... args) {
        egRetryTwo();
    }

    private static void egRetryOne() {
        /*
            Need to be careful while using retry with no arguments as it will keep on attempting infinite times till the
            Operation gets completed successfully.
        */

        Observable.just(1,2,3,0,1,4).map(m -> 10/m)
                .retry()
                .subscribe(System.out::println,Throwable::printStackTrace);
    }

    private static void egRetryTwo() {
        /*
            Here Retry will try two times to check if it can successfully perform the operation before throwing exception.
        */

        Observable.just(1,2,3,1,1,4).map(m -> 10/m)
                .retry(2)
                .toList()
                .subscribe(System.out::println,Throwable::printStackTrace);


    }

}