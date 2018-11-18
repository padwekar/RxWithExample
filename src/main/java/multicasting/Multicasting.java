package multicasting;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class Multicasting {
    /*
       Multicasting : Here lets see how to convert an cold observable into hot observable.
    */
    public static void main(String... args) {
        exMulticastingOne();
    }

    private static void exMulticastingOne() {
        /*
            Here Multicasting will simply convert the 'range' observable into a hot observable.
        */

        ConnectableObservable<Integer> connectableObservable = Observable.range(1,10).publish();


        connectableObservable.subscribe(result -> System.out.println("1st Observer :"+ result));

        connectableObservable.subscribe(result -> System.out.println("2nd Observer :"+ result));


        connectableObservable.connect();


    }


    private static void simpleColdObservable(){
        Observable<Integer> integerObservable = Observable.range(1,10);

        integerObservable.subscribe(result -> System.out.println("1st Observer :"+ result));

        integerObservable.subscribe(result -> System.out.println("2nd Observer :"+ result));

    }



}