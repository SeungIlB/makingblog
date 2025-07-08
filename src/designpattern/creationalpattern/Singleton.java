package designpattern.creationalpattern;

public class Singleton {
    // volatile 키워드를 추가하여 멀티스레드 환경에서의 가시성 문제 해결
    private static volatile Singleton instance;

    // private 생성자로 외부에서 직접적인 객체 생성을 막음
    private Singleton() {
        System.out.println("Singleton 객체 생성!");
        // 실제 애플리케이션에서는 여기에서 초기화 로직 수행
    }

    // 전역 접근 메서드 (Double-Checked Locking 방식)
    public static Singleton getInstance() {
        if (instance == null) { // 첫 번째 체크: 인스턴스가 없는 경우에만 동기화 블록 진입
            synchronized (Singleton.class) { // 동기화 블록: 여러 스레드가 동시에 접근하는 것을 막음
                if (instance == null) { // 두 번째 체크: 인스턴스가 실제로 없는지 다시 확인
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void sayHello() {
        System.out.println("안녕! 나는 싱글톤 객체입니다.");
    }

    // 테스트 코드
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        s1.sayHello();
        System.out.println("s1과 s2는 동일한 객체인가? " + (s1 == s2)); // true: 동일 객체임을 확인
    }
}