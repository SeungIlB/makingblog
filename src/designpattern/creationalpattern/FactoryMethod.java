package designpattern.creationalpattern;

// Product 인터페이스: 생성될 객체들의 공통 인터페이스
interface Animal {
    void speak();
}

// Concrete Product: 실제 생성될 객체들
class Dog implements Animal {
    @Override
    public void speak() {
        System.out.println("멍멍!");
    }
}

class Cat implements Animal {
    @Override
    public void speak() {
        System.out.println("야옹~");
    }
}

// Creator 추상 클래스: 팩토리 메서드를 선언
abstract class AnimalFactory {
    // 팩토리 메서드: 객체 생성을 담당하는 메서드 (하위 클래스에서 구현)
    public abstract Animal createAnimal();

    // 팩토리 메서드를 활용하는 로직 (선택 사항)
    public void doSomethingWithAnimal() {
        Animal animal = createAnimal(); // 하위 클래스가 어떤 동물을 만들지 결정
        animal.speak();
    }
}

// Concrete Creator: 팩토리 메서드를 구현하여 특정 Product 객체 생성
class DogFactory extends AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Dog();
    }
}

class CatFactory extends AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Cat();
    }
}

// 테스트 코드
public class FactoryMethod {
    public static void main(String[] args) {
        AnimalFactory dogFactory = new DogFactory();
        Animal dog = dogFactory.createAnimal();
        dog.speak(); // 멍멍!

        AnimalFactory catFactory = new CatFactory();
        Animal cat = catFactory.createAnimal();
        cat.speak(); // 야옹!

        System.out.println("\n--- 팩토리 메서드를 활용한 추가 로직 ---");
        dogFactory.doSomethingWithAnimal();
        catFactory.doSomethingWithAnimal();
    }
}