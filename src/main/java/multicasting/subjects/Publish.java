package multicasting.subjects;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public class Publish {

    /*
       Publish : Is simplest form of subject. To which multiple observer will subscribe and you can manually
       call the onNext and all the observer subscribed to it will receive.
    */

    public static void main(String... args) {
        exPublishSubjectOne();
    }

    private static void exPublishSubjectOne() {
        /*
            Here Publish acts as a both subscriber as well as emitter.
        */

        PublishSubject<Integer> publishSubject = PublishSubject.create();

        publishSubject.map(integer -> integer+" Caught");

        publishSubject.subscribe(result -> System.out.println("First Observer : "+ result),
                Throwable::printStackTrace,
                () -> System.out.println("Completed"));

        publishSubject.onNext(1);

        publishSubject.onNext(2);

        publishSubject.onNext(3);

        publishSubject.onComplete();

        /*
            OUTPUT :

            First Observer : 1
            First Observer : 2
            First Observer : 3
            Completed

         */

    }


}