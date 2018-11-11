package operator.transforming;

import io.reactivex.Observable;
import operator.suppressing.Take;

import static model.Bugs.activeBugs;

public class Scan {
    /*
       Scan : Is a rolling aggregator. It will emit the element by performing the specific operation with the previous emission to it.
    */
    public static void main(String... args) {
        egScanTwo();
    }

    private static void egScanOne() {
        /*
            Scan will have the both previous and current value
        */

        Observable.just(0,1,2,3,4,5,6,7).scan((previous,current) -> previous + current).subscribe(System.out::println);
    }

    private static void egScanTwo() {
        /*
            Putting comma after each element
        */

//        Observable.just("Healer","Neha","Life","Musically","Died","Death").scan((aggregator,current) -> aggregator+","+current).lastElement()
//                .subscribe(System.out::println);


        Observable.fromIterable(activeBugs()).map(bug -> bug.title).scan((aggregator,current) -> aggregator+","+current).lastElement()
                .subscribe(System.out::println);

    }

}