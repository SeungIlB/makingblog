package designpattern.behavioralpattern;

abstract class Handler {
    protected Handler next;
    public void setNext(Handler next) { this.next = next; }
    public abstract void handleRequest(String request);
}

class Manager extends Handler {
    public void handleRequest(String request) {
        if (request.equals("휴가")) System.out.println("Manager: 휴가 승인");
        else if (next != null) next.handleRequest(request);
    }
}

class Director extends Handler {
    public void handleRequest(String request) {
        if (request.equals("급여 인상")) System.out.println("Director: 급여 인상 승인");
        else if (next != null) next.handleRequest(request);
    }
}

public class ChainofResponsibilityPattern {
    public static void main(String[] args) {
        Handler manager = new Manager();
        Handler director = new Director();
        manager.setNext(director);
        manager.handleRequest("급여 인상");
    }
}
