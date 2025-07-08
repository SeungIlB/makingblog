package designpattern.creationalpattern;

// Cloneable 인터페이스 구현
class Sheep implements Cloneable {
    private String name;

    public Sheep(String name) {
        this.name = name;
    }

    public void setName(String name) {this.name = name;}
    public String getName() {return name;}

    @Override
    public Sheep clone(){
        try{
            return (Sheep) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class PrototypePattern {
    public static void main(String[] args) {
        Sheep original = new Sheep("Dolly");
        Sheep clone = original.clone();
        clone.setName("Clone Dolly");

        System.out.println("원본: " + original.getName());
        System.out.println("복제: " + clone.getName());
    }
}
