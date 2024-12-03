
## ■ 出会った課題たち
#### アプリケーションで修正な箇所
* 利用者確認画面（userList）の表（テーブル）について、ヘッダ部とボディ部の列数が合っていない。
* マークダウン記法を用いたキャプチャを貼り付け方法
* 簡単プランは確定するまで（日本時間で日付が変わるまで）
* spring.application.name=demo プロパティ形式からyml形式に変更時のエラー
* 題名と内容が一致していない箇所がある（.md）
* あいまい検索で「java」と検索でspring、Thymeleaf も関連で検索結果に出力する方法(逆も)

## ■ javaの日付APIの実装例
* LocalDateの場合
~~~
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

* Timestampの場合
~~~
// 協定世界時のUTC 1970年1月1日深夜零時との差をミリ秒で取得
// ミリ秒を引数としてTimestampオブジェクトを作成
Timestamp timestamp = new Timestamp(System.currentTimeMillis());
SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
String formattedDate = sdf.format(timestamp);
~~~
Date型とTimestamp型の違いについて<br>
参考：https://qiita.com/mumian1014/items/921ef11c7e5a937980fd


## ■ javascriptで生年月日のセレクトボックスを作成
* 11月31日、2月31日　などあり得ない日付が入るパターンを2示す

HTMLに埋め込み<br>
【HTML】
~~~
    <label for="dateOfBirth">生年月日：</label>
    <select id="year">
      <option value="0">----</option>
    </select>年
    <select id="month">
      <option value="0">--</option>
    </select>月
    <select id="day">
      <option value="0">--</option>
    </select>日​
~~~

【JavaScript】
~~~
  //現在の年数オブジェクトを4桁で生成
  var time = new Date();
  var year = time.getFullYear();
  //1900年まで表示
  for (var i = year; i >= 1900; i--) {
    createOptionElements(i, 'year');
  }
  //1～12の数字を生成
  for (var i = 1; i <= 12; i++) {
    createOptionElements(i, 'month');
  }
  //1～31の数字を生成
  for (var i = 1; i <= 31; i++) {
    createOptionElements(i, 'day');
  }
  function createOptionElements(num, parentId) {
    var doc = document.createElement('option');
    doc.value = doc.innerHTML = num;
    document.getElementById(parentId).appendChild(doc);
  }
~~~

外部ファイル<br>
【HTML】
~~~
<select id="year" name="year"></select>
<select id="month" name="month"></select>
<select id="date" name="date"></select>
~~~
【JavaScript】
~~~
(function() {
  var today = new Date();
  var year = today.getFullYear();
  var month = today.getMonth() + 1;
  var date = today.getDate();
  function createOption(id, startNum, endNum, current) {
    var selectDom = document.getElementById(id);
    var optionDom = '';
    for (var i = startNum; i <= endNum; i++) {
      var option = '';
      if (i === current) {
        option = '<option value="' + i + '">' + i + '</option>';
      } else {
        option = '<option value="' + i + '" selected>' + i + '</option>';
      }
      optionDom += option;
    }
    selectDom.insertAdjacentHTML('beforeend', optionDom);
  }
  createOption('year', 1900, year, year);
  createOption('month', 1, 12, month);
  createOption('date', 1, 31, date);
})()
~~~



## ■ 備忘録
* ショートカット：ctrl shift v　VSCodeでマークダウンのプレビュー表示
