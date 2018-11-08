package operator;


import io.reactivex.Observable;

public class Range {
    /*
         This will emit each number from a start value and increment each emission until the specified count is reached.
     */
    public static void main(String...args){
        Observable.range(5,10).subscribe(System.out::println);
    }
}
