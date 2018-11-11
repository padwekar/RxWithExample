package operator.action;

import io.reactivex.Observable;

public class DoOnError {
    /*
       DoOnError : Will peek the error emitting down the chain and lets you do some action on it.
    */
    public static void main(String... args) {
        exDoOnErrorOne();
    }

    private static void exDoOnErrorOne() {
        /*
            Here DoOnError can be used to identify which observer down the chain has thrown the error.
        */

        Observable.just(1,2,3,0,5,6,7,8).doOnError(e -> {
          System.out.println("Source Failed");
        }).map(num -> 10/num).doOnError(e -> {
          System.out.println("Mapping Failed");
        }).subscribe(System.out::println);

    }

    private static void exDoOnErrorTwo() {
        /*
            Here DoOnError
        */
    }

}