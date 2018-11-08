package operator;


import io.reactivex.Observable;

public class Empty {
    /*
        Empty observable Emits Nothing, So directly on Complete will be called.

        Empty Observable simply represent empty data sets.

        Its specially the concept of null in rxjava. This is much more elegent because simply of throwing NullPointerException.
        Program continues to run.

     */
    public static void main(String...args){
        Observable.empty().subscribe(System.out::println,Throwable::printStackTrace,() -> System.out.println("Done"));
    }
}
