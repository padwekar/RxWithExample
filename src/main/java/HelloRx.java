import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class HelloRx {
    public static void main(String...args){

        observableWithDelay();

    }


    private static void observableWithDelay(){
        Observable<Long> numberObservable = Observable.interval(1, TimeUnit.SECONDS);
        numberObservable.subscribe(s -> System.out.println(s));
        //sleep(10000);
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void simpleObservable() {
        /*
       A given Observable<T>pushes things of type T through a series of operators until
       it arrives at an Observer that consumes the items.
        */

        Observable<String> myStrings = getSimpleStringObservable();

        /*
           Use just to emit fixed set of items
         */

        /*
           Above Statement is Just declaration and it wont do anything
         */

        /*
          To let that observable push myString we need a subscriber or consumers.To Quicky Connect the @param myStrings
         */

        myStrings.subscribe(s -> System.out.println(s));
    }

    private static Observable<String> getSimpleStringObservable(){
        return Observable.just("Saurabh","Healer","Savi","Padwekar");
    }


    private static void observableWithOperator(){
        /*
            We can append multiple operator before the result actually gets delivered to the subscriber.
         */

        /*
            Each Operator Returns a new observable derived off the previous one.
         */
        Observable<String> myStrings = getSimpleStringObservable();
        myStrings.map(v -> v.length()).subscribe(System.out::println);
    }

    /*
    The key difference is that Observable pushes the items while
    Streams and sequences pull the items
     */
}
