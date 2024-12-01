##spring bootでPostgreSQLを使う方法
####spring.application.name=demo プロパティ形式からyml形式に変更時のエラー

* 備忘録
* ショートカット：ctrl shift v　VSCodeでプレビュー表示
> 参考：https://qiita.com/
>
* アプリケーションで修正な箇所
* 利用者確認画面（userList）の表（テーブル）について、ヘッダ部とボディ部の列数が合っていない。
* キャプチャを貼り付けたいので、その方法を調べる。
* 簡単プランは確定するまで（日本時間で日付が変わるまで）



## ■ 文法チート
日付の取り扱いについて<br/>
~~~
// 生年月日のプルダウンリスト項目を作成する。
LocalDate startDay = LocalDate.of(2000, 1, 1);
LocalDate endDay = LocalDate.of(2010, 1, 11);
long diffDay = ChronoUnit.DAYS.between(startDay, endDay);
var dateOfBirthList = new LinkedList<LocalDate>();
for (int i = 0; i < Math.toIntExact(diffDay); i++) {
    dateOfBirthList.add(startDay.plusDays(i));
}

// LocalDateの月末日を取得
LocalDate targetDate = LocalDate.of(2020, 2, 1);
LocalDate result = targetDate.with(TemporalAdjusters.lastDayOfMonth());
System.out.println("LocalDate.with(TemporalAdjusters.lastDayOfMonth()) = " + result);
~~~

Date型とTimestamp型の違いについて<br>
https://qiita.com/mumian1014/items/921ef11c7e5a937980fd
~~~
// Timestampの実装一例は下記↓に示す。
// 協定世界時のUTC 1970年1月1日深夜零時との差をミリ秒で取得
// ミリ秒を引数としてTimestampオブジェクトを作成
Timestamp timestamp = new Timestamp(System.currentTimeMillis());
SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
String formattedDate = sdf.format(timestamp);
~~~

