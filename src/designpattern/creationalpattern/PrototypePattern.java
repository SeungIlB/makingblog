package designpattern.creationalpattern;

// Prototype 인터페이스 또는 추상 클래스 (여기서는 Cloneable 인터페이스 활용)
class Sheep implements Cloneable {
    private String name;
    private String color; // 새로운 속성 추가

    public Sheep(String name, String color) {
        this.name = name;
        this.color = color;
        System.out.println(name + " 양이 태어났습니다.");
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setColor(String color) { this.color = color; }
    public String getColor() { return color; }

    // clone 메서드를 오버라이드하여 객체 복제 로직 구현
    @Override
    public Sheep clone() {
        try {
            // super.clone()을 호출하여 얕은 복사 수행
            return (Sheep) super.clone();
        } catch (CloneNotSupportedException e) {
            // CloneNotSupportedException은 Cloneable 인터페이스를 구현하지 않았을 때 발생
            throw new RuntimeException("복제할 수 없습니다.", e);
        }
    }
}

// 테스트 코드
public class PrototypePattern {
    public static void main(String[] args) {
        // 원본 양 생성 (복잡한 초기화 과정이 있다고 가정)
        Sheep originalSheep = new Sheep("돌리", "하얀색");
        System.out.println("원본 양: " + originalSheep.getName() + " (" + originalSheep.getColor() + ")");

        // 원본 양을 복제하여 새로운 양 생성
        Sheep clonedSheep1 = originalSheep.clone();
        clonedSheep1.setName("복제 돌리 1");
        System.out.println("복제 양 1: " + clonedSheep1.getName() + " (" + clonedSheep1.getColor() + ")");

        Sheep clonedSheep2 = originalSheep.clone();
        clonedSheep2.setName("복제 돌리 2");
        clonedSheep2.setColor("검은색"); // 복제본의 속성 변경
        System.out.println("복제 양 2: " + clonedSheep2.getName() + " (" + clonedSheep2.getColor() + ")");

        // 원본 양은 변하지 않음을 확인
        System.out.println("원본 양 (변경 없음): " + originalSheep.getName() + " (" + originalSheep.getColor() + ")");

        System.out.println("\n원본과 복제본은 다른 객체인가? " + (originalSheep != clonedSheep1));
        System.out.println("복제본 1과 복제본 2는 다른 객체인가? " + (clonedSheep1 != clonedSheep2));
    }
}