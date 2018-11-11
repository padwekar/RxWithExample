package operator.errorrecovery;

import io.reactivex.Observable;
import io.reactivex.Observer;
import model.Bugs;

import java.util.List;

import static model.Bugs.activeBugs;

public class OnErrorResume {

    private static int errorCounter = 0;

    /*
       OnErrorResumeNext : Similar to onErrorReturn and onErrorReturnItem. The only potential difference is that incase of error you can
       emit multiple value by providing other observable.
    */
    public static void main(String... args) {
        egOnErrorResumeOne();
    }

    private static void egOnErrorResumeOne() {
        /*
            Here OnErrorResume provides multiple emission from another observer if source observer encounters any error.
        */

        Observable.just(1,0,3,5,6,8).map(m -> 10/m).onErrorResumeNext(Observable.just(-1))
                .subscribe(System.out::println);


    }

    private static void egOnErrorResumeTwo() {
        /*
            Here OnErrorResume
        */

        List<Bugs> bugsList = activeBugs();
        bugsList.add(new Bugs(0,"BuggyItem",0));

        Observable.fromIterable(bugsList).map(bug -> 1000/bug.id).scan((agg,value) -> errorCounter++)
                .onErrorResumeNext(Observable.just(-1).repeat((bugsList.size() - (errorCounter))))
                .subscribe(System.out::println,Throwable::printStackTrace,() -> {
                    errorCounter = 0;
                    System.out.println("Done");
                });
    }

}