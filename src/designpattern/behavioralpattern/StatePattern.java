package designpattern.behavioralpattern;

// State 인터페이스
interface State {
    void pressPowerButton(PhoneContext context);
    void pressHomeButton(PhoneContext context);
}

// 구체 상태: 전원 꺼짐 상태
class PowerOffState implements State {
    public void pressPowerButton(PhoneContext context) {
        System.out.println("전원 켜짐");
        context.setState(new StandByState());
    }

    public void pressHomeButton(PhoneContext context) {
        System.out.println("전원이 꺼져 있어 아무 동작 없음");
    }
}

// 구체 상태: 대기 상태
class StandByState implements State {
    public void pressPowerButton(PhoneContext context) {
        System.out.println("전원 꺼짐");
        context.setState(new PowerOffState());
    }

    public void pressHomeButton(PhoneContext context) {
        System.out.println("홈 화면으로 이동");
        context.setState(new CallState());
    }
}

// 구체 상태: 통화 중 상태
class CallState implements State {
    public void pressPowerButton(PhoneContext context) {
        System.out.println("통화 종료 및 전원 꺼짐");
        context.setState(new PowerOffState());
    }

    public void pressHomeButton(PhoneContext context) {
        System.out.println("통화 중 홈 버튼 눌림 - 통화 유지");
    }
}

// Context
class PhoneContext {
    private State state;

    public PhoneContext() {
        this.state = new PowerOffState(); // 초기 상태: 전원 꺼짐
    }

    public void setState(State state) {
        this.state = state;
        System.out.println("현재 상태: " + state.getClass().getSimpleName());
    }

    public void pressPowerButton() {
        state.pressPowerButton(this);
    }

    public void pressHomeButton() {
        state.pressHomeButton(this);
    }
}

// 실행
public class StatePattern {
    public static void main(String[] args) {
        PhoneContext phone = new PhoneContext();

        phone.pressPowerButton();  // 전원 켜짐 -> 대기 상태
        phone.pressHomeButton();   // 홈 화면 -> 통화 상태
        phone.pressHomeButton();   // 통화 중 홈 버튼 눌림
        phone.pressPowerButton();  // 통화 종료 및 전원 꺼짐
    }
}

