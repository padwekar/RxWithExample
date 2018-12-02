package slowingemissions.buffering;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.HashSet;

public class Simple {
    /*
       Simple : Is a way to binding up the emissions into batches based on certain scope and emitting
       them as list or any other collection. Batches can be fixed size, time based or time slices of other
       observables.
    */


    public static void main(String... args) {
        exBufferingFive();
    }

    private static void exBufferingOne() {
        /*
            Simple : Here we will see fixed size buffering.Simplest overload of buffer
            accepts a size parameter used to decide the fixed size of batch.
        */

        Observable.range(1,50)
                .buffer(10)
                .subscribe(System.out::println);


        /* OUTPUT :
            [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
            [11, 12, 13, 14, 15, 16, 17, 18, 19, 20]
            [21, 22, 23, 24, 25, 26, 27, 28, 29, 30]
            [31, 32, 33, 34, 35, 36, 37, 38, 39, 40]
            [41, 42, 43, 44, 45, 46, 47, 48, 49, 50]
         */
    }

    private static void exBufferingTwo() {
        /*
            Simple : Cont.ex1 Of course if the
            size provided does not divide the total emissions clearly the remaining emission will
            be emitted in the final batch.
        */
        Observable.range(1,10)
                .buffer(3)
                .subscribe(System.out::println);

        /* OUTPUT :
            [1, 2, 3]
            [4, 5, 6]
            [7, 8, 9]
            [10]
         */
    }


    private static void exBufferingThree(){
        /*
            Simple : You can filter the final list whose size is not
            equal to the buffer size provided.
         */

        int bufferSize = 3;

        Observable.range(1,10)
                .buffer(bufferSize)
                .filter(list -> list.size() == bufferSize)
                .subscribe(System.out::println);

         /* OUTPUT :
            [1, 2, 3]
            [4, 5, 6]
            [7, 8, 9]
         */
    }


    //BufferSupplier
    private static void exBufferingFour() {
        /*
            Simple : Along with the size you can also pass buffer supplier which specifies the
            resultant collection you are expecting
         */

        int bufferSize = 3;


        Observable.fromIterable(Arrays.asList(1,1,1,2,3,3,4,5,6,7,7,8,9))
                .buffer(bufferSize, HashSet::new)
                .filter(list -> list.size() == bufferSize)
                .subscribe(System.out::println);

         /* OUTPUT :
            [1, 2, 3]
            [4, 5, 6]
            [7, 8, 9]
         */
    }


    //Skip
    private static void exBufferingFive() {
        /*
            Simple : Skip argument of buffer() tells number of emissions to be skipped before
            emitting in the next batch.
         */

        int bufferSize = 2;
        int numberOfEmissionToSkipBeforeNextBatch = 3;


        Observable.range(1,10)
                .buffer(bufferSize, numberOfEmissionToSkipBeforeNextBatch)
                .subscribe(System.out::println);

         /* OUTPUT :
            [1, 2]
            [4, 5]
            [7, 8]
            [10]
         */
    }

}