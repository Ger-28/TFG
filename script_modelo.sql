-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`entidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`entidad` (
  `id_entidad` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `pais` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_entidad`),
  UNIQUE INDEX `identidad_UNIQUE` (`id_entidad` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`participante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`participante` (
  `id_participante` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `id_entidad` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_participante`),
  INDEX `fk_participante_entidad1_idx` (`id_entidad` ASC) VISIBLE,
  CONSTRAINT `fk_participante_entidad1`
    FOREIGN KEY (`id_entidad`)
    REFERENCES `mydb`.`entidad` (`id_entidad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`servicio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`servicio` (
  `id_servicio` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NULL,
  PRIMARY KEY (`id_servicio`),
  UNIQUE INDEX `idservicio_UNIQUE` (`id_servicio` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuario` (
  `id_usuario` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `pass_hash` VARCHAR(255) NOT NULL,
  `fecha_creacion` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `rol` ENUM('TECNICO', 'ADMINISTRADOR') NOT NULL,
  `id_servicio` INT UNSIGNED NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE INDEX `id_usuario_UNIQUE` (`id_usuario` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_usuario_servicio1_idx` (`id_servicio` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_servicio1`
    FOREIGN KEY (`id_servicio`)
    REFERENCES `mydb`.`servicio` (`id_servicio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`proyecto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`proyecto` (
  `id_proyecto` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(100) NOT NULL,
  `tipo` ENUM('CBHE', 'JM', 'PC', 'EMJM', 'EMDM', 'PI') NOT NULL,
  `acronimo` VARCHAR(20) NOT NULL,
  `rol_profesor` ENUM('Coordinador', 'Socio') NULL DEFAULT NULL,
  `estado` ENUM('Preparación', 'Presentado', 'No presentado', 'Concedido', 'No concedido') NULL DEFAULT 'No presentado',
  `evaluacion` VARCHAR(45) NULL DEFAULT 'Aún no consta.',
  `id_coordinador` INT UNSIGNED NULL,
  `id_tecnico` INT UNSIGNED NULL,
  PRIMARY KEY (`id_proyecto`),
  UNIQUE INDEX `idproyecto_UNIQUE` (`id_proyecto` ASC) VISIBLE,
  INDEX `fk_table1_participante1_idx` (`id_coordinador` ASC) VISIBLE,
  INDEX `fk_proyecto_usuario1_idx` (`id_tecnico` ASC) VISIBLE,
  CONSTRAINT `fk_table1_participante1`
    FOREIGN KEY (`id_coordinador`)
    REFERENCES `mydb`.`participante` (`id_participante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_proyecto_usuario1`
    FOREIGN KEY (`id_tecnico`)
    REFERENCES `mydb`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`proyecto_participante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`proyecto_participante` (
  `id_participante` INT UNSIGNED NOT NULL,
  `id_proyecto` INT UNSIGNED NOT NULL,
  INDEX `fk_proyecto_participante_participante1_idx` (`id_participante` ASC) VISIBLE,
  INDEX `fk_proyecto_participante_proyecto1_idx` (`id_proyecto` ASC) VISIBLE,
  CONSTRAINT `fk_proyecto_participante_participante1`
    FOREIGN KEY (`id_participante`)
    REFERENCES `mydb`.`participante` (`id_participante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_proyecto_participante_proyecto1`
    FOREIGN KEY (`id_proyecto`)
    REFERENCES `mydb`.`proyecto` (`id_proyecto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`paquete_trabajo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`paquete_trabajo` (
  `id_paquete` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(45) NULL,
  `fecha_inicio` TIMESTAMP NULL,
  `fecha_fin` TIMESTAMP NULL,
  `milestones` VARCHAR(200) NULL,
  `id_entidad_lider` INT UNSIGNED NULL,
  `id_entidad_colider` INT UNSIGNED NULL,
  `id_participante_responsable` INT UNSIGNED NULL,
  `id_proyecto` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_paquete`),
  UNIQUE INDEX `id_paquete_UNIQUE` (`id_paquete` ASC) VISIBLE,
  INDEX `fk_paquete_trabajo_entidad1_idx` (`id_entidad_lider` ASC) VISIBLE,
  INDEX `fk_paquete_trabajo_entidad2_idx` (`id_entidad_colider` ASC) VISIBLE,
  INDEX `fk_paquete_trabajo_participante1_idx` (`id_participante_responsable` ASC) VISIBLE,
  INDEX `fk_paquete_trabajo_proyecto1_idx` (`id_proyecto` ASC) VISIBLE,
  CONSTRAINT `fk_paquete_trabajo_entidad1`
    FOREIGN KEY (`id_entidad_lider`)
    REFERENCES `mydb`.`entidad` (`id_entidad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_paquete_trabajo_entidad2`
    FOREIGN KEY (`id_entidad_colider`)
    REFERENCES `mydb`.`entidad` (`id_entidad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_paquete_trabajo_participante1`
    FOREIGN KEY (`id_participante_responsable`)
    REFERENCES `mydb`.`participante` (`id_participante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_paquete_trabajo_proyecto1`
    FOREIGN KEY (`id_proyecto`)
    REFERENCES `mydb`.`proyecto` (`id_proyecto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`paquete_participante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`paquete_participante` (
  `id_paquete` INT UNSIGNED NOT NULL,
  `id_participante` INT UNSIGNED NOT NULL,
  INDEX `fk_paquete_participante_paquete_trabajo1_idx` (`id_paquete` ASC) VISIBLE,
  INDEX `fk_paquete_participante_participante1_idx` (`id_participante` ASC) VISIBLE,
  CONSTRAINT `fk_paquete_participante_paquete_trabajo1`
    FOREIGN KEY (`id_paquete`)
    REFERENCES `mydb`.`paquete_trabajo` (`id_paquete`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_paquete_participante_participante1`
    FOREIGN KEY (`id_participante`)
    REFERENCES `mydb`.`participante` (`id_participante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`actividad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`actividad` (
  `id_actividad` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(45) NULL,
  `descripción` VARCHAR(200) NULL,
  `actividad_padre` INT UNSIGNED NULL,
  `id_paquete` INT UNSIGNED NOT NULL,
  `id_responsable` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_actividad`),
  UNIQUE INDEX `id_actividad_UNIQUE` (`id_actividad` ASC) VISIBLE,
  INDEX `fk_actividad_actividad1_idx` (`actividad_padre` ASC) VISIBLE,
  INDEX `fk_actividad_paquete_trabajo1_idx` (`id_paquete` ASC) VISIBLE,
  INDEX `fk_actividad_participante1_idx` (`id_responsable` ASC) VISIBLE,
  CONSTRAINT `fk_actividad_actividad1`
    FOREIGN KEY (`actividad_padre`)
    REFERENCES `mydb`.`actividad` (`id_actividad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_actividad_paquete_trabajo1`
    FOREIGN KEY (`id_paquete`)
    REFERENCES `mydb`.`paquete_trabajo` (`id_paquete`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_actividad_participante1`
    FOREIGN KEY (`id_responsable`)
    REFERENCES `mydb`.`participante` (`id_participante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`actividad_participante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`actividad_participante` (
  `id_actividad` INT UNSIGNED NOT NULL,
  `id_participante` INT UNSIGNED NOT NULL,
  INDEX `fk_actividad_participante_actividad1_idx` (`id_actividad` ASC) VISIBLE,
  INDEX `fk_actividad_participante_participante1_idx` (`id_participante` ASC) VISIBLE,
  CONSTRAINT `fk_actividad_participante_actividad1`
    FOREIGN KEY (`id_actividad`)
    REFERENCES `mydb`.`actividad` (`id_actividad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_actividad_participante_participante1`
    FOREIGN KEY (`id_participante`)
    REFERENCES `mydb`.`participante` (`id_participante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`entregable`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`entregable` (
  `id_entregable` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `descripcion` VARCHAR(200) NULL,
  `fecha_entrega` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_participante` INT UNSIGNED NOT NULL,
  `id_actividad` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_entregable`),
  UNIQUE INDEX `id_entregable_UNIQUE` (`id_entregable` ASC) VISIBLE,
  INDEX `fk_entregable_participante1_idx` (`id_participante` ASC) VISIBLE,
  INDEX `fk_entregable_actividad1_idx` (`id_actividad` ASC) VISIBLE,
  CONSTRAINT `fk_entregable_participante1`
    FOREIGN KEY (`id_participante`)
    REFERENCES `mydb`.`participante` (`id_participante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_entregable_actividad1`
    FOREIGN KEY (`id_actividad`)
    REFERENCES `mydb`.`actividad` (`id_actividad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Departamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Departamento` (
  `id_departamento` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(75) NULL,
  `tipo_entidad` ENUM('ESCUELA', 'FACULTAD', 'CENTRO') NULL,
  `id_entidad` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_departamento`),
  UNIQUE INDEX `id_departamento_UNIQUE` (`id_departamento` ASC) VISIBLE,
  INDEX `fk_Departamento_entidad1_idx` (`id_entidad` ASC) VISIBLE,
  CONSTRAINT `fk_Departamento_entidad1`
    FOREIGN KEY (`id_entidad`)
    REFERENCES `mydb`.`entidad` (`id_entidad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`participante_departamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`participante_departamento` (
  `id_departamento` INT UNSIGNED NOT NULL,
  `id_participante` INT UNSIGNED NOT NULL,
  INDEX `fk_participante_departamento_Departamento1_idx` (`id_departamento` ASC) VISIBLE,
  INDEX `fk_participante_departamento_participante1_idx` (`id_participante` ASC) VISIBLE,
  CONSTRAINT `fk_participante_departamento_Departamento1`
    FOREIGN KEY (`id_departamento`)
    REFERENCES `mydb`.`Departamento` (`id_departamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_participante_departamento_participante1`
    FOREIGN KEY (`id_participante`)
    REFERENCES `mydb`.`participante` (`id_participante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`archivo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`archivo` (
  `id_archivo` INT NOT NULL,
  `archivo_adjunto` LONGBLOB NULL,
  `id_entregable` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_archivo`),
  INDEX `fk_archivo_entregable1_idx` (`id_entregable` ASC) VISIBLE,
  CONSTRAINT `fk_archivo_entregable1`
    FOREIGN KEY (`id_entregable`)
    REFERENCES `mydb`.`entregable` (`id_entregable`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
