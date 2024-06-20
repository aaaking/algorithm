package testrxjava;

import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxjavaDemo {
    private RxjavaDemo() {
    }

    private static class Holder {
        private static RxjavaDemo INSTANCE = new RxjavaDemo();
    }

    public static RxjavaDemo get() {
        return Holder.INSTANCE;
    }

    public void executeText() {
        System.out.println("-------------------------------------stat test rxjava " + Thread.currentThread());
        Observable motherCallEat = createMotherCallEatObservable();
        Observer observer = motherCallEat.subscribeWith(createChildObserver());
    }

    private Observable createMotherCallEatObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                // 2 sub thread
                System.out.println("subscribe emitter=" + "<warn: you cannot print this emitter object, or will not execute on next> " + " " + Thread.currentThread() + " " + Thread.currentThread().getId());
                emitter.onNext("baby ,it's time for lunch ");
            }
        }).subscribeOn(Schedulers.computation());
    }

    private Observer createChildObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                // 1 mainthread
                System.out.println("on subscribe disposable=" + d + " " + Thread.currentThread() + " " + Thread.currentThread().getId());
            }

            @Override
            public void onNext(String str) {
                // 3 sub thread
                System.out.println("on next str=<" + str + "> " + Thread.currentThread() + " " + Thread.currentThread().getId());
            }

            @Override
            public void onError(Throwable e) {
                //事件出错会调用这个方法
            }

            @Override
            public void onComplete() {
                System.out.println("on compleete " + Thread.currentThread() + " " + Thread.currentThread().getId());
            }
        };
    }
}
