package designpattern.structuralpattern;

/**
 * [Adaptee] 기존에 사용되던 클래스입니다.
 * 클라이언트가 직접 사용하기에는 인터페이스가 호환되지 않는 대상입니다.
 */
class OldPrinter {
    void printOld(String msg) {
        System.out.println("구형 프린터: " + msg);
    }
}

// ----------------------------------------------------------------

/**
 * [Target] 클라이언트가 사용하고자 하는 새로운 인터페이스입니다.
 */
interface Printer {
    void print(String msg);
}

// ----------------------------------------------------------------

/**
 * [Adapter] OldPrinter(Adaptee)를 Printer(Target) 인터페이스에 맞춰주는 어댑터 클래스입니다.
 * - Printer 인터페이스를 구현합니다.
 * - 내부에 OldPrinter 인스턴스를 가집니다.
 * - print() 메소드가 호출되면, 내부의 OldPrinter 인스턴스의 printOld() 메소드를 호출하여 작업을 위임합니다.
 */
class PrinterAdapter implements Printer {
    private OldPrinter oldPrinter; // Adaptee 인스턴스

    // 생성자를 통해 Adaptee 인스턴스를 주입받습니다.
    public PrinterAdapter(OldPrinter oldPrinter) {
        this.oldPrinter = oldPrinter;
    }

    @Override
    public void print(String msg) {
        // Target 인터페이스의 메소드(print) 호출을 Adaptee의 메소드(printOld)로 변환하여 호출합니다.
        System.out.println("어댑터가 호출되었습니다.");
        oldPrinter.printOld(msg);
    }
}

// ----------------------------------------------------------------


public class AdapterPattern {
    public static void main(String[] args) {
        // 1. 기존에 사용하던 구형 프린터(Adaptee) 객체를 생성합니다.
        System.out.println("1. 구형 프린터 객체 생성");
        OldPrinter oldPrinter = new OldPrinter();

        // 2. 구형 프린터를 새로운 인터페이스(Printer)에 맞게 변환해 줄 어댑터를 생성합니다.
        System.out.println("2. 어댑터 객체 생성 (구형 프린터 장착)");
        Printer printer = new PrinterAdapter(oldPrinter);

        // 3. 클라이언트는 새로운 인터페이스(Printer)를 통해 작업을 요청합니다.
        //    내부적으로는 어댑터를 통해 구형 프린터가 동작하게 됩니다.
        System.out.println("3. 새로운 규격의 print() 메소드 호출");
        printer.print("Hello Adapter Pattern!");

        System.out.println("\n테스트 완료: 클라이언트는 구형 프린터의 존재를 모르고도 작업을 수행했습니다.");
    }
}