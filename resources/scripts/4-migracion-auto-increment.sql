-- Para bases de datos creadas con la versión anterior de 3-creacion-tablas.sql
-- (sin AUTO_INCREMENT), necesaria para que el backend pueda insertar
-- laboratorios, materias, usuarios, horarios y registros de acceso sin tener
-- que enviar el id manualmente.

USE `controlpuertas`;

ALTER TABLE `laboratorio` MODIFY `id_laboratorio` INT(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `estado_laboratorio` MODIFY `id_estado` INT(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `materia` MODIFY `id_materia` INT(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `horario` MODIFY `id_horario` INT(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `registro` MODIFY `id_registro` INT(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `usuario` MODIFY `id_usuario` INT(11) NOT NULL AUTO_INCREMENT;
