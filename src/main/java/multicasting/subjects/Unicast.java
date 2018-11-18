package multicasting.subjects;

import io.reactivex.subjects.UnicastSubject;

public class Unicast {
    /*
       UnicastSubject : Will buffer all the missions until any observer subscribes to it.
       And releases the emission all at once to that subscriber and clears the cache.
    */
    public static void main(String... args) {
        exUnicastOne();
    }

    private static void exUnicastOne() {
        /*
            Unicast : Output says the second observer doesn't receive anything.
        */

        UnicastSubject<Integer> integerUnicastSubject = UnicastSubject.create();

        integerUnicastSubject.onNext(1);
        integerUnicastSubject.onNext(2);
        integerUnicastSubject.onNext(3);
        integerUnicastSubject.onNext(4);

        integerUnicastSubject.subscribe(result -> System.out.println("Caught by 1st observer : "+ result));

        //Throws error as it can't be subscribed again.
        //integerUnicastSubject.subscribe(result -> System.out.println("Caught by 1st observer : "+ result));


        /* OUTPUT :
            Caught by 1st observer : 1
            Caught by 1st observer : 2
            Caught by 1st observer : 3
            Caught by 1st observer : 4
         */
    }


}