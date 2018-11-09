package operator.transforming;

import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;

public class SwitchIfEmpty {
    /*
       SwitchIfEmpty : Switches to another Observable if the source observable turns to be empty.
    */
    public static void main(String... args) {
        exSwitchIfEmptyOne();
    }

    private static void exSwitchIfEmptyOne() {
        /*
            Here SwitchIfEmpty, If there are no active bugs for userId 12 then show the backlog bugs
        */

        getActiveBugsObservable().filter(bug -> bug.assignedTo == 12)
                .switchIfEmpty(getBackLockObservable())
                .filter(bug -> bug.assignedTo == 12)
                .subscribe(bugs -> System.out.println(bugs.title));

    }

    private static void exSwitchIfEmptyTwo() {
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

    public static List<Bugs> bugsFromBackLock(){
        List<Bugs> bugsList = new ArrayList<>();
        bugsList.add(new Bugs(11,"Button not working",19));
        bugsList.add(new Bugs(98,"TextView not working",12));
        bugsList.add(new Bugs(31,"Wrong Text Displayed",12));
        bugsList.add(new Bugs(52,"Crashes on launch",10));
        bugsList.add(new Bugs(8,"Login Takes lot of time",10));
        return  bugsList;
    }

    public static List<Bugs> activeBugs(){
        List<Bugs> bugsList = new ArrayList<>();
        bugsList.add(new Bugs(99,"Button Color not proper",11,true));
        bugsList.add(new Bugs(76,"Crash in production build",19,true));
        bugsList.add(new Bugs(88,"Scrolling crashes the application",19,true));
        bugsList.add(new Bugs(25,"Payment option not displayed",10,true));
        bugsList.add(new Bugs(34,"No option to logout",10,true));
        return  bugsList;
    }

}

class Bugs implements Comparable<Bugs>{
    int id = 0;
    boolean isFromActive;
    int assignedTo = 0;
    String title = "";

    Bugs(int id ,String title,int assignedTo,boolean isFromActive){
        this.id = id;
        this.title = title;
        this.assignedTo = assignedTo;
        this.isFromActive = isFromActive;
    }

    Bugs(int id ,String title,int assignedTo){
        this.id = id;
        this.title = title;
        this.assignedTo = assignedTo;
    }


    @Override
    public int compareTo(Bugs o) {
        if(id == o.id){
            return 0;
        }
        return id > o.id ? 1 : -1;
    }
}