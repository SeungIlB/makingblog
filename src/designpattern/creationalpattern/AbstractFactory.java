package designpattern.creationalpattern;

// Abstract Product: 생성될 객체들의 공통 인터페이스
interface Button {
    void click();
}

interface TextBox {
    void type(String text);
}

// Concrete Product - Light Theme: Light 테마의 실제 객체들
class LightButton implements Button {
    @Override
    public void click() {
        System.out.println("라이트 버튼 클릭!");
    }
}

class LightTextBox implements TextBox {
    @Override
    public void type(String text) {
        System.out.println("라이트 텍스트 박스에 입력: " + text);
    }
}

// Concrete Product - Dark Theme: Dark 테마의 실제 객체들
class DarkButton implements Button {
    @Override
    public void click() {
        System.out.println("다크 버튼 클릭!");
    }
}

class DarkTextBox implements TextBox {
    @Override
    public void type(String text) {
        System.out.println("다크 텍스트 박스에 입력: " + text);
    }
}

// Abstract Factory: 객체 패밀리를 생성하는 추상 팩토리
interface GUIFactory {
    Button createButton();
    TextBox createTextBox();
}

// Concrete Factory - Light: Light 테마 객체들을 생성하는 구체 팩토리
class LightFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new LightButton();
    }
    @Override
    public TextBox createTextBox() {
        return new LightTextBox();
    }
}

// Concrete Factory - Dark: Dark 테마 객체들을 생성하는 구체 팩토리
class DarkFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new DarkButton();
    }
    @Override
    public TextBox createTextBox() {
        return new DarkTextBox();
    }
}

// 테스트 코드
public class AbstractFactory {
    public static void main(String[] args) {
        // Dark 테마의 GUI 컴포넌트 생성
        System.out.println("--- Dark 테마 ---");
        GUIFactory darkFactory = new DarkFactory();
        Button darkButton = darkFactory.createButton();
        TextBox darkTextBox = darkFactory.createTextBox();

        darkButton.click();
        darkTextBox.type("밤에는 어두운 테마가 최고!");

        System.out.println("\n--- Light 테마 ---");
        // Light 테마의 GUI 컴포넌트 생성 (팩토리만 교체)
        GUIFactory lightFactory = new LightFactory();
        Button lightButton = lightFactory.createButton();
        TextBox lightTextBox = lightFactory.createTextBox();

        lightButton.click();
        lightTextBox.type("낮에는 밝은 테마가 좋지!");
    }
}