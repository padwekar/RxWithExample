package operator;


import io.reactivex.Observable;

public class FromCallable {

    public static void main(String... args) {
        withCallable();
    }

    private static void withOutCallable() {
        //The action 1/0 is not happening in the lazy manner.
        //The error is not propogated through the observable chain.
        Observable.just(1 / 0).subscribe(System.out::println, err -> System.out.println("Error Captured " + err.getLocalizedMessage()), () -> System.out.println("Done"));
    }

    private static void withCallable() {
        //The action 1/0 will happen in lazy manner.
        // Error will be propogated through the observable chain.
        Observable.fromCallable(() -> {
            int m = 1 / 0;
            return m;
        }).subscribe(System.out::println,
                     err -> System.out.println("Error Captured " + err.getLocalizedMessage()),
                     () -> System.out.println("Done"));

    }

}