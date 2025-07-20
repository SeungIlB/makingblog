package designpattern.structuralpattern;

// 구현부 인터페이스 (Implementor)
// TV의 기본 동작을 정의합니다.
interface TV {
    void on();
    void off();
    void tuneChannel(int channel);
}

// 구현체 A - 삼성 TV
class SamsungTV implements TV {
    public void on() {
        System.out.println("🔵 Samsung TV is ON");
    }

    public void off() {
        System.out.println("🔵 Samsung TV is OFF");
    }

    public void tuneChannel(int channel) {
        System.out.println("🔵 Samsung TV: Switched to channel " + channel);
    }
}

// 구현체 B - LG TV
class LgTV implements TV {
    public void on() {
        System.out.println("🟢 LG TV is ON");
    }

    public void off() {
        System.out.println("🟢 LG TV is OFF");
    }

    public void tuneChannel(int channel) {
        System.out.println("🟢 LG TV: Switched to channel " + channel);
    }
}

// 추상화 계층 (Abstraction)
// 리모컨의 기본 기능 정의. 구현부(TV)와 연결.
abstract class RemoteControl {
    protected TV tv;

    public RemoteControl(TV tv) {
        this.tv = tv;
    }

    abstract void turnOn();
    abstract void turnOff();
    abstract void setChannel(int ch);
}

// 확장된 추상화 (Refined Abstraction)
// 리모컨의 구체적 구현 (기본형)
class BasicRemote extends RemoteControl {
    public BasicRemote(TV tv) {
        super(tv);
    }

    public void turnOn() {
        tv.on();
    }

    public void turnOff() {
        tv.off();
    }

    public void setChannel(int ch) {
        tv.tuneChannel(ch);
    }
}

// 클라이언트
public class BridgePattern {
    public static void main(String[] args) {
        // 삼성 TV에 대한 리모컨 사용
        TV samsung = new SamsungTV();
        RemoteControl remote1 = new BasicRemote(samsung);
        remote1.turnOn();
        remote1.setChannel(7);
        remote1.turnOff();

        System.out.println();

        // LG TV에 대한 리모컨 사용
        TV lg = new LgTV();
        RemoteControl remote2 = new BasicRemote(lg);
        remote2.turnOn();
        remote2.setChannel(11);
        remote2.turnOff();
    }
}
