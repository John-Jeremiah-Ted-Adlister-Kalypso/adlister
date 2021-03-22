USE adlister_db;

CREATE TABLE IF NOT EXISTS `users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(240) NOT NULL,
  `email` VARCHAR(240) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `avatar_filepath` VARCHAR(240) NULL DEFAULT '/img/user-solid.svg',
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `ads`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ads` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL,
  `title` VARCHAR(240) NOT NULL,
  `description` TEXT NOT NULL,
  `created_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),

  CONSTRAINT `id`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `categories` (
  `cat_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`cat_id`));


-- -----------------------------------------------------
-- Table `ad_categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ads_categories` (
  `ad_id` INT UNSIGNED NOT NULL,
  `cat_id` INT UNSIGNED NOT NULL,

    FOREIGN KEY (`ad_id`)
    REFERENCES `ads` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`cat_id`)
    REFERENCES `categories` (`cat_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `images` (
  `id_image` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `filepath` VARCHAR(255) NULL,
  PRIMARY KEY (`id_image`));


-- -----------------------------------------------------
-- Table `ads_images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ads_images` (
  `id_images` INT UNSIGNED NULL,
  `ad_id` INT UNSIGNED NULL,
  CONSTRAINT `ad_id`
    FOREIGN KEY (`ad_id`)
    REFERENCES `ads` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idImages`
    FOREIGN KEY (`id_images`)
    REFERENCES `images` (`id_image`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

SET GLOBAL time_zone = '+8:00';