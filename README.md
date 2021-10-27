# RTPlugin

コマンドを定期実行するプラグイン

## Requirement

* spigot

# Author

* kigawa
    * kigawa.8390@gmail.com

## About

* commandsフォルダーに入ったtxt形式のファイルに書かれたコマンドを実行する
* 一秒の誤差が発生する可能性がある
* コマンドの書き方
  * 一行につき一つずつコマンドを書いていく
  * 書き方の例
```
give @a bread
kill @a
```
* ファイルの命名の仕方
  * ```時間;分.txt```と書く
  * ワイルドカードとして```_```が使える
  * ```tick.txt```は毎ティック実行
  * ```sec.txt```は毎秒実行
  * 命名の例
```
0;0.txt//0時0分
7;30.txt//7時30分
_;0.txt//毎時0分
8;_.txt//8時の間毎分
tick.txt//毎ティック
sec.txt//毎秒
```

## Command



## Change



# making

## Version

### 例: 9.1a
* **9.1a**
  * プラグインのバージョン
  * **9**: メジャー
  * **1**: マイナー
  * **a**: プラグインのバージョン アルファ(a)/ベータ(b)/推奨(R)



## ToDo



## sample

