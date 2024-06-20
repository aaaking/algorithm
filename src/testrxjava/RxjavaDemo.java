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
        Observer observer = motherCallEat.subscribeOn(Schedulers.computation()).subscribeWith(createChildObserver());
    }

    private Observable createMotherCallEatObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                System.out.println("subscribe emitter=" + emitter + " " + Thread.currentThread()); // 2 sub thread
                emitter.onNext("baby ,it's time for lunch ");
            }
        });
    }

    private Observer createChildObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("on subscribe disposable=" + d + " " + Thread.currentThread()); // 1 mainthread
            }

            @Override
            public void onNext(String str) {
                //处理传递过来的事件
                System.out.println("on next str=<" + str + "> " + Thread.currentThread()); // 3 sub thread
            }

            @Override
            public void onError(Throwable e) {
                //事件出错会调用这个方法
            }

            @Override
            public void onComplete() {
                System.out.println("on compleete " + Thread.currentThread());
            }
        };
    }
}
