package operator.action;

import io.reactivex.Observable;

public class DoOnSuccess {
    /*
       DoOnSuccess : Since there is no doOnNext for Single and Maybe Operator. We Have doOnSuccess operator which will be called before
       OnSuccess of the observer chain is called.
    */
    public static void main(String... args) {
        exDoOnSuccessOne();
    }

    private static void exDoOnSuccessOne() {
        /*
            Here DoOnSuccess will be called before the onComplete
        */

        Observable.just(5, 3, 7, 10, 2, 14)
                .reduce((total, next) -> total + next)
                .doOnSuccess(i -> System.out.println("Just before: " + i))
                .subscribe(i -> System.out.println("Success: " + i));
    }

    private static void exDoOnSuccessTwo() {
        /*
            Here DoOnSuccess
        */
    }

}