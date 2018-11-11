package operator.suppressing;


import io.reactivex.Observable;

public class Distinct {

    /*
       Distinct : Distinct will perform distinct emissions. Will suppress any emissions that is not distinct.
       The distinction of elements will be determined based on the hashcode/equals method.
    */

    public static void main(String... args) {
        egDistinctTwo();
    }

    private static void egDistinctOne() {
        /*
            Here Distinct will filter duplicate elements
        */

        Observable.just("Saurabh","Savi","Saurabh","Healer","Savi").distinct().subscribe(System.out::println);
    }

    private static void egDistinctTwo() {
        /*
            Here Distinct inside distinct block you can override the equality definition.
        */

        Observable.just("Saurabh","Savi","Saurabh","Healer","Savi").distinct(s -> { return s.startsWith("S");}).subscribe(System.out::println);

    }

}