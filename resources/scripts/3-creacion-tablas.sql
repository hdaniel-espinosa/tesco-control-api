-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema controlpuertas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema controlpuertas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `controlpuertas` DEFAULT CHARACTER SET latin1 ;
USE `controlpuertas` ;

-- -----------------------------------------------------
-- Table `controlpuertas`.`laboratorio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlpuertas`.`laboratorio` (
  `id_laboratorio` INT(11) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `edificio` VARCHAR(10) NOT NULL,
  `n_lugares` INT(11) NOT NULL,
  PRIMARY KEY (`id_laboratorio`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `controlpuertas`.`estado_laboratorio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlpuertas`.`estado_laboratorio` (
  `id_estado` INT(11) NOT NULL,
  `id_laboratorio` INT(11) NOT NULL,
  `fecha_hora` DATETIME NULL DEFAULT NULL,
  `temperatura` INT(11) NULL DEFAULT NULL,
  `humedad` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_estado`),
  INDEX `fk_estado_laboratorio_laboratorio1_idx` (`id_laboratorio` ASC),
  CONSTRAINT `fk_estado_laboratorio_laboratorio1`
    FOREIGN KEY (`id_laboratorio`)
    REFERENCES `controlpuertas`.`laboratorio` (`id_laboratorio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `controlpuertas`.`materia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlpuertas`.`materia` (
  `id_materia` INT(11) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `grupo` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id_materia`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `controlpuertas`.`horario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlpuertas`.`horario` (
  `id_horario` INT(11) NOT NULL,
  `dia` VARCHAR(20) NOT NULL,
  `hora_inicio` TIME NOT NULL,
  `hora_termino` TIME NOT NULL,
  `id_laboratorio` INT(11) NOT NULL,
  `id_materia` INT(11) NOT NULL,
  PRIMARY KEY (`id_horario`),
  INDEX `id_laboratorio` (`id_laboratorio` ASC),
  INDEX `id_materia` (`id_materia` ASC),
  CONSTRAINT `horario_ibfk_1`
    FOREIGN KEY (`id_laboratorio`)
    REFERENCES `controlpuertas`.`laboratorio` (`id_laboratorio`),
  CONSTRAINT `horario_ibfk_2`
    FOREIGN KEY (`id_materia`)
    REFERENCES `controlpuertas`.`materia` (`id_materia`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `controlpuertas`.`tarjeta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlpuertas`.`tarjeta` (
  `id_tarjeta` VARCHAR(15) NOT NULL,
  `tipo` VARCHAR(50) NOT NULL,
  `activa` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id_tarjeta`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `controlpuertas`.`registro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlpuertas`.`registro` (
  `id_registro` INT(11) NOT NULL,
  `id_tarjeta` VARCHAR(15) NOT NULL,
  `id_laboratorio` INT(11) NOT NULL,
  `fecha_hora` DATETIME NOT NULL,
  `abrio` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id_registro`),
  INDEX `id_tarjeta` (`id_tarjeta` ASC),
  INDEX `id_laboratorio` (`id_laboratorio` ASC),
  CONSTRAINT `registro_ibfk_1`
    FOREIGN KEY (`id_tarjeta`)
    REFERENCES `controlpuertas`.`tarjeta` (`id_tarjeta`),
  CONSTRAINT `registro_ibfk_2`
    FOREIGN KEY (`id_laboratorio`)
    REFERENCES `controlpuertas`.`laboratorio` (`id_laboratorio`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `controlpuertas`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlpuertas`.`usuario` (
  `id_usuario` INT(11) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `ap_paterno` VARCHAR(50) NOT NULL,
  `ap_materno` VARCHAR(50) NOT NULL,
  `correo` VARCHAR(50) NOT NULL,
  `telefono` VARCHAR(15) NOT NULL,
  `activo` TINYINT(1) NOT NULL,
  `tipo_usuario` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `controlpuertas`.`usuario_materia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlpuertas`.`usuario_materia` (
  `id_usuario` INT(11) NOT NULL,
  `id_materia` INT(11) NOT NULL,
  PRIMARY KEY (`id_usuario`, `id_materia`),
  INDEX `fk_usuario_has_materia_materia1_idx` (`id_materia` ASC),
  INDEX `fk_usuario_has_materia_usuario1_idx` (`id_usuario` ASC),
  CONSTRAINT `fk_usuario_has_materia_materia1`
    FOREIGN KEY (`id_materia`)
    REFERENCES `controlpuertas`.`materia` (`id_materia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_materia_usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `controlpuertas`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `controlpuertas`.`usuario_sistema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlpuertas`.`usuario_sistema` (
  `id_usuario_sistema` INT(11) NOT NULL,
  `nombre` VARCHAR(50) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `ap_paterno` VARCHAR(45) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `ap_materno` VARCHAR(45) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `contraseña` VARCHAR(45) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `activo` TINYINT(1) NULL DEFAULT NULL,
  `tipo` VARCHAR(45) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  PRIMARY KEY (`id_usuario_sistema`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `controlpuertas`.`usuario_tarjeta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlpuertas`.`usuario_tarjeta` (
  `id_usuario` INT(11) NOT NULL,
  `id_tarjeta` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id_usuario`, `id_tarjeta`),
  INDEX `fk_usuario_has_tarjeta_tarjeta1_idx` (`id_tarjeta` ASC),
  INDEX `fk_usuario_has_tarjeta_usuario1_idx` (`id_usuario` ASC),
  CONSTRAINT `fk_usuario_has_tarjeta_tarjeta1`
    FOREIGN KEY (`id_tarjeta`)
    REFERENCES `controlpuertas`.`tarjeta` (`id_tarjeta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_tarjeta_usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `controlpuertas`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
