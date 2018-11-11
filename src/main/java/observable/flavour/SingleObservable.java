package observable.flavour;

import io.reactivex.Observable;
import io.reactivex.Single;

public class SingleObservable {

    /*
        SingleObservable is an observable. That will only emit one item.
        It works just like an observable but, limited only to operators that makes sense for single emissions.
     */

    public static void main(String... args) {
        singleExampleTwo();
    }


    public static void singleExampleOne(){
        Single.just("Hello").map(string -> string.length()).subscribe(
                success -> System.out.println(success));
    }

    public static void singleExampleTwo(){
        Observable<String> stringObservable = Observable.just("XVIDEOS");
        stringObservable.first("lol").subscribe(System.out::println);
    }



}

