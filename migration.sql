USE adlister_db;

DROP TABLE IF EXISTS ads;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS ad_categories;
DROP TABLE IF EXISTS categories;

CREATE TABLE IF NOT EXISTS `users` (
                                       `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                       `username` VARCHAR(240) NOT NULL,
                                       `email` VARCHAR(240) NOT NULL,
                                       `password` VARCHAR(255) NOT NULL,
                                       `avatar_filepath` VARCHAR(240) NULL DEFAULT '/img/user-solid.svg',
                                       PRIMARY KEY (`id`),
                                       UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
                                       UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
    ENGINE = InnoDB;


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
                                     INDEX `id_idx` (`user_id` ASC) VISIBLE,
                                     CONSTRAINT `id`
                                         FOREIGN KEY (`user_id`)
                                             REFERENCES `users` (`id`)
                                             ON DELETE NO ACTION
                                             ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `categories` (
                                            `cat_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                            `name` VARCHAR(100) NOT NULL,
                                            PRIMARY KEY (`cat_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ad_categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ad_categories` (
                                               `ad_id` INT UNSIGNED NOT NULL,
                                               `cat_id` INT UNSIGNED NOT NULL,
                                               INDEX `id_idx` (`cat_id` ASC) VISIBLE,
                                               FOREIGN KEY (`ad_id`)
                                                   REFERENCES `ads` (`id`)
                                                   ON DELETE NO ACTION
                                                   ON UPDATE NO ACTION,
                                               FOREIGN KEY (`cat_id`)
                                                   REFERENCES `categories` (`cat_id`)
                                                   ON DELETE NO ACTION
                                                   ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `images` (
                                        `id_image` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                        `filepath` VARCHAR(255) NULL,
                                        PRIMARY KEY (`id_image`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ads_images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ads_images` (
                                            `id_images` INT UNSIGNED NULL,
                                            `ad_id` INT UNSIGNED NULL,
                                            INDEX `ad_id_idx` (`ad_id` ASC) VISIBLE,
                                            INDEX `idImages_idx` (`id_images` ASC) VISIBLE,
                                            CONSTRAINT `ad_id`
                                                FOREIGN KEY (`ad_id`)
                                                    REFERENCES `ads` (`id`)
                                                    ON DELETE NO ACTION
                                                    ON UPDATE NO ACTION,
                                            CONSTRAINT `idImages`
                                                FOREIGN KEY (`id_images`)
                                                    REFERENCES `images` (`id_image`)
                                                    ON DELETE NO ACTION
                                                    ON UPDATE NO ACTION)
    ENGINE = InnoDB;
