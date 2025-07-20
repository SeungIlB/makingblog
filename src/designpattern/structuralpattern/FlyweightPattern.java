package designpattern.structuralpattern;

import java.util.HashMap;
import java.util.Map;

// ===========================
// 1. Flyweight 인터페이스
// ===========================
interface Shape {
    void draw(String color); // 외부 상태 (extrinsic): 실행 시 주입
}

// ===========================
// 2. ConcreteFlyweight 클래스
// ===========================
class Circle implements Shape {
    private final String type = "Circle"; // 내부 상태 (intrinsic): 공유 대상

    @Override
    public void draw(String color) {
        // 외부 상태는 전달받아서 출력
        System.out.println("🔵 Drawing " + type + " with color: " + color);
    }
}

// ===========================
// 3. Flyweight Factory
// ===========================
class ShapeFactory {
    // 이미 생성된 공유 객체 저장소 (캐싱)
    private static final Map<String, Shape> shapeMap = new HashMap<>();

    // 동일한 타입의 Shape 객체를 재사용
    public static Shape getCircle() {
        String key = "circle";

        // 없으면 새로 생성해서 캐시에 저장
        if (!shapeMap.containsKey(key)) {
            shapeMap.put(key, new Circle());
            System.out.println("🆕 Creating new Circle object");
        }

        return shapeMap.get(key); // 이미 있으면 공유 객체 반환
    }
}

// ===========================
// 4. Client 사용 예시
// ===========================
public class FlyweightPattern {
    public static void main(String[] args) {
        System.out.println("🎨 Start Drawing Circles with Shared Instances");

        String[] colors = {"Red", "Blue", "Green"};

        // 동일한 Circle 객체를 공유하면서 다른 색상으로 그리기
        for (int i = 0; i < 6; i++) {
            Shape circle = ShapeFactory.getCircle();        // 공유 객체 얻기
            String color = colors[i % colors.length];       // 외부 속성 선택
            circle.draw(color);                             // 실행 시 외부 속성 주입
        }
    }
}

