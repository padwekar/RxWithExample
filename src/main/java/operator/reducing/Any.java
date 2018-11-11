package operator.reducing;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class Any {

    static boolean hasTen = false;

    /*
       Any : This operator will check if at-least anyone emissions fulfills the criteria and will emit TRUE immediately if it encounters one.

    */
    public static void main(String... args) {
        egAnyOne();
    }

    private static void egAnyOne() {
        /*
            If any number is 10.
        */


        hasTen = false;

        Observable.just(1,2,3,4,6,8,9,10).any(num -> num == 10).subscribeOn(Schedulers.trampoline()).subscribe(result -> {
            hasTen = result;
        });

        if(hasTen){
            System.out.println("Array has 10 in it.");
        } else  {
            System.out.println("Array dont have 10 in it");
        }

    }

    private static void egAnyTwo() {
        /*
            Here Any
        */
    }

}