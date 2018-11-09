package operator.transforming;

import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;

public class Map {
    /*
       Map : Maps each emission into some specific transformation.
    */
    public static void main(String... args) {
        exMapTwo();
    }

    private static void exMapOne() {
        /*
            Here Map will transform each name to greet name. It does one to one conversation
        */

        Observable.just("Saurabh","Healer","Tushar").map(name -> "Welcome "+ name).subscribe(System.out::println);
    }

    private static void exMapTwo() {
        /*
            Here Map will transform each emission to 10 times of its value.
        */

        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);

        Observable.fromIterable(integerList).map(number -> number * 10).subscribe(System.out::println);
    }

}