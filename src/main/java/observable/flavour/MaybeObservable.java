package observable.flavour;

import io.reactivex.Maybe;
import io.reactivex.Observable;

public class MaybeObservable {

    /*
    The Maybe class represents a deferred computation and emission of a single value, no value at all or an exception.

    Maybe is a special kind of Observable which can only emit zero or one item,
    and report an error if the computation fails at some point.


     */
    public static void main(String... args) {

        /*
            OnComplete is not called after onSuccess in single maybe.
         */
        Maybe<Integer> singleMayBe = Maybe.just(1);
        singleMayBe.subscribe(result -> System.out.println(result),Throwable::printStackTrace,() -> System.out.print("Done"));

        Maybe<Integer> emptyMayBe = Maybe.empty();
        emptyMayBe.subscribe(result -> System.out.println(result),Throwable::printStackTrace,() -> System.out.print("Done"));

    }

}