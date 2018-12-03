package slowingemissions.switching;

import io.reactivex.Observable;
import operator.suppressing.Take;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static operator.suppressing.Take.sleep;

public class SwitchMap {
    /*
       SwitchMap :
    */

    private static int currentVersion = 0;
    private static int versionRange = 10;

    public static void main(String... args) {
        exSwitchMapThree();
    }

    private static void scenario() {
        /*
            SwitchMap : We have one Observable has 9 string for emission. Each emission we are mapping with an function
            which emulate intense calculation which can take between 0-2 seconds. Switch to next example.
        */

        Observable<String> stringObservable = Observable.just("One","Two","Three","Four","Five","Six","Seven","Eight","Nine");

        Observable<String> stringProcessor = stringObservable.concatMap(data -> Observable.just(data).delay(randomSleepTime(),
                TimeUnit.MILLISECONDS));

        stringProcessor.subscribe(System.out::println);

        sleep(20000);

        /* OUTPUT :
                One
                Two
                Three
                Four
                Five
                Six
                Seven
                Eight
                Nine
         */
    }

    public static int randomSleepTime() {
        //returns random sleep time between 0 to 2000 milliseconds
        return ThreadLocalRandom.current().nextInt(2000);
    }

    public static int generateVersion() {

        if(currentVersion + 1 >= versionRange + currentVersion){
            versionRange += 10;
        }

        if(currentVersion + 1 >= versionRange + currentVersion){
            reset();
        }

        currentVersion = ThreadLocalRandom.current().nextInt(currentVersion+1,versionRange + currentVersion);

         return currentVersion;

    }

    private static void reset(){
        currentVersion = 1;
        versionRange = 10;
    }

    private static void exSwitchMapTwo() {
        /*
            SwitchMap : Suppose we want to run exSwitchMapOne() every 5 seconds of interval and dispose all remaining
            processes. After every 5 seconds the operation getResponse() is getting executed and in-case if it is already in
            progress, then it will dispose automatically and perform a fresh service call.
        */

       Observable.interval(5,TimeUnit.SECONDS)
               .switchMap(call -> Observable.just(getResponse()).doOnDispose(() -> System.out.println("Disposed")))
               .subscribe(System.out::println);

        //An Observable which performs network call.
        sleep(40000);


        /* OUTPUT :
            6
            10
            13
            17
            26
            32
            35
            42

         */
    }


    private static void exSwitchMapThree(){

        //Observable has some string.
        Observable<String> intense =
                Observable.just("Savi","Healer","Three","Four","Wednesday","Friday","You","Me")
                .concatMap(result -> Observable.just(result).delay(randomSleepTime(),TimeUnit.MILLISECONDS))
                .doOnDispose(() -> System.out.println(" * * Disposed * *"));

        //Observable to execute intense task every 5 seconds
        Observable.interval(5,TimeUnit.SECONDS)
                .switchMap(r -> intense)
                .subscribe(System.out::println);

        //Sleep the main thread for few seconds
        Take.sleep(20000);

        /*
            After every 5 seconds it will dispose the ongoing intense (variable name) process and
            start the same process again.
         */

        //Output
        /*
        Savi
        Healer
        Three
        Four
         * * Disposed * *
        Savi
        Healer
        Three
        Four
        Wednesday
        Friday
        You
         * * Disposed * *
        Savi
        Healer
        Three
        Four
        Wednesday
         * * Disposed * *
         */
    }

    private static Integer getResponse(){
        randomSleepTime();
        return generateVersion();
    }

}