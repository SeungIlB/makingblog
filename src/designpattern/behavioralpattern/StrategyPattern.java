package designpattern.behavioralpattern;

// 전략 인터페이스
interface PaymentStrategy {
    void pay(int amount);
}

// 구체적인 전략들
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void pay(int amount) {
        System.out.println("신용카드(" + cardNumber + ")로 " + amount + "원 결제했습니다.");
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    public void pay(int amount) {
        System.out.println("PayPal 계정(" + email + ")으로 " + amount + "원 결제했습니다.");
    }
}

// Context
class PaymentContext {
    private PaymentStrategy strategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void pay(int amount) {
        if (strategy == null) {
            throw new IllegalStateException("결제 수단이 설정되지 않았습니다.");
        }
        strategy.pay(amount);
    }
}

// 실행
public class StrategyPattern {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456"));
        context.pay(5000);

        context.setPaymentStrategy(new PayPalPayment("user@example.com"));
        context.pay(7000);
    }
}