package operator.suppressing;


import io.reactivex.Observable;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class Skip {
    /*
       Skip : Operator Does the opposite of take. It skips the emissions falls in the condition.
    */
    public static void main(String... args) {
        skipTwo();
    }


    private static void skipOne() {
        /*
        This observable emits every second, to skip the Initial emissions that occurs within 5 seconds.
        */
        Observable.interval(1, TimeUnit.SECONDS).skip(5,TimeUnit.SECONDS).subscribe(System.out::println);
        Take.sleep(10000);
    }

    private static void skipTwo(){
         /*
        This observable emits from range 1 to 50 & skips the initial 45 emissions.
         */
         Observable.range(1,50).skip(45).subscribe(System.out::println);
    }

}