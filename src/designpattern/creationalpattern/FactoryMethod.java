package designpattern.creationalpattern;

// Product 인터페이스
interface Animal{
    void speak();
}

// Concrete Product
class Dog implements Animal{
    public void speak(){
        System.out.println("멍멍!");
    }
}

class Cat implements Animal{
    public void speak(){
        System.out.println("야옹~");
    }
}

// Creator
abstract class AnimalFactory{
    public abstract Animal createAnimal();
}
class DogFactory extends AnimalFactory{
    public Animal createAnimal(){
        return new Dog();
    }
}

class CatFactory extends AnimalFactory{
    public Animal createAnimal(){
        return new Cat();
    }
}

public class FactoryMethod {
    public static void main(String[] args) {
        AnimalFactory factory = new DogFactory();
        Animal dog = factory.createAnimal();
        dog.speak(); // 멍멍!

        factory = new CatFactory();
        Animal cat = factory.createAnimal();
        cat.speak(); // 야옹!
    }
}
