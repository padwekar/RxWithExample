package observer;

import io.reactivex.Observable;
import io.reactivex.Observer;

//Cold Observables are much like a music CD that can be replayed to each listener
public class ColdObserver {

    public static void main(String...args){

        Observable<String> stringObservable = Observable.just("Neha","Nayana","Teju");

        stringObservable.subscribe(v -> System.out.println("First Observer Received "+v));

        stringObservable.subscribe(v -> System.out.println("Second Observer Received "+v));

        /*
             Many Observables emitting from finite data sources
             such as databases, text files, or JSON are cold
         */


    }

}
