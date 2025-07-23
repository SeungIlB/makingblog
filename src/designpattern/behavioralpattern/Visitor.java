package designpattern.behavioralpattern;

interface Element {
    void accept(Visitor v);
}

class Book implements Element {
    public void accept(Visitor v) { v.visit(this); }
    public String getTitle() { return "디자인 패턴 책"; }
}

interface Visitor {
    void visit(Book b);
}

class PriceCalculator implements Visitor {
    public void visit(Book b) {
        System.out.println(b.getTitle() + " 가격은 30000원입니다.");
    }
}
