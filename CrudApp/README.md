# README


## 概要

- CrudAppは、`(key, value)`のペアを作成・参照・更新・削除する操作（CRUD）を行うことができるSpring Bootアプリです。
- Spring Bootアプリの実装方法やDockerを用いたDB作成、O/Rマッパー（Spring Data JPA）を用いたCRUD操作の理解を深めるために作成した簡易的なアプリです。最低限のため、バリデーション機能等は実装していません。


### 環境情報

環境情報は次の通りです。
- OS：Windows 10
- 統合開発環境：Spring Tool Suite 4 (4.12.1)
- 開発言語：Java 11 (11.0.13.8, AdoptOpenJDK)
  - フレームワーク：Spring Boot 2.5.6
- ビルドツール：Maven 3.8.3
- その他（WSL上でDB作成のため使用）
  - Docker 20.10.14
  - Docker Compose v2.6.1


## DBについて

本アプリではPostgreSQLを使用しています。主な設定値は次の通りです。
- DB名：`key_value_db`
- DBテーブル名：`key_value_table`
- ユーザ名：`postgres`
- パスワード：`postgres`


### DBテーブル

今回使用するDBテーブルはID`id`、キー`key`、値`value`から成ります。

テーブル作成時には`src\main\resources\01_schema.sql`を使用します。
```sql
/* 01_schema.sql */
DROP TABLE IF EXISTS key_value_table;

CREATE TABLE key_value_table
(
   id SERIAL NOT NULL,
   key VARCHAR(100) NOT NULL,
   value VARCHAR(100) NOT NULL,
   PRIMARY KEY(id)
);
```


### 補足：DBの作成（Docker）

Dockerが使用できる環境の場合、下記手順でDBを作成できます。

DBを作成するために、下記コマンドを順に入力します。作成するDBの設定は`docker-compose.yml`に記載してあります。

```sh
cd CrudApp
docker network create my-network
docker-compose up -d
```

特にエラーが発生しなれば完了です。


#### Tips
- 作成したネットワーク`my-network`を確認するためには、次のコマンドを実行します。
  ```sh
  docker network inspect my-network
  ```
- 作成したコンテナの中にDBとテーブルが作成できているか確認するためには、次のコマンドを実行します。  
（`\dt`、`\d`はpsqlコンソールで使用します）
  ```sh
  docker exec -it mydb psql -U postgres key_value_db
  \dt
  \d key_value_table
  ```
- DBコンテナ作成の際、上記のコマンド`docker-compose`を実行することで、`docker-compose.yml`が読み込まれます。
- エントリポイント（一番最初に実行される場所、`/docker-entrypoint-initdb.d`）がinitdbコマンドを呼び出してデフォルトのpostgresユーザとデータベースを作成します。 
そして、そのディレクトリで見つかったファイル（拡張子`.sql`, `*.sql.gz`, `.sh`）は自動実行されます。  
  今回対象となるファイルは`src\main\resources\`以下の`01_schema.sql`, `02_data.sql`です。
  - 参考：[Docker Hub (PostgreSQL Quick Reference)](https://hub.docker.com/_/postgres)


## アプリの実行方法

Spring Tool Suite 4にて、次の通り実行します。
- インポート
  - タブ"File"→"Open Projects from File Systems"→"Import source:"の横の欄にアプリ`CrudApp`のパスを入力→"Finish"をクリックしアプリをSpring Tool Suite 4にインポート
- ビルド
  - 画面左側のPackage Explorerの`CrudApp`を右クリック→"Run As"→"Maven build..."をクリック
  - 表示された画面にて以下の通りに設定し、最後に"Run"をクリック
    - `Goals`: `package`
    - `Profiles`: `pom.xml`
- 実行
  - 画面左側のPackage Explorerの`CrudApp`を右クリック→"Run As"→"Spring Boot App"をクリックしてローカル実行
- アクセス
  - ブラウザを起動し、`http://localhost:8081/home`にアクセス（8080番ポートではない）

無事アクセスできると、Key-Value Listという文字と表が画面に表示されます。

各Key-Valueペアの横にボタン`Edit`, `Delete`が表示されますので、ボタンをクリックすることで編集・削除できます。  
新規追加する場合には`go to input form page`をクリックし、遷移した画面でKeyとValueを入力後、`Add`ボタンを押すことでリストに追加されます。

#### Tips

- アプリのポート番号やDB接続設定（PostgreSQLとの連携）等は`CrudApp/src/main/resources/application.properties`に記載してあります。
  - 特にDB周りについては、PostgreSQLでDB作成の際に指定した接続先、ユーザ名、パスワードと同じものを記載してください。