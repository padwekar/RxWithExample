package operator;

import io.reactivex.Observable;

public class Error {

    /*
        Mostly Used with testing. To Throw an error and check the Error handling.
     */
    public static void main(String... args) {
        Observable<Integer> error = Observable.error(new Throwable("Something went wrong"));

        error.subscribe(System.out::println,err -> System.out.println("Error Came"));

    }

}