package flowable;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import operator.suppressing.Take;

public class BackPressure {
    /*
       BackPressure : Is way of controlling the emission speed at which the observable is emitting it's emissions.
       So that consumer can keep pace with it and process them without dropping or creating any problem.
    */
    public static void main(String... args) {
        exBackPressureTwo();
    }

    private static void exBackPressureOne() {
        /*
           Without BackPressure : Let see an example without backPressure.
        */

        Observable.range(1,100)
                .map(Item::new)
                .subscribe(item -> System.out.println("Processed "+ item.value));


        /*
            Above operation is being performed by single thread, so each item is handed one by one to the
            downstream consumer.
         */

        /* OUTPUT :
        Processing 1
        Processed 1
        Processing 2
        Processed 2
        Processing 3
        Processed 3
        .
        .
        .
        .
        .
        .
        Processed 98
        Processing 99
        Processed 99
        Processing 100
        Processed 100

         */
    }


    //With backpressure.

    private static void exBackPressureTwo() {
        /*
            BackPressure : Now for example we introduces concurrency by interpreting the emissions into
            another thread.Then each emissions will happen in different thread resulting an increase in the
            number of producers and the consumer may not be able to keep pace with the emission due to which
            the emission is likely gets queued in the memory which might throw an out of memory error.See the
            following code snippet.
        */

        Observable.range(1,999999999)
                .map(Item::new)
                .observeOn(Schedulers.io())
                .subscribe(item -> {
                    System.out.println("Processed " + item.value);
                });

        Take.sleep(5000);
        /* OUTPUT : <-- Short Snippet ->
            Processing 934753
            Processing 934754
            Processed 840963
            Processed 840964
         */

        /*
            You can 840964 was processed at a time 934753 was in processing. This might lead to increase in
            the buffer size and may cause OutOfMemoryException.To overcome this flowable born.
         */
    }



    static final class Item {
        int value;
        Item(int value){
            this.value = value;
            System.out.println("Processing "+ value);
        }
    }

}