package operator.suppressing;


import io.reactivex.Observable;

public class Filter {

    /*
        Probably the most commonly used operator in RxJava.
        Takes the boolean condition and only allows those emissions which satisfied the given condition.
     */


    public static void main(String... args) {
        Observable.just("Abba","Amla","Aventree","Aloveen","Bootstrap","Coldy")
                .filter(emission -> emission.startsWith("A")).
                subscribe(System.out::println,Throwable::printStackTrace,() -> System.out.println("Complete"));
    }

}