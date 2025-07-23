package designpattern.behavioralpattern;

interface Expression {
    boolean interpret(String context);
}

class TerminalExpression implements Expression {
    private String data;
    public TerminalExpression(String data) { this.data = data; }
    public boolean interpret(String context) {
        return context.contains(data);
    }
}

class OrExpression implements Expression {
    private Expression expr1, expr2;
    public OrExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1; this.expr2 = expr2;
    }
    public boolean interpret(String context) {
        return expr1.interpret(context) || expr2.interpret(context);
    }
}

public class Interpreter {
    public static void main(String[] args) {
        Expression cat = new TerminalExpression("cat");
        Expression dog = new TerminalExpression("dog");
        Expression catOrDog = new OrExpression(cat, dog);

        String test1 = "I have a cat.";
        String test2 = "I like dogs.";
        String test3 = "I have a bird.";

        System.out.println("\"" + test1 + "\" contains cat or dog? " + catOrDog.interpret(test1));
        System.out.println("\"" + test2 + "\" contains cat or dog? " + catOrDog.interpret(test2));
        System.out.println("\"" + test3 + "\" contains cat or dog? " + catOrDog.interpret(test3));
    }
}