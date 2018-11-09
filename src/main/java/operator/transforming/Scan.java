package operator.transforming;

import io.reactivex.Observable;
import operator.suppressing.Take;

public class Scan {
    /*
       Scan : Is a rolling aggregator. It will emit the element by performing the specific operation with the previous emission to it.
    */
    public static void main(String... args) {
        exScanTwo();
    }

    private static void exScanOne() {
        /*
            Scan will have the both previous and current value
        */

        Observable.just(0,1,2,3,4,5,6,7).scan((previous,current) -> previous + current).subscribe(System.out::println);
    }

    private static void exScanTwo() {
        /*
            Putting comma after each element
        */

//        Observable.just("Healer","Neha","Life","Musically","Died","Death").scan((aggregator,current) -> aggregator+","+current).lastElement()
//                .subscribe(System.out::println);


        Observable.fromIterable(SwitchIfEmpty.activeBugs()).map(bug -> bug.title).scan((aggregator,current) -> aggregator+","+current).lastElement()
                .subscribe(System.out::println);

    }

}