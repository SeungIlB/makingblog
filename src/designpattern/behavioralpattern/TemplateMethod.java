package designpattern.behavioralpattern;

abstract class Game {
    // 템플릿 메서드 - 알고리즘의 뼈대 정의
    public final void play() {
        initialize();      // 초기화
        startPlay();       // 실제 게임 시작
        endPlay();         // 게임 종료
    }

    protected abstract void initialize();
    protected abstract void startPlay();
    protected abstract void endPlay();
}

class Football extends Game {
    @Override
    protected void initialize() {
        System.out.println("축구 게임: 선수 입장 및 워밍업");
    }

    @Override
    protected void startPlay() {
        System.out.println("축구 게임: 킥오프!");
    }

    @Override
    protected void endPlay() {
        System.out.println("축구 게임: 경기 종료 및 기록 정리");
    }
}

class Baseball extends Game {
    @Override
    protected void initialize() {
        System.out.println("야구 게임: 선수 정렬 및 연습");
    }

    @Override
    protected void startPlay() {
        System.out.println("야구 게임: 플레이 볼!");
    }

    @Override
    protected void endPlay() {
        System.out.println("야구 게임: 경기 종료 및 인터뷰");
    }
}

public class TemplateMethod {
    public static void main(String[] args) {
        Game game = new Football();
        game.play();

        System.out.println();

        game = new Baseball();
        game.play();
    }
}

