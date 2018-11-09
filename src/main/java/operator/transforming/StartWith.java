package operator.transforming;

import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;

public class StartWith {
    /*
       StartWith : Is useful when you want to emit some initial value before the actual emission takes place.
    */
    public static void main(String... args) {
        exStartWithTwo();
    }

    private static void exStartWithOne() {
        /*
            Here StartWith will append the title "Lottery Participants" before the list of participants.
        */

        List<String> stringList = new ArrayList<>();
        stringList.add("Saurabh");
        stringList.add("Tushar");
        stringList.add("Nayana");
        stringList.add("Healer");
        stringList.add("Neha");
        stringList.add("Dimpi");

        Observable.fromIterable(stringList).startWith("Participants").subscribe(System.out::println);

    }

    private static void exStartWithTwo() {
        /*
            StartWith can also accepts an array if you want to emit multiple initial value before actual emission starts.
        */

        Observable.just("One","Two").startWithArray("Numbers","------").subscribe(System.out::println);
    }

}