package operator.action;

import io.reactivex.Observable;
import model.Bugs;

public class DoOnNext {
    /*
       DoOnNext : When placed between two observer/operator.Informs us before the item reaches from one observer to the another.
    */
    public static void main(String... args) {
        exDoOnNextOne();
    }

    private static void exDoOnNextOne() {
        /*
            Here DoOnNext
        */

        Observable.fromIterable(Bugs.activeBugs())
                .doOnNext(bugs -> {
                    System.out.println(bugs.id+ " before getting mapped.");
                }).map(bugs -> {
                    bugs.id = bugs.id * 10;
                    return bugs;
        }).doOnNext(bugs -> {
                    System.out.println(bugs.id+ "before getting slaughtered.");
        }).map(bugs -> {
            bugs.id = bugs.id * 0;
            return bugs;
        }).subscribe(System.out::println,Throwable::printStackTrace,() -> System.out.println("Done"));
    }

    private static void exDoOnNextTwo() {
        /*
            Here DoOnNext
        */
    }

}