DROP USER IF EXISTS 'dataroast'@'%';
CREATE USER 'dataroast'@'%' IDENTIFIED BY 'dataroastpassword';
GRANT ALL PRIVILEGES ON senderosymontanas.* TO 'dataroast'@'%';
FLUSH PRIVILEGES;