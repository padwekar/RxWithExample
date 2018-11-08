package operator;


import io.reactivex.Observable;

public class Defer {

    private static int start = 1 ;
    private static int count = 10;

    /*
       Official : Do not create the Observable until the observer subscribes, and create a fresh Observable for each observer
    */
    public static void main(String... args) {
        withDefer();
    }

    public static void withOutDefer(){

        Observable<Integer> integerObservable = Observable.range(start,count);

        integerObservable.subscribe(System.out::println);

        count = 2;

        integerObservable.subscribe(System.out::println);

        /*
            The problem is even after modifying the count value.
            It will still take the count value which was used at the time of creating it.

         */
    }


    public static void withDefer(){


        Observable<Integer> integerObservable = Observable.defer(() -> Observable.range(start,count) ) ;

        integerObservable.subscribe(System.out::println);

        count = 2;

        integerObservable.subscribe(System.out::println);

        /*
            The problem is even after modifying the count value.
            It will still take the count value which was used at the time of creating it.
         */
    }


}