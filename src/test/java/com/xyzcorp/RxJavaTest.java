package com.xyzcorp;

import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class RxJavaTest {

    @Test
    public void testBasicObservable() {
        Observable.just(1,2,3,4)
                  .map(x -> x + 3)
                  .filter(x -> x % 2 != 0)
                  .subscribe(integer -> System.out.println("On Next:" + integer),
                          Throwable::printStackTrace,
                          () -> System.out.println("Done"));
    }

    @Test
    public void testIntervalWithObservable() throws InterruptedException {
        Observable<Long> observable = Observable
                .interval(1, TimeUnit.SECONDS)
                .map(x -> x + 1)
                .filter(x -> x % 2 != 0);

        observable.subscribe(System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("Done"));


        observable
                .flatMap(x -> Observable.just(-x, x, x+1))
                .subscribe(System.out::println,
                        Throwable::printStackTrace,
                        () -> System.out.println("Done"));

        Thread.sleep(10000);
    }
}
