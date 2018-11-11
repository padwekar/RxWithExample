package operator.transforming;

import io.reactivex.Observable;
import model.Bugs;

import java.util.ArrayList;
import java.util.List;

import static model.Bugs.activeBugs;
import static model.Bugs.bugsFromBackLock;

public class SwitchIfEmpty {
    /*
       SwitchIfEmpty : Switches to another Observable if the source observable turns to be empty.
    */
    public static void main(String... args) {
        egSwitchIfEmptyOne();
    }

    private static void egSwitchIfEmptyOne() {
        /*
            Here SwitchIfEmpty, If there are no active bugs for userId 12 then show the backlog bugs
        */

        getActiveBugsObservable().filter(bug -> bug.assignedTo == 12)
                .switchIfEmpty(getBackLockObservable())
                .filter(bug -> bug.assignedTo == 12)
                .subscribe(bugs -> System.out.println(bugs.title));

    }

    private static void egSwitchIfEmptyTwo() {
        /*
            Here SwitchIfEmpty
        */
    }

    private static Observable<Bugs> getBackLockObservable (){
        return Observable.fromIterable(bugsFromBackLock());
    }

    private static Observable<Bugs> getActiveBugsObservable (){
        return Observable.fromIterable(activeBugs());
    }


}

