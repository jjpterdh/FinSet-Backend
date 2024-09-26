drop schema finset;
create schema finset;
use finset;

CREATE TABLE `tbl_user_type` (
                                `itno` INT NOT NULL AUTO_INCREMENT,
                                `it_name` VARCHAR(20) NOT NULL COMMENT '안정형/안정추구형/위험중립형/적극투자형/공격투자형',
                                PRIMARY KEY (`itno`)
);

INSERT INTO tbl_usertype (itno, it_name) VALUES (1, '안정형');
INSERT INTO tbl_usertype (itno, it_name) VALUES (2, '안정추구형');
INSERT INTO tbl_usertype (itno, it_name) VALUES (3, '위험중립형');
INSERT INTO tbl_usertype (itno, it_name) VALUES (4, '적극투자형');
INSERT INTO tbl_usertype (itno, it_name) VALUES (5, '공격투자형');

CREATE TABLE `tbl_user` (
                            `uno` INT NOT NULL AUTO_INCREMENT,
                            `email` VARCHAR(50) NOT NULL UNIQUE,
                            `password` VARCHAR(255) NOT NULL,
                            `user_name` VARCHAR(50) NOT NULL,
                            `itno` INT NULL DEFAULT NULL,
                            `status` INT NOT NULL DEFAULT 1 COMMENT '기본: 1 / 탈퇴: 0',
                            `createdAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            `status_date` TIMESTAMP NULL,
                            PRIMARY KEY (`uno`),
                            FOREIGN KEY (`itno`) REFERENCES `tbl_user_type`(`itno`)
);

CREATE INDEX idx_tbl_user_email ON tbl_user (email);

CREATE TABLE `tbl_stock` (
                             `sno` INT NOT NULL AUTO_INCREMENT,
                             `stock_symbol` VARCHAR(10) NOT NULL COMMENT '직접입력',
                             `stock_name` VARCHAR(50) NOT NULL COMMENT '(주식현재가 시세) rprs_mrkt_kor_name',
                             `stock_price` INT NOT NULL COMMENT '(주식현재가 시세) stck_prpr',
                             `price_change_rate` DOUBLE NOT NULL COMMENT '(주식현재가 시세) prdy_ctrt',
                             `stock_volume` INT NOT NULL COMMENT '(주식현재가 시셰) acml_vol',
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
                                 `updatedAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 PRIMARY KEY (`bno`),
                                 FOREIGN KEY (`uno`) REFERENCES `tbl_user`(`uno`)
);

CREATE TABLE `tbl_stock_chart` (
                                   `scno` INT NOT NULL AUTO_INCREMENT,
                                   `stock_datetime` DATE NOT NULL,
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

CREATE TABLE `tbl_kospi` (
                             `kno` INT NOT NULL AUTO_INCREMENT,
                             `kospi_date` DATE NOT NULL,
                             `kospi_val` DOUBLE NOT NULL ,
                             PRIMARY KEY (`kno`)
);

CREATE TABLE `tbl_keyword` (
                               `keno` INT NOT NULL AUTO_INCREMENT,
                               `uno` INT NOT NULL,
                               `keyword` VARCHAR(255) NOT NULL,
                               `createdAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               PRIMARY KEY (`keno`),
                               FOREIGN KEY (`uno`) REFERENCES `tbl_user`(`uno`)
);

-- 사용자 권한 테이블
create table tbl_auth
(
    id varchar(50) not null,       -- 사용자 id
    authority char(50) not null, -- 권한 문자열 role_admin, role_manager, role_member,
    primary key (id, authority),   -- 복합키
    constraint fk_authorities_users foreign key (id) references tbl_user (email)
);
