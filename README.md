# constructBOM.java(20180628) 
## (1)コマンドプロンプトにおいてconstructBOM.javaをコンパイル＆実行(以下の命令を入力) 
------------------  
javac -encoding utf-8 *.java<Enter>  
java constructBOM<Enter>  
------------------
## (2)「Print -> 1: Parts in BOM, 2: Work Data, 3:Order, 4:Exit」と表示されるので，
　BOMの部品構造を中間製品から表示するならば　1
　作業の順序で作業名と部品名, 作業時間を表示　2
　終了するならば　3(その他)
を入力して＜Enter＞
------------------------------
### <1を選択>  
### (3)コマンドプロンプトにおいて　 "Part Name?" と出たら製品(部品)名を入力し，enterキーを入力する。  
### →　部品　以下の部品構造が表示される  
### (3)再度，入力を促してくる。  
### (4)終了の際は， cntrl+C キーで終了  
------------------------------
### <2を選択>  
### (3)作業順序に従って，作業名，部品名，作業時間が表示される。
------------------------------
### <3を選択>  
### (3)受注による必要な部品と部品点数が表示される。
------------------------------
### <4を選択>  
### (3)終了

## 注意事項：DBとなる各種テーブルはテキスト形式として, <data_file>のフォルダに入れる．
