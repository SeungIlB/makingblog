package designpattern.behavioralpattern;

class Memento {
    private final String state;
    public Memento(String state) { this.state = state; }
    public String getState() { return state; }
}

class Originator {
    private String state;
    public void setState(String state) { this.state = state; }
    public String getState() { return state; }
    public Memento save() { return new Memento(state); }
    public void restore(Memento m) { this.state = m.getState(); }
}

class Caretaker {
    private Memento memento;
    public void saveState(Originator o) { memento = o.save(); }
    public void restoreState(Originator o) { o.restore(memento); }
}

public class MementoTest {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState("상태1");
        System.out.println("현재 상태: " + originator.getState());

        caretaker.saveState(originator);  // 상태 저장

        originator.setState("상태2");
        System.out.println("변경된 상태: " + originator.getState());

        caretaker.restoreState(originator);  // 이전 상태 복원
        System.out.println("복원된 상태: " + originator.getState());
    }
}
