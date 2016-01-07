CREATE USER 'velocity'@'localhost' IDENTIFIED BY 'velocity';
GRANT USAGE ON * . * TO 'velocity'@'localhost' IDENTIFIED BY 'velocity';
CREATE DATABASE IF NOT EXISTS `velocity`;
GRANT ALL PRIVILEGES ON `velocity` . * TO 'velocity'@'localhost';