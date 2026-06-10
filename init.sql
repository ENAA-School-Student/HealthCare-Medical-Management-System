CREATE USER 'healthcare_user'@'%' IDENTIFIED BY '1234567890';
GRANT ALL PRIVILEGES ON healthcare_db.* TO 'healthcare_user'@'%';
FLUSH PRIVILEGES;
