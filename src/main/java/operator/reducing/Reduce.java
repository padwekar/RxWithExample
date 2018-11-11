package operator.reducing;

import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;

public class Reduce {
    /*
       Reduce : it is nearly similar to scan() but it roll outs the aggregator only once all the emissions are complete.
       Reduce returns single or maybe.
    */
    public static void main(String... args) {
        egReduceTwo();
    }

    private static void egReduceOne() {
        /*
            Here Reduce can be used to put ,in between the array elements.
        */

        List<String> stringList = new ArrayList<>();

        stringList.add("Sunny");
        stringList.add("Mindfulness");
        stringList.add("Life");
        stringList.add("Death");
        stringList.add("Impermanent");
        stringList.add("Purpose");
        stringList.add("Present");
        stringList.add("Breathing");

        Observable.fromIterable(stringList).reduce((aggregator,current) -> aggregator+","+current)
                .subscribe(System.out::print,Throwable::printStackTrace);

    }

    private static void egReduceTwo() {
        /*
            Here Reduce
        */

        Observable.just(1,2,3,4,5).reduce((aggregator,value)-> aggregator + value).subscribe(System.out::println);
    }

}