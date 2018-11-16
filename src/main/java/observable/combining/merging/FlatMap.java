package observable.combining.merging;

import io.reactivex.Observable;
import operator.suppressing.Take;

import java.util.concurrent.TimeUnit;

public class FlatMap {
    /*
       FlatMap : The simplest application of flatMap() is to map one emission to many emissions
    */
    public static void main(String... args) {
        exFlatMapThree();
    }

    private static void exFlatMapOne() {
        /*
            Here FlatMap emits every character from the specified string. Flatmap converts each source value to another observable.

            We can use flatmap to specify a function<T,Observable<R>> that maps each T object into Observable of R.
        */

        Observable.just("Saurabh","Padwekar")
                .flatMap(string -> Observable.fromArray(string.split("")))
                .subscribe(System.out::println);

    }

    private static void exFlatMapTwo() {
        /*
            Here in another example of flatmap we will parse only the Number of Strings containing numbers.
        */


        Observable.just("12345/VCAA/8990","MXXMAP/12/BBB","ROD/1234555/XVB","98888","SAVI/222","HEALER")
                .flatMap(string -> Observable.fromArray(string.split("/")))
                .filter(string -> string.matches("[0-9]+"))
                .subscribe(System.out::println);

    }


    private static void exFlatMapThree(){
        /*
            We can also combine multiple infinite sources.
         */


        Observable<Integer> intervalArguments =
                Observable.just(2, 3, 10, 7);

        intervalArguments.
                flatMap(i -> Observable.interval(i, TimeUnit.SECONDS).map(next -> "From "+(i)+"s : "+ (i+1)*next+ " Seconds "))
                .doOnEach(next -> Database.save(next.getValue()))
                .subscribe();

        Take.sleep(12000);


    }

}

 class Database {
    public static void save(String l){
        //Save logic
        System.out.println(l);
    }
}