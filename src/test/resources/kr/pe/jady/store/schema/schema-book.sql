DROP SCHEMA IF EXISTS log;
-- DROP TABLE IF EXISTS log.user_transaction_log;
CREATE SCHEMA log;
CREATE TABLE log.user_transaction_log (
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  user_id VARCHAR2(50) NOT NULL,
  stock_id BIGINT NOT NULL,
  type CHAR(1) NOT NULL,
  amount BIGINT NOT NULL,
  created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP()
);
INSERT INTO log.user_transaction_log (user_id, stock_id, type, amount, created) VALUES ('jhlee@abc.com', 5, 'P', 2000, DATEADD('DD', -2, CURRENT_TIMESTAMP()));
INSERT INTO log.user_transaction_log (user_id, stock_id, type, amount, created) VALUES ('brkang@abc.com', 6, 'P', 2000, DATEADD('DD', -2, CURRENT_TIMESTAMP()));
INSERT INTO log.user_transaction_log (user_id, stock_id, type, amount, created) VALUES ('jhlee@abc.com', 1, 'P', 1000, DATEADD('DD', -1, CURRENT_TIMESTAMP()));
INSERT INTO log.user_transaction_log (user_id, stock_id, type, amount, created) VALUES ('brkang@abc.com', 2, 'P', 1000, DATEADD('DD', -1, CURRENT_TIMESTAMP()));
INSERT INTO log.user_transaction_log (user_id, stock_id, type, amount, created) VALUES ('jhlee@abc.com', 1, 'R', 1000, DATEADD('DD', -1, CURRENT_TIMESTAMP()));

DROP SCHEMA IF EXISTS summary;
CREATE SCHEMA summary;
CREATE TABLE summary.daily_transaction_summary (
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  date DATE NOT NULL,
  type CHAR(1) NOT NULL,
  count BIGINT NOT NULL,
  amount BIGINT NOT NULL,
  created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP()
);