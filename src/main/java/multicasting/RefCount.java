package multicasting;

import io.reactivex.Observable;
import operator.suppressing.Take;

import java.util.concurrent.TimeUnit;

public class RefCount {
    /*
       RefCount : Similar to autoConnect(1) which fires after one subscription.
       When it has no observer any more it will decompose it self and starts over when it gets one.
    */
    public static void main(String... args) {
        exRefCountOne();
    }

    private static void exRefCountOne() {
        /*
            Here RefCount
        */

        Observable<Long> integerObservable = Observable.interval(1, TimeUnit.SECONDS)
                                                    .publish()
                                                    .refCount();

        integerObservable.take(5).subscribe(result -> System.out.println("First :"+result));

        Take.sleep(3000);

        integerObservable.take(2).subscribe(result -> System.out.println("Second :"+result));


        Take.sleep(3000);

        /*
          At this point all the subscriptions are over within these 3 + 3 = 6 seconds , Observer 1 & 2, has wrapped up
          their work of listening to 5 events and 2 events. So integerObservable has decomposed itself since there is no
          active observer subscribed to it.
         */

        /*
          After this point any new subscriber will get a new stream of emission.
         */

        integerObservable.take(2).subscribe(result -> System.out.println("Third :"+result));

        Take.sleep(3000);


    }


}