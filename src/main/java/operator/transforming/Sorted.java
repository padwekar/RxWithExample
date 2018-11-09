package operator.transforming;

import io.reactivex.Observable;

import java.util.Comparator;

public class Sorted {
    /*
       Sorted : Used to sort the element based on Comparable<T> implemented by those objects.

       While using make your object implements Comparable Otherwise it will throw ClassCastException
    */
    public static void main(String... args) {
        exSortedTwo();
    }

    private static void exSortedOne() {
        /*
            Here Sorted sorts the emission based on the comparable implemented.
        */
        Observable.fromIterable(SwitchIfEmpty.bugsFromBackLock()).sorted().subscribe(bug -> System.out.println(bug.id));
        /*
          In reverse order
         */
    }

    private static void exSortedTwo() {
        /*
            Quickly implement comparable
        */
        Observable.fromIterable(SwitchIfEmpty.bugsFromBackLock()).sorted((x,y) -> { if(x.id == y.id){
            return 0;
        }
        return (x.id > y.id) ? 1 : -1;
        }).subscribe(bug -> System.out.println(bug.id));


    }

}