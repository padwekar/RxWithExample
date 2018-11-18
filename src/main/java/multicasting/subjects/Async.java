package multicasting.subjects;

import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;

public class Async {
    /*
       Async : Emits the last emission of the operation.
    */
    public static void main(String... args) {
        exAsyncOne();
    }

    private static void exAsyncOne() {
        /*
            Async : It won't emit anything unless onComplete is called.
        */

        AsyncSubject<Integer> integerAsyncSubject = AsyncSubject.create();

        integerAsyncSubject.onNext(1);
        integerAsyncSubject.onNext(2);
        integerAsyncSubject.onNext(3);
        integerAsyncSubject.onNext(4);
        integerAsyncSubject.onNext(5);


        integerAsyncSubject.subscribe(System.out::println);
        //At this point nothing is emitted.

        integerAsyncSubject.onComplete(); // Emits the last emission i.e 5

        /* OUTPUT :
             5
         */
    }

}