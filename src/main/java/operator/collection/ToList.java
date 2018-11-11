package operator.collection;

import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.Collections;

public class ToList {
    /*
       ToList : Will collect all the incoming emissions into list and emit them at once.
    */
    public static void main(String... args) {
        egToListOne();
    }

    private static void egToListOne() {
        /*
            Here ToList will convert the int emission to list of integer.
        */

        Observable.just(91,2,13,4,85,96,77,8,9).toList().subscribe(list -> {
            System.out.println(list);
        });
    }

    private static void egToListTwo() {
        /*
            Here ToList
        */
    }

}