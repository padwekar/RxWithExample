package operator.suppressing;


import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Take {
    /*
       Take : Has two overloads.

    */
    public static void main(String... args) {
        Observable.interval(5,TimeUnit.SECONDS).subscribe( x -> System.out.println("Recieved "+ x),Throwable::printStackTrace,()-> System.out.println("Done"));
        //takeLast();
    }

    /*
     1. One will take specified number of emissions before calling onComplete. eg take(4)
     */
    private static void takeOne(){
        Observable.just(1,2,3,4,5,6,7,8).take(3).subscribe(System.out::println,Throwable::printStackTrace,() -> System.out.println("Done"));
    }

    /*
    1.1.Also If you emit less number of emission than you specified in take operator. It will simply emit those number of emission and onComplete will be called.
     */
    private static void takeOnePointOne(){
        Observable.just(1,2,3,4).take(5).subscribe(System.out::println,Throwable::printStackTrace,() -> System.out.println("Done"));
    }

    /*
        2.Second one will take emission for specified time interval before calling onComplete.
     */

    private static void takeTwo(){
        Observable.interval(2,TimeUnit.SECONDS).take(4, TimeUnit.SECONDS)
                .subscribe(System.out::println,Throwable::printStackTrace,() -> System.out.println("Done"));

        sleep(5000);
    }


    private static void takeLast(){
        Observable.just(1,2,3,4,6,7,8,9).takeLast(2).subscribe(System.out::println);
    }

    public static void sleep(long millis){
        try {
            Thread.sleep(millis);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }


}