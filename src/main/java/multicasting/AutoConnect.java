package multicasting;

import io.reactivex.Observable;

public class AutoConnect {
    /*
       AutoConnect : Calls connect automatically on a connectable Observer.
    */
    public static void main(String... args) {
        exAutoConnectOne();
    }

    private static void exAutoConnectOne() {
        /*
            Here AutoConnect will return an Observable which will automatically calls connect after a specified
            number of observer connects to it. Here we have passed two in autoconnect parameter that means it will
            emission will start as soon as two observer subscribes to it.
        */

        Observable<Integer> integerObservable = Observable.just(1,2,3,4,5,6,8,9)
                                            .publish()
                                            .autoConnect(2);

        integerObservable.subscribe(result -> System.out.println("First Observer :"+ result));

        integerObservable.subscribe(result -> System.out.println("Second Observer :"+ result));

    }

    private static void exAutoConnectTwo() {
        /*
            Here AutoConnect if you dont pass anything it will take 1 by default.
        */

        Observable<Integer> integerObservable = Observable.just(1,2,3,4,5,6,8,9)
                .publish()
                .autoConnect();

        integerObservable.subscribe(result -> System.out.println("Observer :"+ result));

    }

}