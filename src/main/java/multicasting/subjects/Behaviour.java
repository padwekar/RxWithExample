package multicasting.subjects;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class Behaviour {

    /*
       BehaviourSubject : Is similar to publish subject with only difference is that it will also provided the last emission
       whenever a observer subscribes to it.
    */

    public static void main(String... args) {
        exBehaviourSubjectOne();
    }

    private static void exBehaviourSubjectOne() {
        /*
            As soon as the second observer subscribe to the behaviour subject it receives the last emission from the
            first observer i.e 10.
        */

        BehaviorSubject<Integer> behaviorSubject = BehaviorSubject.create();

        behaviorSubject.map(integer -> integer+" Caught");

        behaviorSubject.subscribe(result -> System.out.println("First Observer "+ result));

        Observable.range(1,10).subscribe(behaviorSubject::onNext);

        behaviorSubject.subscribe(result -> System.out.println("Second Observer "+ result));

        /*
            OUTPUT :

            First Observer 1
            First Observer 2
            First Observer 3
            First Observer 4
            First Observer 5
            First Observer 6
            First Observer 7
            First Observer 8
            First Observer 9
            First Observer 10
            Second Observer 10

         */
    }


}