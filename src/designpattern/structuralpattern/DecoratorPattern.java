package designpattern.structuralpattern;

/**
 * [Component] 기본 객체와 장식(Decorator)을 하나로 묶는 공통 인터페이스입니다.
 */
interface Coffee {
    String getDescription();
    int cost();
}

// ----------------------------------------------------------------

/**
 * [Concrete Component] 장식될 기본 객체입니다.
 */
class BasicCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "기본 커피";
    }

    @Override
    public int cost() {
        return 3000;
    }
}

// ----------------------------------------------------------------

/**
 * [Decorator] Component 인터페이스를 구현하며, 내부에 Component 객체를 가지고 있습니다.
 * 이 추상 클래스는 모든 구체적인 데코레이터들의 부모 역할을 합니다.
 */
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee; // 장식할 대상이 되는 커피 객체

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    // 기본적으로는 장식 대상의 메서드를 그대로 호출합니다.
    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

    @Override
    public int cost() {
        return coffee.cost();
    }
}

// ----------------------------------------------------------------

/**
 * [Concrete Decorator] 구체적인 장식(책임)을 추가하는 클래스입니다.
 * 우유를 추가하는 역할을 합니다.
 */
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        // 기존 설명에 새로운 설명을 추가합니다.
        return super.getDescription() + ", 우유 추가";
    }

    @Override
    public int cost() {
        // 기존 가격에 새로운 가격을 추가합니다.
        return super.cost() + 500;
    }
}

/**
 * [Concrete Decorator] 구체적인 장식(책임)을 추가하는 클래스입니다.
 * 휘핑크림을 추가하는 역할을 합니다.
 */
class WhipDecorator extends CoffeeDecorator {
    public WhipDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", 휘핑크림 추가";
    }

    @Override
    public int cost() {
        return super.cost() + 700;
    }
}

// ----------------------------------------------------------------

/**
 * [Client] 데코레이터 패턴을 사용하는 클라이언트 코드입니다.
 */
public class DecoratorPattern {
    public static void main(String[] args) {
        // 1. 순수한 기본 커피 객체를 생성합니다.
        Coffee coffee = new BasicCoffee();
        System.out.println("====== 기본 커피 ======");
        System.out.println("주문: " + coffee.getDescription());
        System.out.println("가격: " + coffee.cost() + "원");
        System.out.println("----------------------------\n");

        // 2. 기본 커피에 '우유'를 추가합니다.
        // MilkDecorator로 기존 coffee 객체를 감쌉니다(Decorate).
        coffee = new MilkDecorator(coffee);
        System.out.println("====== 우유 추가 ======");
        System.out.println("주문: " + coffee.getDescription());
        System.out.println("가격: " + coffee.cost() + "원");
        System.out.println("----------------------------\n");

        // 3. 우유가 추가된 커피에 다시 '휘핑크림'을 추가합니다.
        // WhipDecorator로 현재 coffee 객체(MilkDecorator)를 다시 감쌉니다.
        coffee = new WhipDecorator(coffee);
        System.out.println("====== 휘핑크림 추가 ======");
        System.out.println("주문: " + coffee.getDescription());
        System.out.println("가격: " + coffee.cost() + "원");
        System.out.println("----------------------------\n");
    }
}