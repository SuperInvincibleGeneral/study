/**
 * 課題20 解答例: 「メールを送る」という抽象。
 *
 * 依存性逆転の原則（DIP）:
 *   上位（業務ロジック）は下位（具体的な送信手段）に依存してはならない。
 *   どちらも「抽象」に依存すべきである。
 *
 * ポイントは、このインターフェースを「誰のために」定義するか。
 * SMTP の都合ではなく、OrderService が必要とする形（宛先・件名・本文）で定義する。
 * こうすると SMTP でも、社内API でも、テスト用の記録役でも差し替えられる。
 */
public interface MailGateway {
    void send(String to, String subject, String body);
}
