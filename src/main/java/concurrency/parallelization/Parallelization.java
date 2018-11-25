package concurrency.parallelization;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import operator.suppressing.Take;

import java.time.LocalTime;

public class Parallelization {
    /*
       Parallelization : In terms of rxjava handling emissions concurrently instead of serially.
       But the observable contract dictates emissions must be pushed serially and must not be race
       each other due to concurrency.
    */
    public static void main(String... args) {
        exParallelizationThree();
    }

    private static void exParallelizationOne() {
        /*
            Parallelization : Inorder to process multiple emissions at once. Rxjava provides
            some really helpful operators using them we can achieve the same.
        */

        Observable.just(1,2,3,4,5,6,7,8,9,10)
                .map(num -> heavyMathematicalOperation(num))
                .subscribe(emission -> System.out.println("Received emission "+emission+ " "+ LocalTime.now()));


        /*
            It took 10 seconds to complete the operation. Want to achieve it faster switch to 'exParallelizationTwo()'
         */
        /* OUTPUT :
            Received emission 1 20:16:34.870683
            Received emission 2 20:16:35.875878
            Received emission 3 20:16:36.877194
            Received emission 4 20:16:37.881541
            Received emission 5 20:16:38.886644
            Received emission 6 20:16:39.889423
            Received emission 7 20:16:40.891909
            Received emission 8 20:16:41.892883
            Received emission 9 20:16:42.897149
            Received emission 10 20:16:43.900843

         */
    }

    private static void exParallelizationTwo() {
        /*
            Parallelization : By converting each emission into an separate Observable and Executing it concurrently can
            finish the operation much faster.
        */


        Observable.range(1,10)
                .flatMap(num -> Observable.just(num).map(emission -> heavyMathematicalOperation(emission))
                .subscribeOn(Schedulers.computation()))
                .subscribe(emission -> System.out.println("Received emission "+emission+ " "+ LocalTime.now()));


        Take.sleep(20000);

        /*
            The operation got completed in just 3 seconds. If you feel like creating an observable for each emission
            is heavy there is another solution too. Refer to 'exParallelizationThree()'.
         */
        /* OUTPUT :
        Received emission 5 20:25:33.532723
        Received emission 1 20:25:33.533273
        Received emission 2 20:25:33.533344
        Received emission 3 20:25:33.533425
        Received emission 4 20:25:33.533481
        Received emission 6 20:25:33.533539
        Received emission 7 20:25:33.533601
        Received emission 8 20:25:33.533657
        Received emission 10 20:25:34.510985
        Received emission 9 20:25:34.511210
         */
    }

    private static void exParallelizationThree(){
        /*
            Instead of creating a observable for each emission we can group the observable into desirable number.
            And execute them with limited number of thread.
         */

        Observable.range(1,20)
                  .groupBy(num -> num % 4)
                  .flatMap(grp -> grp.observeOn(Schedulers.computation()).map(Parallelization::heavyMathematicalOperation))
                  .subscribe(emission -> System.out.println("Received emission "+emission+ " "+ LocalTime.now() + Thread.currentThread().getName()));

        Take.sleep(20000);
    }

    private static Integer heavyMathematicalOperation(Integer num) {
        Take.sleep(1000);
        return num;
    }

}