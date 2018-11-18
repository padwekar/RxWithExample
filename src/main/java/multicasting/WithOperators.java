package multicasting;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.ThreadLocalRandom;

public class WithOperators {
    /*
       WithOperators : Idea of stream consolidation is called multicasting. But when operators are involved,
       Even if you use ConnectableObservable and Call publish , any operators that follows calls an separate stream.
       Lets understand this with out first example given in 'exWithOperatorsOne()'.
    */

    public static void main(String... args) {
        convertColdObservableToHot();
    }

    private static void coldObserverExample() {
        /*
            Here WithOperators
        */

        Observable<Integer> threeRandom = Observable.range(1,3)
                .map(num -> randomInt());

        threeRandom.subscribe(num -> System.out.println("First Subscriber "+ num));

        threeRandom.subscribe(num -> System.out.println("Second Subscriber "+ num));


    }

    private static void convertColdObservableToHot() {
        /*
            Here WithOperators : If we want to prevent the map() operator from yielding two separate streams for each Observer,
            we need to call publish() after map() instead:
        */


        ConnectableObservable<Integer> threeRandom = Observable.range(1,3)
                .map(num -> randomInt()).publish();

        threeRandom.subscribe(num -> System.out.println("First Subscriber "+ num));

        threeRandom.subscribe(num -> System.out.println("Second Subscriber "+ num));

        threeRandom.connect();


    }

    private static void convertColdObservableToHotHavingOperator() {
        /*

            This occurred because we multicast after Observable.range(), but the multicasting happens before the map() operator.
            Even though we consolidated to one set of emissions coming from Observable.range(), each Observer is still going to get a separate stream at map().
            Everything before publish() was consolidated into a single stream (or more technically, a single proxy Observer).

        */


        ConnectableObservable<Integer> threeRandom = Observable.range(1,3).publish();

        threeRandom.map(num -> randomInt());

        threeRandom.subscribe(num -> System.out.println("First Subscriber "+ num));

        threeRandom.subscribe(num -> System.out.println("Second Subscriber "+ num));

        threeRandom.connect();


    }

    private static int randomInt(){
       return ThreadLocalRandom.current().nextInt(100000);
    }

}