/** 売上1件。このクラスはそのまま使ってよい。 */
public class Sale {

    private final String category;
    private final String item;
    private final int amount;

    public Sale(String category, String item, int amount) {
        this.category = category;
        this.item = item;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public String getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }
}
