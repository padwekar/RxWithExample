package operator.action;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

public class DoOnSubscribeDispose {
    /*
       DoOnSubscribe : Will be called whenever an observer subscribe to an observable.

       DoOnDispose : Wil be not be called for normal disposal using dispose method.

    */

    public static void main(String... args) {
        exDoOnSubscribeDisponseTwo();
    }

    private static void exDoOnSubscribeDisponseOne() {
        /*
            Here DoOnSubscribeDispose
        */

       Disposable disposable = Observable.just(1,2,3,4,5)
                .doOnSubscribe(d -> System.out.println("Subscribed"))
                .doOnDispose(() -> System.out.println("Disposed"))
                .subscribe(System.out::println);
        disposable.dispose();

    }

    private static void exDoOnSubscribeDisponseTwo() {
        /*
            Here DoOnSubscribeDispose
        */
        Single.fromCallable(() -> 1)
                .doOnSubscribe(d -> System.out.println("Subscribing!"))
                .doOnDispose(() -> System.out.println("Disposing!")).subscribe(i -> System.out.println("RECEIVED: " + i));


    }

}