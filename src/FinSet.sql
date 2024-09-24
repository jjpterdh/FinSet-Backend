drop schema finset;
create schema finset;
use finset;

CREATE TABLE `tbl_user` (
                            `uno` INT NOT NULL AUTO_INCREMENT,
                            `email` VARCHAR(50) NOT NULL,
                            `password` VARCHAR(255) NOT NULL,
                            `user_name` VARCHAR(50) NOT NULL,
                            `status` INT NOT NULL DEFAULT 1 COMMENT '기본: 1 / 탈퇴: 0',
                            `createdAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            `status_date` TIMESTAMP NULL,
                            PRIMARY KEY (`uno`)
);

CREATE TABLE `tbl_stock` (
                             `sno` INT NOT NULL AUTO_INCREMENT,
                             `stock_symbol` VARCHAR(10) NOT NULL COMMENT '직접입력',
                             `stock_name` VARCHAR(50) NOT NULL COMMENT '(주식현재가 시세) rprs_mrkt_kor_name',
                             `stock_price` INT NOT NULL COMMENT '(주식현재가 시세) stck_prpr',
                             `price_change_rate` DOUBLE NULL COMMENT '(주식현재가 시세) prdy_ctrt',
                             `stock_volume` INT NULL COMMENT '(주식현재가 시셰) acml_vol',
                             PRIMARY KEY (`sno`)
);

CREATE TABLE `tbl_deposit` (
                               `dno` INT NOT NULL AUTO_INCREMENT,
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
                               `deposit_link` TEXT NULL COMMENT '직접입력',
                               PRIMARY KEY (`dno`)
);

CREATE TABLE `tbl_fund` (
                            `fno` INT NOT NULL AUTO_INCREMENT,
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
                            `fund_regdate` DATE NULL,
                            PRIMARY KEY (`fno`)
);

CREATE TABLE `tbl_forex` (
                             `feno` INT NOT NULL AUTO_INCREMENT,
                             `forex_name` VARCHAR(50) NOT NULL COMMENT 'cur_unit',
                             `forex_basic_rate` DOUBLE NOT NULL COMMENT 'deal_bas_r',
                             `forex_buy` DOUBLE NOT NULL COMMENT 'ttb',
                             `forex_sell` DOUBLE NOT NULL COMMENT 'tts',
                             PRIMARY KEY (`feno`)
);

CREATE TABLE `tbl_dict` (
                            `dino` INT NOT NULL AUTO_INCREMENT,
                            `word` VARCHAR(50) NOT NULL,
                            `content` TEXT NOT NULL,
                            PRIMARY KEY (`dino`)
);

CREATE TABLE `tbl_community` (
                                 `bno` INT NOT NULL AUTO_INCREMENT,
                                 `uno` INT NOT NULL,
                                 `content` TEXT NOT NULL,
                                 `createdAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 `updatedAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 PRIMARY KEY (`bno`),
                                 FOREIGN KEY (`uno`) REFERENCES `tbl_user`(`uno`)
);

CREATE TABLE `tbl_stock_chart` (
                                   `scno` INT NOT NULL AUTO_INCREMENT,
                                   `stock_datetime` DATETIME NOT NULL,
                                   `stock_price` INT NOT NULL,
                                   `stock_symbol` VARCHAR(10) NOT NULL,
                                   PRIMARY KEY (`scno`)
);

CREATE TABLE `tbl_fund_chart` (
                                  `fcno` INT NOT NULL AUTO_INCREMENT,
                                  `fund_name` VARCHAR(50) NOT NULL,
                                  `fund_datetime` DATE NOT NULL,
                                  `fund_val` DOUBLE NOT NULL,
                                  `ben_val` DOUBLE NOT NULL,
                                  `type_val` DOUBLE NOT NULL,
                                  PRIMARY KEY (`fcno`)
);

CREATE TABLE `tbl_forex_chart` (
                                   `fecno` INT NOT NULL AUTO_INCREMENT,
                                   `forex_basic_rate` DOUBLE NOT NULL COMMENT 'deal_bas_r',
                                   `forex_datetime` DATE NOT NULL COMMENT '직접입력',
                                   `forex_name` VARCHAR(50) NOT NULL COMMENT 'cur_unit',
                                   PRIMARY KEY (`fecno`)
);


CREATE TABLE `tbl_news` (
                            `nno` INT NOT NULL AUTO_INCREMENT,
                            `stock_symbol` VARCHAR(10) NOT NULL,
                            `title` TEXT NOT NULL COMMENT 'title',
                            `content` TEXT NOT NULL COMMENT 'summary',
                            `link` TEXT NULL COMMENT 'content_url',
                            `image` TEXT NULL COMMENT 'image_url',
                            PRIMARY KEY (`nno`)
);

CREATE TABLE `tbl_type` (
                            `tno` INT NOT NULL AUTO_INCREMENT,
                            `t_name` VARCHAR(10) NOT NULL COMMENT '예금/ 적금/ 펀드/ 주식/ 외환',
                            PRIMARY KEY (`tno`)
);

CREATE TABLE `tbl_wish` (
                            `wno` INT NOT NULL AUTO_INCREMENT,
                            `tno` INT NOT NULL,
                            `uno` INT NOT NULL,
                            `pno` INT NOT NULL,
                            `createdAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            PRIMARY KEY (`wno`),
                            FOREIGN KEY (`tno`) REFERENCES `tbl_type`(`tno`),
                            FOREIGN KEY (`uno`) REFERENCES `tbl_user`(`uno`)
);

CREATE TABLE `tbl_dict_wish` (
                                 `dwno` INT NOT NULL AUTO_INCREMENT,
                                 `uno` INT NOT NULL,
                                 `dino` INT NOT NULL,
                                 `memo` TEXT NULL,
                                 `createdAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 PRIMARY KEY (`dwno`),
                                 FOREIGN KEY (`uno`) REFERENCES `tbl_user`(`uno`),
                                 FOREIGN KEY (`dino`) REFERENCES `tbl_dict`(`dino`)
);

CREATE TABLE `tbl_installment` (
                                   `ino` INT NOT NULL AUTO_INCREMENT,
                                   `installment_category` VARCHAR(50) NOT NULL COMMENT '복리/단리',
                                   `installment_name` VARCHAR(50) NOT NULL COMMENT 'fin_prdt_nm',
                                   `installment_bank` VARCHAR(50) NOT NULL COMMENT 'kor_co_nm',
                                   `installment_max_rate` TEXT NULL COMMENT 'intr_rate2',
                                   `installment_normal_rate` TEXT NULL COMMENT 'intr_rate',
                                   `installment_join` VARCHAR(50) NULL COMMENT 'join_deny',
                                   `installment_stream` VARCHAR(50) NULL COMMENT 'save_trm',
                                   `installment_amount` TEXT NULL COMMENT 'max_limit',
                                   `installment_way` VARCHAR(50) NULL COMMENT 'join_way',
                                   `installment_target` VARCHAR(50) NULL COMMENT 'join_member',
                                   PRIMARY KEY (`ino`)
);

CREATE TABLE `tbl_userType` (
                                `itno` INT NOT NULL AUTO_INCREMENT,
                                `uno` INT NOT NULL,
                                `it_name` VARCHAR(20) NOT NULL COMMENT '안정형/안정추구형/위험중립형/적극투자형/공격투자형',
                                PRIMARY KEY (`itno`),
                                FOREIGN KEY (`uno`) REFERENCES `tbl_user`(`uno`)
);