package multicasting;

import io.reactivex.Observable;
import operator.suppressing.Take;

import java.util.concurrent.TimeUnit;

public class Cache {
    /*
       Cache : Cache is auto connecting and replay everything for an indefinite time.
    */

    public static void main(String... args) {
        exCacheOne();
    }

    private static void exCacheOne() {

        Observable<Long> integerObservable = Observable.interval(1, TimeUnit.SECONDS)
                                                    .cache();

        integerObservable.subscribe(result -> System.out.println("First observer :"+result));

        Take.sleep(3000);

        integerObservable.subscribe(result -> System.out.println("Second observer :"+result));

        Take.sleep(3000);


        /*
            Output :
            First observer :0
            First observer :1
            First observer :2
            Second observer :0
            Second observer :1
            Second observer :2
            First observer :3
            Second observer :3
            First observer :4
            Second observer :4
            First observer :5
            Second observer :5
         */
    }


}
