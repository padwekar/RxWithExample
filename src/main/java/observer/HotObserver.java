package observer;

/*
    Just like a radio station, if you tune in too late, you will have missed that song.
 */

/*
    The events can carry data with them, but there is a time-sensitive component where late observers can miss
    previously emitted data.
 */

/*
    UI events on JavaFX and Android are prime examples of hot Observables, but you can also
    use hot Observables to reflect server requests.
 */


import io.reactivex.Observable;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

public class HotObserver {

    public static void main(String...args){

        final EventListener[] eveListener = {null};

         Observable.create(emitter -> {
            EventListener eventListener = evt -> emitter.onNext((long)evt.getEventPhase());
            eveListener[0] = eventListener;
        }).doOnNext(System.out::print);

        Observable.just(1,2,3,4,5,6,7,8).filter(num -> num % 2 == 0).subscribe(
                num -> eveListener[0].handleEvent(new DummyEvent(num))
        );
    }
}

class DummyEvent implements Event {

    int recentValue = 0;

    DummyEvent(int value){
        recentValue = value;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public EventTarget getTarget() {
        return null;
    }

    @Override
    public EventTarget getCurrentTarget() {
        return null;
    }

    @Override
    public short getEventPhase() {
        return (short) recentValue;
    }

    @Override
    public boolean getBubbles() {
        return false;
    }

    @Override
    public boolean getCancelable() {
        return false;
    }

    @Override
    public long getTimeStamp() {
        return 0;
    }

    @Override
    public void stopPropagation() {

    }

    @Override
    public void preventDefault() {

    }

    @Override
    public void initEvent(String eventTypeArg, boolean canBubbleArg, boolean cancelableArg) {

    }
}
