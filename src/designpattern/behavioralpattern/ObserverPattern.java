package designpattern.behavioralpattern;

import java.util.ArrayList;
import java.util.List;

// 옵서버
interface Subscriber {
    void update(String videoTitle);
}

// 구체 옵서버
class User implements Subscriber {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void update(String videoTitle) {
        System.out.println(name + "님, 새 영상 '" + videoTitle + "'이(가) 업로드되었습니다!");
    }
}

// Subject
class YouTubeChannel {
    private List<Subscriber> subscribers = new ArrayList<>();
    private String channelName;

    public YouTubeChannel(String channelName) {
        this.channelName = channelName;
    }

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void uploadNewVideo(String title) {
        System.out.println("📢 [" + channelName + "] 새 영상 업로드됨: " + title);
        notifySubscribers(title);
    }

    private void notifySubscribers(String title) {
        for (Subscriber s : subscribers) {
            s.update(title);
        }
    }
}

// 실행
public class ObserverPattern {
    public static void main(String[] args) {
        YouTubeChannel channel = new YouTubeChannel("승일TV");

        Subscriber user1 = new User("민지");
        Subscriber user2 = new User("철수");

        channel.subscribe(user1);
        channel.subscribe(user2);

        channel.uploadNewVideo("디자인 패턴 쉽게 배우기");
    }
}
