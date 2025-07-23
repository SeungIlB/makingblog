package designpattern.behavioralpattern;

import java.util.ArrayList;
import java.util.List;

interface MediatorInterface {
    void sendMessage(String msg, Colleague sender);
}

abstract class Colleague {
    protected MediatorInterface mediator;
    public Colleague(MediatorInterface m) { this.mediator = m; }
}

class User1 extends Colleague {
    private String name;
    public User1(MediatorInterface m, String name) {
        super(m);
        this.name = name;
    }
    public void send(String msg) {
        System.out.println(this.name + " 보냄: " + msg);
        mediator.sendMessage(msg, this);
    }
    public void receive(String msg) {
        System.out.println(this.name + " 수신: " + msg);
    }
}

class ChatMediator implements MediatorInterface {
    private List<User1> users = new ArrayList<>();
    public void addUser(User1 user) { users.add(user); }
    public void sendMessage(String msg, Colleague sender) {
        for (User1 user : users)
            if (user != sender) user.receive(msg);
    }
}

public class Mediator {
    public static void main(String[] args) {
        ChatMediator mediator = new ChatMediator();

        User1 alice = new User1(mediator, "Alice");
        User1 bob = new User1(mediator, "Bob");
        User1 charlie = new User1(mediator, "Charlie");

        mediator.addUser(alice);
        mediator.addUser(bob);
        mediator.addUser(charlie);

        alice.send("안녕하세요, 모두들!");
        bob.send("안녕하세요 Alice!");
        charlie.send("안녕하세요 Alice와 Bob!");
    }
}
