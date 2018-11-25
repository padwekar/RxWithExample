package concurrency.scheduler;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class Computation {
    /*
       Computation : Use Computation for Math, algorithms, and complex logic. Computation Scheduler will maintain
       Fixed number of threads based on the processor count your java session have and may utilize core to its full
       extent.


       When you are unsure how many tasks will be executed concurrently or
       are simply unsure which Scheduler is the right one to use, prefer the computation one by default.
    */


    public static void main(String... args) {
        exComputationOne();
    }

    private static void exComputationOne() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation());

    }


}