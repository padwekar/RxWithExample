package slowingemissions.throttle;


import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ThrottleParent {

    /*
       Throttle : While buffer and window were consolidating emissions into collections and observable.
       Throttle omits the emission when they occur repeatedly. This is useful when the emissions are redundant or unwanted.
    */


    protected static Observable<String> source1;
    protected static Observable<String> source2;
    protected static Observable<String> source3;

    static {
        init();
    }

    /*
     This case: we have three Observable.interval() sources, the first emitting every 100 milliseconds,
     the second every 300 milliseconds, and the third every 2000 milliseconds.
     We only take 10 emissions from the first source, three from the second, and two from the third.
     */

    private static void init(){

        source1 = Observable.interval(100, TimeUnit.MILLISECONDS)
                .map(num -> ((num+1)*100))
                .take(10)
                .map(i -> "SOURCE 1: " + i);

        source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(num -> ((num+1)*300))
                .take(3)
                .map(i -> "SOURCE 2: " + i);


        source3 = Observable.interval(2000, TimeUnit.MILLISECONDS)
                .map(num -> ((num+1)*2000))
                .take(2)
                .map(i -> "SOURCE 3: " + i);

    }

}
