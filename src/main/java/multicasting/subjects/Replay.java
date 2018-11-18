package multicasting.subjects;

import io.reactivex.subjects.ReplaySubject;

public class Replay {
    /*
       Replay : Is similar to Publish Subject with cache.
       This will emit all the previous emission every time a observer subscribes to it.
    */

    public static void main(String... args) {
        exReplaySubjectOne();
    }

    private static void exReplaySubjectOne() {
        /*
            Replay : Looking out the output, you can understand that this one emits all previous emission to the new subscriber.
        */

        ReplaySubject<Integer> integerReplay =  ReplaySubject.create();

        integerReplay.onNext(1);
        integerReplay.onNext(2);
        integerReplay.onNext(3);

        integerReplay.subscribe(result -> System.out.println("Caught by 1st observer : "+ result));

        integerReplay.onNext(4);

        integerReplay.subscribe(result -> System.out.println("Caught by 2nd observer : "+ result));



        /* OUTPUT :

            Caught by 1st observer : 1
            Caught by 1st observer : 2
            Caught by 1st observer : 3
            Caught by 1st observer : 4
            Caught by 2nd observer : 1
            Caught by 2nd observer : 2
            Caught by 2nd observer : 3
            Caught by 2nd observer : 4


         */
    }

    private static void exReplaySubjectTwo() {
        /*
            Replay :
        */


        /* OUTPUT :

         */
    }

}