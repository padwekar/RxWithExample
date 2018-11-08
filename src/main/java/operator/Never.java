package operator;


import io.reactivex.Observable;

/*
   A cousin of Empty Observer ;)
 */
public class Never{
    public static void main(String...args){
        Observable<String> empty = Observable.never();
        /*
            The main difference is that it never calls onComplete. Always keep waiting
            for the observable to emit.

            Doesn't have much use. It is used in tasting to make sure no events are triggered from elsewhere.
         */

        empty.subscribe(System.out::println,Throwable::printStackTrace,() -> System.out.println("done"));
        sleep(5000);
    }

    public static void sleep(long milliseconds){
        try {
            Thread.sleep(milliseconds);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }
}
