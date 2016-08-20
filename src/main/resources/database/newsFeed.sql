-- MySQL Script generated by MySQL Workbench
-- Sat Aug 20 12:36:37 2016
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema newsfeed
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `newsfeed` ;

-- -----------------------------------------------------
-- Schema newsfeed
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `newsfeed` DEFAULT CHARACTER SET utf8 ;
USE `newsfeed` ;

-- -----------------------------------------------------
-- Table `newsfeed`.`NewsItem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `newsfeed`.`NewsItem` ;

CREATE TABLE IF NOT EXISTS `newsfeed`.`NewsItem` (
  `idNewsItem` VARCHAR(50) NOT NULL,
  `newsText` VARCHAR(20000) NULL,
  `datePublished` MEDIUMTEXT NULL,
  PRIMARY KEY (`idNewsItem`),
  UNIQUE INDEX `idNewsItem_UNIQUE` (`idNewsItem` ASC))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
