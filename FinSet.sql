CREATE DATABASE finset;
USE finset;

CREATE TABLE `tbl_user` (
                            `uno` INT NOT NULL AUTO_INCREMENT primary key,
                            `email` VARCHAR(50) NOT NULL,
                            `password` VARCHAR(12) NOT NULL,
                            `user_name` VARCHAR(50) NOT NULL,
                            `user_position` VARCHAR(50) NULL,
                            `status` INT(1) NOT NULL DEFAULT '1' COMMENT '탈퇴 여부',
                            `createAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            `status_date` TIMESTAMP NULL
);
ALTER TABLE `tbl_user`
    ADD INDEX `idx_user_name` (`user_name`);
CREATE TABLE `tbl_stock` (
                             `sno` INT NOT NULL AUTO_INCREMENT primary key,
                             `stock_symbol` VARCHAR(10) NOT NULL COMMENT '직접입력',
                             `stock_name` VARCHAR(50) NOT NULL COMMENT '(주식현재가 시세) rprs_mrkt_kor_name',
                             `stock_price` VARCHAR(50) NOT NULL COMMENT '(주식현재가 시세) stck_prpr',
                             `price_change_rate` VARCHAR(50) NULL COMMENT '(주식현재가 시세) prdy_ctrt',
                             `stock_volume` VARCHAR(50) NULL COMMENT '(주식현재가 시세) acml_vol'

);
CREATE TABLE `tbl_type` (
                            `tno`	INT NOT NULL AUTO_INCREMENT primary key,
                            `t_name`	VARCHAR(10)	NOT NULL	COMMENT '예금/ 적금/  펀드/ 주식/ 외환'
);
CREATE TABLE `tbl_deposit` (
                               `dno` INT NOT NULL AUTO_INCREMENT primary key,
                               `deposit_category` VARCHAR(50) NOT NULL COMMENT '복리/단리',
                               `depoist_name` VARCHAR(50) NOT NULL COMMENT 'fin_prdt_nm',
                               `deposit_bank` VARCHAR(50) NOT NULL COMMENT 'kor_co_nm',
                               `deposit_max_rate` TEXT NULL COMMENT 'intr_rate2',
                               `deposit_normal_rate` TEXT NULL COMMENT 'intr_rate',
                               `deposit_join` VARCHAR(50) NULL COMMENT 'join_deny',
                               `deposit_stream` TEXT NULL COMMENT 'save_trm',
                               `deposit_amount` TEXT NULL COMMENT 'max_limit',
                               `deposit_way` VARCHAR(50) NULL COMMENT 'join_way',
                               `deposit_target` VARCHAR(50) NULL COMMENT 'join_member',
                               `deposit_link` TEXT NULL COMMENT '직접입력'

);

CREATE TABLE `tbl_fund` (
                            `fno` INT NOT NULL AUTO_INCREMENT primary key,
                            `fund_category` VARCHAR(50) NOT NULL,
                            `fund_name` VARCHAR(50) NOT NULL,
                            `fund_earning_ratio` DOUBLE NULL,
                            `fund_standard` DOUBLE NULL,
                            `fund_change_rate` DOUBLE NULL,
                            `fund_scale` DOUBLE NULL,
                            `fund_company` VARCHAR(50) NULL,
                            `fund_lisk` VARCHAR(50) NULL,
                            `fund_type` VARCHAR(50) NULL,
                            `fund_charge` VARCHAR(50) NULL,
                            `fund_regdate` VARCHAR(50) NULL

);

CREATE TABLE `tbl_forex` (
                             `feno` INT NOT NULL AUTO_INCREMENT primary key,
                             `forex_name` VARCHAR(50) NOT NULL COMMENT 'cur_unit',
                             `forex_basic_rate` DECIMAL(15,2) NOT NULL COMMENT 'deal_bas_r',
                             `forex_buy` DECIMAL(15,2) NOT NULL COMMENT 'ttb',
                             `forex_sell` DECIMAL(15,2) NOT NULL COMMENT 'tts'

);

CREATE TABLE `tbl_dict` (
                            `dno` INT NOT NULL AUTO_INCREMENT primary key,
                            `word` VARCHAR(50) NOT NULL,
                            `content` TEXT NOT NULL

);

CREATE TABLE `tbl_board` (
                             `bno` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             `uno` INT NOT NULL,
                             `content` TEXT NOT NULL,
                             `writer` VARCHAR(255) NOT NULL,
                             `createdAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             `updatedAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             FOREIGN KEY (`uno`) REFERENCES `tbl_user` (`uno`),
                             FOREIGN KEY (`writer`) REFERENCES `tbl_user` (`user_name`)
);


CREATE TABLE `tbl_stock_chart` (
                                   `scno` INT NOT NULL AUTO_INCREMENT primary key,
                                   `stock_symbol` VARCHAR(10) NOT NULL,
                                   `stock_datetime` DATETIME NOT NULL,
                                   `stock_price` INT NOT NULL

);

CREATE TABLE `tbl_fund_chart` (
                                  `fcno` INT NOT NULL AUTO_INCREMENT primary key,
                                  `fund_name` VARCHAR(50) NOT NULL,
                                  `fund_datetime` VARCHAR(50) NOT NULL,
                                  `fund_val` DOUBLE NOT NULL,
                                  `ben_val` DOUBLE NOT NULL,
                                  `type_val` DOUBLE NOT NULL

);

CREATE TABLE `tbl_forex_chart` (
                                   `fecno` INT NOT NULL AUTO_INCREMENT primary key,
                                   `forex_basic_rate` VARCHAR(50) NOT NULL COMMENT 'deal_bas_r',
                                   `forex_datetime` DATE NOT NULL COMMENT '직접입력',
                                   `forex_name` VARCHAR(50) NOT NULL COMMENT 'cur_unit'

);

CREATE TABLE `tbl_news` (
                            `nno` INT NOT NULL AUTO_INCREMENT primary key,
                            `stock_symbol` VARCHAR(10) NOT NULL,
                            `title` TEXT NOT NULL COMMENT 'title',
                            `content` TEXT NOT NULL COMMENT 'summary',
                            `link` TEXT NULL COMMENT 'content_url',
                            `image` TEXT NULL COMMENT 'image_url'

);


CREATE TABLE `tbl_wish` (
                            `wno` VARCHAR(255) NOT NULL,
                            `uno` INT NOT NULL,
                            `tno` VARCHAR(255) NOT NULL,
                            `pno` INT NOT NULL,
                            `createAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            PRIMARY KEY (`wno`, `uno`, `tno`),
                            FOREIGN KEY (`uno`) REFERENCES `tbl_user` (`uno`),
                            FOREIGN KEY (`tno`) REFERENCES `tbl_type` (`tno`)
);

CREATE TABLE `tbl_dict_wish` (
                                 `dwno` VARCHAR(255) NOT NULL,
                                 `uno` INT NOT NULL,
                                 `dno` INT NOT NULL,
                                 `memo` TEXT NULL,
                                 `createAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 PRIMARY KEY (`dwno`, `uno`, `dno`),
                                 FOREIGN KEY (`uno`) REFERENCES `tbl_user` (`uno`),
                                 FOREIGN KEY (`dno`) REFERENCES `tbl_dict` (`dno`)
);

CREATE TABLE `tbl_installment` (
                                   `ino` INT NOT NULL AUTO_INCREMENT primary key,
                                   `installment_category` VARCHAR(50) NOT NULL COMMENT '복리/단리',
                                   `installment_name` VARCHAR(50) NOT NULL COMMENT 'fin_prdt_nm',
                                   `installment_bank` VARCHAR(50) NOT NULL COMMENT 'kor_co_nm',
                                   `installment_max_rate` TEXT NULL COMMENT 'intr_rate2',
                                   `installment_normal_rate` TEXT NULL COMMENT 'intr_rate',
                                   `installment_join` VARCHAR(50) NULL COMMENT 'join_deny',
                                   `installment_stream` VARCHAR(50) NULL COMMENT 'save_trm',
                                   `installment_amount` TEXT NULL COMMENT 'max_limit',
                                   `installment_way` VARCHAR(50) NULL COMMENT 'join_way',
                                   `installment_target` VARCHAR(50) NULL COMMENT 'join_member'

);

CREATE TABLE `tbl_kospi` (
                             `kno` INT NOT NULL AUTO_INCREMENT primary key,
                             `kospi_datetime` VARCHAR(50) NOT NULL COMMENT '날짜',
                             `kospi_value` DOUBLE NOT NULL COMMENT '종가'
);










ALTER TABLE `tbl_wish` ADD CONSTRAINT `FK_tbl_user_TO_tbl_wish_1` FOREIGN KEY (
                                                                               `uno`
    )
    REFERENCES `tbl_user` (
                           `uno`
        );

ALTER TABLE `tbl_wish` ADD CONSTRAINT `FK_tbl_type_TO_tbl_wish_1` FOREIGN KEY (
                                                                               `tno`
    )
    REFERENCES `tbl_type` (
                           `tno`
        );

ALTER TABLE `tbl_dict_wish` ADD CONSTRAINT `FK_tbl_user_TO_tbl_dict_wish_1` FOREIGN KEY (
                                                                                         `uno`
    )
    REFERENCES `tbl_user` (
                           `uno`
        );

ALTER TABLE `tbl_dict_wish` ADD CONSTRAINT `FK_tbl_dict_TO_tbl_dict_wish_1` FOREIGN KEY (
                                                                                         `dno`
    )
    REFERENCES `tbl_dict` (
                           `dno`
        );

