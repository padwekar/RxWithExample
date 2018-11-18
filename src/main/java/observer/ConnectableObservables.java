package observer;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class ConnectableObservables {
    /*
       ConnectableObservables : Converts an cold observer to hot observer.
    */
    public static void main(String... args) {
        exConnectableObservableOne();
    }

    private static void exConnectableObservableOne() {
        /*
            Here ConnectableObservables : allows you to setup the observers in advanced, and fire the emission once you are ready.
            & not when it is subscribed.
        */


        ConnectableObservable<Integer> connectableObservable = Observable.just(1,2,3,4,5).publish();

        connectableObservable.subscribe(result -> System.out.println("Caught by 1st : "+ result));

        connectableObservable.subscribe(result -> System.out.println("Caught by 2nd : "+ result));

        connectableObservable.connect();

        //This will be never called as connectableObservable has already finished its emissions.

        connectableObservable.subscribe(result -> System.out.println("Caught by 3rd : "+ result));

    }

    private static void exConnectableObservableTwo() {
        /*
            Here ConnectableObservables
        */
    }

}