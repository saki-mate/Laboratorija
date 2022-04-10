-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema lab1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lab1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lab1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `lab1` ;

-- -----------------------------------------------------
-- Table `lab1`.`Kategorija`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab1`.`Kategorija` (
  `idKategorija` INT NOT NULL,
  `naziv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idKategorija`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lab1`.`Uloga`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab1`.`Uloga` (
  `idUloga` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUloga`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lab1`.`Laboratorija_korisnik`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab1`.`Laboratorija_korisnik` (
  `idKorisnik` INT NOT NULL AUTO_INCREMENT,
  `brLicneKarte` VARCHAR(45) NOT NULL,
  `sifra` VARCHAR(255) NOT NULL,
  `ime` VARCHAR(45) NOT NULL,
  `prezime` VARCHAR(45) NOT NULL,
  `datum_rodjenja` VARCHAR(45) NULL DEFAULT NULL,
  `kontakt` VARCHAR(45) NULL DEFAULT NULL,
  `idUloga` INT NOT NULL,
  PRIMARY KEY (`idKorisnik`),
  INDEX `fk_Laboratorija_korisnik_Uloga_idx` (`idUloga` ASC) VISIBLE,
  CONSTRAINT `fk_Laboratorija_korisnik_Uloga`
    FOREIGN KEY (`idUloga`)
    REFERENCES `lab1`.`Uloga` (`idUloga`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lab1`.`Rezervacija`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab1`.`Rezervacija` (
  `idRezervacija` INT NOT NULL AUTO_INCREMENT,
  `idLaborant` INT NOT NULL,
  `idAnaliza` INT NOT NULL,
  `datum` DATE NOT NULL,
  `idKorisnik` INT NOT NULL,
  PRIMARY KEY (`idRezervacija`),
  INDEX `fk_Rezervacija_Analiza_idx` (`idAnaliza` ASC) VISIBLE,
  INDEX `fk_Rezervacija_Laboratorija_korisnik_idx` (`idKorisnik` ASC) VISIBLE,
  CONSTRAINT `fk_Rezervacija_Analiza`
    FOREIGN KEY (`idAnaliza`)
    REFERENCES `lab1`.`Analiza` (`idAnaliza`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_Rezervacija_Laboratorija_korisnik`
    FOREIGN KEY (`idKorisnik`)
    REFERENCES `lab1`.`Laboratorija_korisnik` (`idKorisnik`))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lab1`.`Analiza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab1`.`Analiza` (
  `idAnaliza` INT NOT NULL AUTO_INCREMENT,
  `cena` INT NOT NULL,
  `naziv` VARCHAR(45) NULL DEFAULT NULL,
  `idKategorija` INT NULL DEFAULT NULL,
  `idRezervacija` INT NULL DEFAULT NULL,
  PRIMARY KEY (`idAnaliza`),
  INDEX `fk_Analiza_Kategorija_idx` (`idKategorija` ASC) VISIBLE,
  INDEX `fk_Analiza_Rezervacija_idx` (`idRezervacija` ASC) VISIBLE,
  CONSTRAINT `fk_Analiza_Kategorija`
    FOREIGN KEY (`idKategorija`)
    REFERENCES `lab1`.`Kategorija` (`idKategorija`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_Analiza_Rezervacija`
    FOREIGN KEY (`idRezervacija`)
    REFERENCES `lab1`.`Rezervacija` (`idRezervacija`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lab1`.`Komentar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab1`.`Komentar` (
  `idKomentar` INT NOT NULL AUTO_INCREMENT,
  `vreme` DATE NULL DEFAULT NULL,
  `opis` VARCHAR(45) NULL DEFAULT NULL,
  `idRezervacija` INT NOT NULL,
  PRIMARY KEY (`idKomentar`),
  INDEX `fk_Komentar_Rezervacija` (`idRezervacija` ASC) VISIBLE,
  CONSTRAINT `fk_Komentar_Rezervacija`
    FOREIGN KEY (`idRezervacija`)
    REFERENCES `lab1`.`Rezervacija` (`idRezervacija`))
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lab1`.`Termin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab1`.`Termin` (
  `idTermin` INT NOT NULL AUTO_INCREMENT,
  `vreme` VARCHAR(45) NOT NULL,
  `idLaborant` INT NOT NULL,
  `zauzetost` INT NOT NULL,
  PRIMARY KEY (`idTermin`),
  INDEX `fk_Termin_Laborant_korisnik_idx` (`idLaborant` ASC) VISIBLE,
  CONSTRAINT `fk_Termin_Laboratorija_korisnik`
    FOREIGN KEY (`idLaborant`)
    REFERENCES `lab1`.`Laboratorija_korisnik` (`idKorisnik`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

