package multicasting;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import operator.suppressing.Take;

import java.util.concurrent.TimeUnit;

public class Replay {
    /*
       Replay : This operator will cache all the emissions. Whenever a new observer subscribe to the observer, it will
       multi-cast both the previous cached emissions as well as current emission from this point forward.
    */

    public static void main(String... args) {
        exReplayOne();
    }

    private static void exReplayOne() {

        /*
            If you see the output you will notice the second observer has received all the previous
            emissions as well as new emissions from that point forward.
         */

        Observable<Long> stringObservable =
                Observable.interval(1, TimeUnit.SECONDS)
                .replay()
                .autoConnect();

        stringObservable.subscribe(result -> System.out.println("First Observer :"+result));

        Take.sleep(4000);

        stringObservable.subscribe(result -> System.out.println("Second Observer :"+result));

        Take.sleep(2000);


        /*
            Output :

                First Observer :0
                First Observer :1
                First Observer :2
                First Observer :3
                Second Observer :0
                Second Observer :1
                Second Observer :2
                Second Observer :3
                First Observer :4
                Second Observer :4
                First Observer :5
                Second Observer :5

         */
    }

    private static void exReplayTwo() {
        /*
            Here Replay
        */
    }

}