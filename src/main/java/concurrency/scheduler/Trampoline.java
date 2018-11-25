package concurrency.scheduler;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class Trampoline {

    /*
       Trampoline : It will force the task to be happen on the same immediate thread but
       it prevent case of recursive scheduling.
    */

    public static void main(String... args) {
        exTrampolineOne();
    }

    private static void exTrampolineOne() {
        /*
            Trampoline : Here trampoline will queue the task on the main thread and
                    and execute it one by one.
        */

        Observable.just(1,2,3,4,6,5)
                .subscribeOn(Schedulers.trampoline())
                .subscribe(System.out::println);



        Observable.just("Alpha","Beta","Gamma","Pamma")
                .subscribeOn(Schedulers.trampoline())
                .subscribe(System.out::println);


        /* OUTPUT :

            1
            2
            3
            4
            6
            5
            Alpha
            Beta
            Gamma
            Pamma
         */
    }

}