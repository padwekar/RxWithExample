package observer;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HelloObserver {
    public static void main(String... args) {
        observerCreateShorten();
    }

    public static void observerCreate() {
        Observable<String> stringObservable = Observable.just("Hello", "Hi", "Tata", "Bye");

        Observer<Integer> intObserver = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done");
            }
        };

        stringObservable.map(String::length).filter(i -> i >= 5).subscribe(intObserver);
    }

    public static void observerCreateShorten() {

        Observable<String> stringObservable = Observable.just("Hello", "Hi", "Tata", "Bye");
        stringObservable.map(String::length).filter(i -> i >= 5).subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.print("Done"));

    }

    public static void observerCreateShortenSkipOnComplete() {
        Observable<String> stringObservable = Observable.just("Hello", "Hi", "Tata", "Bye");
        stringObservable.map(String::length).filter(i -> i >= 5).subscribe(System.out::println, Throwable::printStackTrace);
    }
}
