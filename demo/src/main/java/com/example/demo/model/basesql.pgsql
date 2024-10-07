-- PostgreSQLのSQL文
-- バージョン
select * From version();
-- ユーザ名：
select current_user;
-- 接続スキーマ：public：
select current_schema();
-- 接続文字列：jdbc:postgresql://localhost:5432/postgres
-- DB名：postgres：
SELECT current_database() AS database_name