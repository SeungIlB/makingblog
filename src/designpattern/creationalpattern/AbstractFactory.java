package designpattern.creationalpattern;

// Abstract Product
interface Button{
    void click();
}

interface TextBox{
    void type(String text);
}

// concrete Product - Light Theme
class LightButton implements Button{
    public void click() {
        System.out.println("Light 버튼 클릭");
    }
}

class LightTextBox implements TextBox{
    public void type(String text) {
        System.out.println("Light 텍스트: " + text);
    }
}

// Concrete Product - Dark Theme
class DarkButton implements Button{
    public void click() {
        System.out.println("Dark 버튼 클릭");
    }
}

class DarkTextBox implements TextBox{
    public void type(String text) {
        System.out.println("Dark 텍스트: " + text);
    }
}

// Abstract Factory
interface GUIFactory{
    Button createButton();
    TextBox createTextBox();
}

// Concrete Factory - Light
class LightFactory implements GUIFactory{
    public Button createButton(){
        return new LightButton();
    }
    public TextBox createTextBox(){
        return new LightTextBox();
    }
}

// Concrete Factory - Dark
class DarkFactory implements GUIFactory {
    public Button createButton() {
        return new DarkButton();
    }
    public TextBox createTextBox() {
        return new DarkTextBox();
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        GUIFactory factory = new DarkFactory(); // DarkFactory로 교체 가능

        Button button = factory.createButton();
        TextBox textBox = factory.createTextBox();

        button.click();
        textBox.type("디자인 패턴 어렵다!");
    }
}
