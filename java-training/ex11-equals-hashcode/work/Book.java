import java.util.Objects;

/**
 * 課題11: 書籍。
 * 「同じ本を2回登録すると重複する」「登録したはずの本が検索で見つからない」という不具合がある。
 * 同一性は ISBN だけで決まる（タイトルや価格が違っても ISBN が同じなら同じ本）。
 */
public class Book {

    private final String isbn;
    private final String title;
    private final int price;

    public Book(String isbn, String title, int price) {
        this.isbn = Objects.requireNonNull(isbn, "isbn");
        this.title = title;
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return title + "(" + isbn + ")";
    }
}
