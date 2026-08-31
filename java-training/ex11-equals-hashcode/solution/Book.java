import java.util.Objects;

/**
 * 課題11 解答例。
 *
 * ポイント:
 *  1. equals を実装しないと Object の equals（== と同じ、参照の比較）が使われる。
 *     内容が同じでも別インスタンスなら「別物」と判定される。
 *  2. HashSet / HashMap は「まず hashCode でバケットを決め、その中で equals で比較する」。
 *     そのため equals だけ実装して hashCode を忘れると、
 *     equals では等しいのに Set に重複が入る、Map から取り出せない、という不可解な挙動になる。
 *     -> equals と hashCode は必ずセットで実装する（equals/hashCode 契約）。
 *  3. 契約:
 *     - a.equals(a) は true（反射性）
 *     - a.equals(b) と b.equals(a) は一致（対称性）
 *     - a.equals(b) が true なら a.hashCode() == b.hashCode()
 *     - null と比較したら false、型が違えば false
 *  4. 引数は Object 型にすること。Book 型にすると「オーバーライド」ではなく
 *     「オーバーロード」になり、コレクションからは呼ばれない（@Override を付ければコンパイラが気づく）。
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        Book other = (Book) o;
        return isbn.equals(other.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return title + "(" + isbn + ")";
    }
}
