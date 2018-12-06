package flowable;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import operator.suppressing.Take;
import org.reactivestreams.Subscription;

public class Subscriber {
    /*
       Subscriber : To create a subscriber you need to pass lamba in the subscribe method. Also when you subscribe you get
       a subscription on which you can request the number of items from the source at once.
    */
    public static void main(String... args) {
        exSUbscriberOne();
    }

    private static void exSUbscriberOne() {
        /*
            Subscriber : Simplest way to create a subscriber by passing lambda.
        */

        Flowable.range(1,100)
                .doOnNext(s -> System.out.println("Source pushed "+s))
                .observeOn(Schedulers.io())
                .map(Subscriber::intenseCalculation)
                .subscribe(new org.reactivestreams.Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE); //Simplest form
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Take.sleep(50);
                        System.out.println("Received "+ integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Take.sleep(40000);



        /* OUTPUT :

         */
    }

    private static <T> T intenseCalculation(T value){
        Take.sleep(2000);
        return value;
    }

}