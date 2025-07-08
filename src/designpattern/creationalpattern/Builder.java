package designpattern.creationalpattern;

// Product
class Pizza{
    private String dough;
    private String sauce;
    private String topping;

    public void setDough(String dough) {this.dough = dough;}
    public void setSauce(String sauce) {this.sauce = sauce;}
    public void setTopping(String topping) {this.topping = topping;}

    public void show() {
        System.out.println("Pizza [" + dough + "," + sauce + "," + topping + "]");
    }
}

// Builder
interface PizzaBuilder{
    void buildDough();
    void buildSauce();
    void buildTopping();
    Pizza getPizza();
}

// Concrete Builder
class SpicyPizzaBuilder implements PizzaBuilder{
    private Pizza pizza = new Pizza();

    public void buildDough() { pizza.setDough("두꺼운 도우"); }
    public void buildSauce() { pizza.setSauce("매운 소스"); }
    public void buildTopping() { pizza.setTopping("불고기"); }
    public Pizza getPizza() { return pizza; }
}

// Director
class PizzaDirector{
    public void construct(PizzaBuilder pizzaBuilder){
        pizzaBuilder.buildDough();
        pizzaBuilder.buildSauce();
        pizzaBuilder.buildTopping();
    }
}

public class Builder {
    public static void main(String[] args){
        PizzaBuilder builder = new SpicyPizzaBuilder();
        PizzaDirector director = new PizzaDirector();
        director.construct(builder);

        Pizza pizza = builder.getPizza();
        pizza.show();
    }
}
