package flowable;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import operator.suppressing.Take;

public class Flowablex {
    /*
       Flowable : Flowable allows you to control the pace at which the observable is emitting and ask tells
       the source to emit at a pace specified by the downstream operations.
    */

    /*
        What is great about Flowables and their operators is that they usually do all the work for you
     */
    public static void main(String... args) {
        exFlowableTwo();
    }

    private static void exFlowableOne() {
        /*
            Flowable : Here we will apply flowable to the example we have seen in the
            backpressure section.
        */

        Flowable.range(1,999999999)
                .map(BackPressure.Item::new)
                .observeOn(Schedulers.io())
        .subscribe(item ->                  {   Take.sleep(50);
        System.out.println("Processed "+ item.value);});

        Take.sleep(Long.MAX_VALUE);


        /* OUTPUT : <-- Output Snippet -->
            .
            .
            Processing 128
            Processed 1
            Processed 2
            Processed 3
            Processed 4
            .
            .
            Processed 96
            Processing 129
            Processing 130
            Processing 131
         */

        /*
            From the output we can say that initially 128 items were in processing. After that observeOn Pushed
            96 processed 96 emissions were processed and like that the flow went on.
         */

        /*
            The difference in processed and processing elements are significantly less.
         */

    }

    //Creating Flowable
    private static void exFlowableTwo() {
        Flowable<Integer> flowable = Flowable.create(emitter -> {

            for(int i = 0 ; i < 100 ; i ++){
                if(!emitter.isCancelled()){
                    emitter.onNext(i);
                }
            }

            emitter.onComplete();

        }, BackpressureStrategy.BUFFER);
        flowable.subscribe(System.out::println);
    }


    }