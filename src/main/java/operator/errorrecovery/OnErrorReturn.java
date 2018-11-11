package operator.errorrecovery;

import io.reactivex.Observable;

public class OnErrorReturn {

    public static void main(String... args) {
        egOnErrorReturnTwo();
    }

    private static void egOnErrorReturnOne() {
        /*
       1. OnErrorReturnItem : Allows you provide default value of the same type if error occurs without observer knowing about it.
        */

        Observable.just(1,2,3,4,5,0,2,5,5)
                .map(value -> 10/value)
                .onErrorReturnItem(-1)
                .subscribe(System.out::println,Throwable::printStackTrace);
    }

    private static void egOnErrorReturnTwo() {
        /*
           2. OnErrorReturn : Also provides the stacktrace and allows you to give to provide the default value as well.
        */

        Observable.just(1,2,4,5,0,1,2,5,1).map(num -> 10/num).onErrorReturn(e -> {
            return -1;
        }).subscribe(System.out::println,Throwable::printStackTrace);

    }

    private static void egOnErrorReturnThree() {
        /*
           3. Inorder to continue the emissions even after the error occurrence  .
        */

        Observable.just(1,2,4,5,0,1,2,5,1).map(num -> 10/num).onErrorReturn(e -> {
            return -1;
        }).subscribe(System.out::println,Throwable::printStackTrace);

    }

}