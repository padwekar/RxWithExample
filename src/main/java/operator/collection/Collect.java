package operator.collection;

import io.reactivex.Observable;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Collect {
    /*
       Collect : Can used to gather the emission into an user specific collection.
       for e.g toSet() is not available in rxjava and if you need to convert a set of emission into a set.
       You must use collect.
       It takes two values one is used to initialise the collection and another is used for adding the element into it.
    */
    public static void main(String... args) {
        egCollectOne();
    }

    private static void egCollectOne() {
        /*
            Here Collect is converting the emissions into tree set.
        */

        Observable.just(9,1,2,4,5,1,2,4,6).collect(TreeSet::new,TreeSet::add)
                .subscribe(System.out::println);

    }

    private static void egCollectTwo() {
        /*
            Here Collect
        */
    }

}