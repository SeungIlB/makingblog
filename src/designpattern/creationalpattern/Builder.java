package designpattern.creationalpattern;

// Product: 생성될 복잡한 객체 (피자)
class Pizza {
    private String dough;
    private String sauce;
    private String topping;

    public void setDough(String dough) { this.dough = dough; }
    public void setSauce(String sauce) { this.sauce = sauce; }
    public void setTopping(String topping) { this.topping = topping; }

    public void showPizza() {
        System.out.println("완성된 피자: [도우: " + dough + ", 소스: " + sauce + ", 토핑: " + topping + "]");
    }
}

// Builder: Product의 각 부분을 생성하는 추상 인터페이스
interface PizzaBuilder {
    void buildDough();
    void buildSauce();
    void buildTopping();
    Pizza getPizza(); // 최종 Product 객체를 반환
}

// Concrete Builder: Builder 인터페이스를 구현하여 특정 Product의 부분을 생성
class SpicyPizzaBuilder implements PizzaBuilder {
    private Pizza pizza = new Pizza(); // 빌더가 생성할 피자 객체

    @Override
    public void buildDough() { pizza.setDough("씬 크러스트 도우"); }
    @Override
    public void buildSauce() { pizza.setSauce("매운 토마토 소스"); }
    @Override
    public void buildTopping() { pizza.setTopping("페퍼로니, 할라피뇨"); }
    @Override
    public Pizza getPizza() { return pizza; }
}

class VeggiePizzaBuilder implements PizzaBuilder {
    private Pizza pizza = new Pizza();

    @Override
    public void buildDough() { pizza.setDough("오리지널 도우"); }
    @Override
    public void buildSauce() { pizza.setSauce("바질 페스토 소스"); }
    @Override
    public void buildTopping() { pizza.setTopping("버섯, 올리브, 피망"); }
    @Override
    public Pizza getPizza() { return pizza; }
}

// Director: Builder를 사용하여 Product 객체를 구성하는 역할
class PizzaDirector {
    public void constructPizza(PizzaBuilder pizzaBuilder) {
        pizzaBuilder.buildDough();
        pizzaBuilder.buildSauce();
        pizzaBuilder.buildTopping();
    }
}

// 테스트 코드
public class Builder {
    public static void main(String[] args) {
        PizzaDirector director = new PizzaDirector();

        // 매운 피자 만들기
        PizzaBuilder spicyBuilder = new SpicyPizzaBuilder();
        director.constructPizza(spicyBuilder);
        Pizza spicyPizza = spicyBuilder.getPizza();
        spicyPizza.showPizza();

        // 야채 피자 만들기
        PizzaBuilder veggieBuilder = new VeggiePizzaBuilder();
        director.constructPizza(veggieBuilder);
        Pizza veggiePizza = veggieBuilder.getPizza();
        veggiePizza.showPizza();
    }
}