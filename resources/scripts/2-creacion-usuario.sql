CREATE USER 'controlpuertasusr'@'%' identified by 'controltesco123';
GRANT ALL ON controlpuertas.* TO 'controlpuertasusr'@'%';

CREATE USER 'controlpuertasusr'@'localhost' identified by 'controltesco123';
FLUSH PRIVILEGES;

