package operator.action;

import io.reactivex.Observable;

public class DoOnComplete {
    /*
       DoOnComplete : Allows you to fire an event when its source observable completes its emission before the actual onComplete is called.
    */
    public static void main(String... args) {
        exDoOnCompleteOne();
    }

    private static void exDoOnCompleteOne() {
        /*
            Here DoOnComplete
        */

        Observable.just(1,2,3,4,5,7).doOnComplete(() -> {
            System.out.println("I am done with Emission");
        }).subscribe(System.out::println,Throwable::printStackTrace,() -> System.out.println("Completed"));

    }

    private static void exDoOnCompleteTwo() {
        /*
            Here DoOnComplete
        */
    }

}