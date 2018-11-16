package observable.combining.concatenation;

import io.reactivex.Observable;

public class Concat {
    /*
        Concat : Concatenation is remarkably similar to merging,
        but with an important nuance: it will fire elements of
        each provided Observable sequentially and in the order specified.

        1. IT will not move to next observable until the current observable calls the onComplete.
        2. This is a great way to ensure that emissions happens sequentially.
        3. This one is a poor choice if you are using infinite observable. Since it will never move to another Observable.
        4. if you still want to use concat with infinite observable then this should be the last observable.
    */

    public static void main(String... args) {
        exConcatTwo();
    }

    private static void exConcatOne() {
        /*
            Here Concat : Refer Merge Documents
        */

       Observable<Integer> integerObservable = Observable.just(1,2,3,4,5,6,7);

       Observable<String> stringObservable = Observable.just("Saurabh","Padwekar");

       stringObservable.mergeWith(stringObservable).subscribe(System.out::println);
    }

    private static void exConcatTwo() {
        /*
            Here Concat : Refer Merge Documents
        */


        Observable<Integer> integerObservable = Observable.just(1,2,3,4,5,6,7);

        Observable<String> stringObservable = Observable.just("Saurabh","Padwekar");

        Observable.concat(integerObservable,stringObservable).subscribe(System.out::println);
    }

}