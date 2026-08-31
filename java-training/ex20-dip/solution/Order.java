import java.time.LocalDateTime;

/** 注文。 */
public class Order {

    private final String customerEmail;
    private final String item;
    private final int amount;
    private final LocalDateTime orderedAt;

    public Order(String customerEmail, String item, int amount, LocalDateTime orderedAt) {
        this.customerEmail = customerEmail;
        this.item = item;
        this.amount = amount;
        this.orderedAt = orderedAt;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }
}
