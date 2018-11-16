package observable.combining;

import io.reactivex.Observable;

public class GroupBy {
    /*
       GroupBy : Will map every emission into an separate observable grouped on basis of specified condition.
    */
    public static void main(String... args) {
        exGroupByOne();
    }

    private static void exGroupByOne() {

        Observable.just("One","Two","Three","Four","Five","Ten")
                .groupBy(num -> num.charAt(0))
                .flatMapSingle(grp -> grp.toList())
                .subscribe(System.out::println);

    }

    private static void exGroupByTwo() {
        /*
            Here GroupBy
        */
    }

}