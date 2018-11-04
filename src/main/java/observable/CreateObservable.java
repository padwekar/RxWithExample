package observable;


import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;

public class CreateObservable {
    public static void main(String...args){
        emittingFromIterable();
    }

    public static void combiningOperatorsExpanded(){
        Observable<String> stringObservable = Observable.create(emitter -> {

            try {
                emitter.onNext("Saurabh"); // Way to hand each item
                emitter.onNext("Padwekar");
                emitter.onNext("Savi");
                emitter.onNext("Healer");
                emitter.onComplete();
            }catch (Exception e){
                emitter.onError(e);
            }
        });

        Observable<Integer> integerObservable = stringObservable.map(v -> v.length());

        Observable<Integer> filteredIntegerObservable = integerObservable.filter(num -> num > 5);

        filteredIntegerObservable.subscribe(s -> System.out.println("Received "+s), Throwable::printStackTrace);
    }

    //Using Create
    public static void combiningOperatorShorten(){
       Observable<String> stringObservable = Observable.create(emitter -> {

            try {
                emitter.onNext("Saurabh"); // Way to hand each item
                emitter.onNext("Padwekar");
                emitter.onNext("Savi");
                emitter.onNext("Healer");
                emitter.onComplete();
            }catch (Exception e){
                emitter.onError(e);
            }
        });

       stringObservable.map(data -> data.length()).filter(length -> length > 3).subscribe(System.out::println);
    }

    //Using Just - When you have fixed known number of emission to make
    public static void combiningOperatorShortenV2(){
        Observable<String> stringObservable = Observable.just("Saurabh","Padwekar","Healer","Savi");
        stringObservable.map(data -> data.length()).filter(length -> length > 3).subscribe(System.out::println);
    }

    private static void emittingFromIterable(){
        List<String>  list = new ArrayList<>();
        list.add("Saurabh");
        list.add("Healer");
        list.add("Savi");
        list.add("Healer");
        Observable.fromIterable(list).subscribe(System.out::println);
    }


}
