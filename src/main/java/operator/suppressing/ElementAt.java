package operator.suppressing;

import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ElementAt {
    /*
       ElementAt : Is used to get a specific emission by index.
    */
    public static void main(String... args) {
        exElementAtOne();
    }

    private static void exElementAtOne() {
        /*
            Here ElementAt will get an element at any random position.
        */

        //Lucky Draw
        List<String> participants = new ArrayList<>();

        participants.add("Healer");
        participants.add("Saurabh");
        participants.add("Padwekar");
        participants.add("Rohit");
        participants.add("Nitish");
        participants.add("Neha");
        participants.add("Nayana");
        participants.add("Teju");

        //The lucky draw winner is.
        Observable.fromIterable(participants).elementAt(new Random().nextInt(participants.size()-1))
                .subscribe(System.out::println,Throwable::printStackTrace,() -> System.out.println("Done"));

    }

    private static void exElementAtTwo() {
        /*
            Here ElementAt
        */
    }

}