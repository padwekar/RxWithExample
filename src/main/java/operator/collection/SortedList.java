package operator.collection;

import io.reactivex.Observable;

public class SortedList {
    /*
       SortedList : Collect all the emissions into a list. Sorts it and emit the entire collection at once.
    */
    public static void main(String... args) {
        egSortedListTwo();
    }

    private static void egSortedListOne() {
        /*
            Here SortedList will convert the emission into a sorted list.
        */

        Observable.just(1,10,4,4,2,88,43,134,999).toSortedList().subscribe(System.out::println);
    }

    private static void egSortedListTwo() {
        /*
            Here SortedList
        */
        Observable.just("B","Z","R","A","C").toSortedList().subscribe(System.out::println);

    }

}