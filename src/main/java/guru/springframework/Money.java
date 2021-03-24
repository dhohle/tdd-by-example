package guru.springframework;

public class Money implements Expression {

    protected final String currency;
    protected final int amount;

    public Money(int amount, String currency) {
        this.currency = currency;
        this.amount = amount;
    }

    protected String currency() {
        return currency;
    }

    @Override
    public Expression times(int multiplier) {
        return new Money(this.amount * multiplier, this.currency);
    }

    @Override
    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }


    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount &&
                this.currency.equals(money.currency);
    }

    @Override
    public Money reduce(Bank bank, String to) {
        int rate = bank.rate(this.currency, to);
        return new Money(amount/rate, to);
    }

    @Override
    public String toString() {
        return "Money{" +
                "currency='" + currency + '\'' +
                ", amount=" + amount +
                '}';
    }


    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

}
