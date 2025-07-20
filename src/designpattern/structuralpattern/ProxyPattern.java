package designpattern.structuralpattern;

/**
 * [Subject] RealSubject와 Proxy가 구현해야 하는 공통 인터페이스입니다.
 * 클라이언트는 이 인터페이스를 통해 실제 객체와 프록시 객체를 동일한 방식으로 다룰 수 있습니다.
 */
interface Image {
    void display();
}

// ----------------------------------------------------------------

/**
 * [RealSubject] 실제 작업을 수행하는 무거운 객체입니다.
 * 이 예제에서는 이미지 파일을 디스크에서 로드하는 비용이 많이 드는 작업을 시뮬레이션합니다.
 */
class RealImage implements Image {
    private final String filename;

    public RealImage(String filename) {
        this.filename = filename;
        // 생성 시점에 바로 리소스를 로드하는 무거운 작업을 수행합니다.
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println(" => [RealImage] 디스크에서 이미지 로딩 중...: " + filename);
    }

    @Override
    public void display() {
        System.out.println(" => [RealImage] 이미지 출력: " + filename);
    }
}

// ----------------------------------------------------------------

/**
 * [Proxy] RealSubject를 대리하는 객체입니다.
 * RealSubject와 동일한 인터페이스를 구현하며, 클라이언트의 요청을 제어합니다.
 * 이 예제는 '가상 프록시(Virtual Proxy)'로, 실제 객체의 생성을 필요한 시점까지 지연시킵니다.
 */
class ProxyImage implements Image {
    private RealImage realImage; // RealSubject에 대한 참조
    private final String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
        System.out.println("[ProxyImage] 프록시 객체 생성 완료. 아직 실제 이미지는 로드되지 않았습니다.");
    }

    @Override
    public void display() {
        System.out.println("[ProxyImage] display() 요청 받음.");
        // 실제 객체가 아직 생성되지 않았을 경우에만 생성합니다. (지연 초기화, Lazy Initialization)
        if (realImage == null) {
            System.out.println("[ProxyImage] 실제 이미지가 필요하므로 RealImage 객체를 생성합니다.");
            this.realImage = new RealImage(filename);
        }
        // 실제 객체에 작업을 위임합니다.
        this.realImage.display();
    }
}

// ----------------------------------------------------------------

/**
 * [Client] Proxy 패턴을 사용하는 클라이언트 코드입니다.
 */
public class ProxyPattern {
    public static void main(String[] args) {
        // 1. 클라이언트는 실제 이미지가 아닌 프록시 이미지를 생성합니다.
        //    이 시점에서는 비용이 큰 '이미지 로딩' 작업이 발생하지 않습니다.
        Image image = new ProxyImage("design-pattern.png");
        System.out.println("----------------------------------------------");

        // 2. 이미지를 처음 화면에 표시하도록 요청합니다.
        //    이때 프록시는 실제 이미지 객체(RealImage)를 생성하고, 로딩이 발생합니다.
        System.out.println("클라이언트: 첫 번째 이미지 표시 요청...");
        image.display();
        System.out.println("----------------------------------------------");

        // 3. 이미지를 다시 화면에 표시하도록 요청합니다.
        //    프록시는 이미 생성된 실제 이미지 객체를 재사용하므로, 추가 로딩 없이 바로 표시합니다.
        System.out.println("클라이언트: 두 번째 이미지 표시 요청...");
        image.display();
        System.out.println("----------------------------------------------");
    }
}
