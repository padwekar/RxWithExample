package operator.suppressing;


import io.reactivex.Observable;

public class SkipWhile {
    /*
       SkipWhile : Will skip the emissions as long as the condition is true.
       Once the condition is false it will allow all subsequent emissions no matter what.
    */
    public static void main(String... args) {
        skipWhileOne();
    }


    private static void skipWhileOne(){
        /*
            Once the skipWhile Condition is false it will no longer work.
         */
        Observable.just(5,4,5,1,0,9,8,2).skipWhile($0 -> $0 > 0).
                subscribe(System.out::println,Throwable::printStackTrace,() -> System.out.println("Done"));

    }

}