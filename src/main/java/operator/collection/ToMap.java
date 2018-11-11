package operator.collection;

import io.reactivex.Observable;
import model.Bugs;
import operator.transforming.SwitchIfEmpty;

import javax.swing.text.html.parser.Entity;
import java.util.Map;

public class ToMap {
    /*
       ToMap : Is used to create a map from the emission based on the condition you have provided.
    */

    public static void main(String... args) {
        egToMapOne();
    }

    private static void egToMapOne() {
        /*
            Here ToMap
        */
        Observable.fromIterable(Bugs.activeBugs()).toMap(bugs -> bugs.id).subscribe(map -> {
            for(Map.Entry<Integer,Bugs> entity : map.entrySet()){
                System.out.println(entity.getKey());
            }
        });
    }

    private static void egToMapTwo() {
        /*
            Here ToMap
        */
    }

}