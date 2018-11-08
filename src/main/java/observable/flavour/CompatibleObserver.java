package observable.flavour;

import io.reactivex.Completable;

public class CompatibleObserver {

    /*
        Completable is simply concerned with an action being executed, but it does not receive any emissions. Logically, it does not have onNext() or onSuccess() to receive emissions,
         but it does have onError() and onComplete():
     */
    public static void main(String... args) {

        completeExampleOne();

    }

    //Simple calling on complete :)
    public static void completeExampleOne(){
        Completable.complete().subscribe(() -> System.out.println("Done"),Throwable::printStackTrace);
    }

    //From Callable
    public static void completeExampleTwo(){
        Completable.fromCallable(() -> {
            Integer x = 1 ;
            x = x * x + 24 ;
            return x;
        }).subscribe(() -> System.out.println("Done"));
    }
}