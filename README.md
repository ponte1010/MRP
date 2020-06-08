# To Do
- [x] クラス図をアップロード
- [ ] クラス図を修正
- [ ] 計画情報ファイルの作成
- [ ] 計画情報ファイルの入出力プログラム作成
- [ ] MRPシステムの実装(計算部分以外)
- [ ] GUIの作成
# constructBOM.java
## (1)コマンドプロンプトにおいてconstructBOM.javaをコンパイル＆実行

```Java:constructBOM2.bat
javac -encoding utf-8 *.java
java constructBOM
```

## (2)「Print -> 1: Parts in BOM, 2: Work Data, 3:Order, 4:Exit」と表示
BOMの部品構造を中間製品から表示するならば → 1  
作業の順序で作業名と部品名, 作業時間を表示 → 2  
受注による部品数を表示する場合 → 3  
終了するならば → 4  
<1を選択>  
・コマンドプロンプトにおいて　 "Part Name?" と出たら製品(部品)名を入力し，enterキーを入力する  
→ 部品構造が表示される  
・再度，入力を促してくる  
・終了の際は， cntrl+C キーで終了  
<2を選択>  
・作業順序に従って，作業名，部品名，作業時間が表示される  
<3を選択>  
・受注による必要な部品と部品点数が表示される  
<4を選択>  
・終了
## 注意事項：  
DBとなる各種テーブルはテキスト形式として, <data_file>のフォルダに入れる
