package operator.suppressing;


import io.reactivex.Observable;

public class TakeWhile {
    /*
       TakeWhile : this operator will take the emission while the condition is true. As soon as the condition is false it wil call onComplete.
    */
    public static void main(String... args) {
        takeWhileOne();
    }


    private static void takeWhileOne(){
        Observable.just(1,2,3,4,5,6,7,8,9,10).takeWhile(num -> num < 6).subscribe(System.out::println,Throwable::printStackTrace,() -> System.out.println("OnComplete"));
    }

}