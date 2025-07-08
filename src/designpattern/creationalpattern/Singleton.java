package designpattern.creationalpattern;

public class Singleton {
    public static Singleton instance;

    // private 생성자
    private Singleton() {
        System.out.println("Singleton 생성!");
    }

    // 전역 접근 메서드
    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
    public void sayHello(){
        System.out.println("안녕! 나는 싱글톤 객체~");
    }

    // 테스트
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        s1.sayHello();
        System.out.println(s1 == s2);  // true: 동일 객체
    }
}
