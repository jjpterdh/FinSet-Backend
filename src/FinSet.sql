create database FinSet;

CREATE TABLE `tbl_user` (
                            `uno` INT NOT NULL AUTO_INCREMENT,
                            `email` VARCHAR(50) NOT NULL,
                            `user_id` VARCHAR(50) NOT NULL,
                            `password` VARCHAR(255) NOT NULL, -- 해시 값을 저장하기 위해 길이를 늘림
                            `createAt` DATE NOT NULL DEFAULT CURRENT_TIMESTAMP, -- DATETIME 또는 TIMESTAMP로 변경
                            `user_name` VARCHAR(50) NOT NULL,
                            PRIMARY KEY (`uno`)
);


CREATE TABLE `tbl_stock` (
	`sno`	INT	NOT NULL	DEFAULT AUTO_INCREMENT,
	`stock_symbol`	VARCHAR(10)	NOT NULL	COMMENT '직접입력',
	`stock_name`	VARCHAR(50)	NOT NULL	COMMENT '(주식현재가 시세) rprs_mrkt_kor_name',
	`stock_price`	VARCHAR(50)	NOT NULL	COMMENT '(주식현재가 시세) stck_prpr',
	`price_change_rate`	VARCHAR(50)	NULL	COMMENT '(주식현재가 시세) prdy_ctrt',
	`stock_volume`	VARCHAR(50)	NULL	COMMENT '(주식현재가 시셰) acml_vol'
);

CREATE TABLE `tbl_deposit` (
	`dno`	INT	NOT NULL	DEFAULT AUTO_INCREMENT,
	`deposit_category`	VARCHAR(50)	NOT NULL	COMMENT '복리/단리',
	`depoist_name`	VARCHAR(50)	NOT NULL	COMMENT 'fin_prdt_nm',
	`deposit_bank`	VARCHAR(50)	NOT NULL	COMMENT 'kor_co_nm',
	`deposit_max_rate`	TEXT	NULL	COMMENT 'spcl_cnd',
	`deposit_normal_rate`	TEXT	NULL	COMMENT 'mtrt_int',
	`deposit_join`	VARHCAR(50)	NULL	COMMENT 'join_deny',
	`deposit_stream`	TEXT	NULL	COMMENT 'etc_note',
	`deposit_amount`	TEXT	NULL	COMMENT 'etc_note',
	`deposit_way`	VARCHAR(50)	NULL	COMMENT 'join_way',
	`deposit_target`	VARCHAR(50)	NULL	COMMENT 'join_member',
	`deposit_link`	TEXT	NULL	COMMENT '직접입력'
);

CREATE TABLE `tbl_fund` (
	`fno`	INT	NOT NULL	DEFAULT AUTO_INCREMENT,
	`fund_name`	VARHCAR(50)	NOT NULL,
	`fund_earning_ratio`	DOUBLE	NULL,
	`fund_standard`	DOUBLE	NULL,
	`fund_change_rate`	DOUBLE	NULL,
	`fund_scale`	DOUBLE	NULL,
	`fund_company`	VARCAHR(50)	NULL,
	`fund_lisk`	VARCHAR(50)	NULL,
	`fund_type`	VARCHAR(50)	NULL,
	`fund_charge`	VARCHAR(50)	NULL,
	`fund_regdate`	VARCHAR(50)	NULL
);

CREATE TABLE `tbl_exchange` (
	`eno`	INT	NOT NULL	DEFAULT AUTO_INCREMENT,
	`exchange_name`	VARCHAR(50)	NOT NULL	COMMENT 'cur_unit',
	`exchange_basic_rate`	VARCHAR(50)	NOT NULL	COMMENT 'deal_bas_r',
	`exchange_buy`	VARCHAR(50)	NOT NULL	COMMENT 'ttb',
	`exchange_sell`	VARCHAR(50)	NOT NULL	COMMENT 'tts',
	`exchange_change_rate`	VARCHAR(50)	NOT NULL	COMMENT '직접계산'
);

CREATE TABLE `tbl_dict` (
	`dno`	INT	NOT NULL	DEFAULT AUTO_INCREMENT,
	`word`	VARCHAR(50)	NOT NULL,
	`mean`	TEXT	NOT NULL
);

CREATE TABLE `tbl_board` (
	`bno`	INT	NOT NULL	DEFAULT AUTO_INCREMENT,
	`uno`	INT	NOT NULL	DEFAULT AUTO_INCREMENT,
	`createAt`	DATE	NOT NULL	DEFAULT TIMESTAMP,
	`content`	TEXT	NOT NULL
);

CREATE TABLE `tbl_stock_chart` (
	`scno`	INT	NOT NULL,
	`stock_symbol`	VARCHAR(10)	NOT NULL,
	`stock_datetime`	DATETIME	NOT NULL,
	`stock_price`	VARCHAR(50)	NOT NULL
);

CREATE TABLE `tbl_fund_chart` (
	`fcno`	VARCHAR(255)	NOT NULL,
	`fund_datetime`	VARCHAR(255)	NOT NULL,
	`fund_val`	VARCHAR(255)	NOT NULL,
	`ben_val`	VARCHAR(255)	NOT NULL,
	`type_val`	VARCHAR(255)	NOT NULL
);

CREATE TABLE `tbl_exchange_chart` (
	`ecno`	VARCHAR(255)	NOT NULL,
	`exchange_basic_rate`	VARCHAR(50)	NOT NULL	COMMENT 'deal_bas_r',
	`exchange_datetime`	DATE	NOT NULL	COMMENT '직접입력',
	`exchange_name`	VARCHAR(50)	NOT NULL	COMMENT 'cur_unit'
);

CREATE TABLE `tbl_news` (
	`nno`	INT	NOT NULL,
	`stock_symbol`	VARCHAR(10)	NOT NULL,
	`title`	TEXT	NOT NULL	COMMENT 'title',
	`content`	TEXT	NOT NULL	COMMENT 'summary',
	`link`	TEXT	NULL	COMMENT 'content_url',
	`image`	TEXT	NULL	COMMENT 'image_url'
);

CREATE TABLE `tbl_stock_wish` (
	`uno`	INT	NOT NULL	DEFAULT AUTO_INCREMENT,
	`sno`	INT	NOT NULL	DEFAULT AUTO_INCREMENT
);

CREATE TABLE `tbl_deposit_wish` (
	`uno`	INT	NOT NULL	DEFAULT AUTO_INCREMENT,
	`dno`	INT	NOT NULL	DEFAULT AUTO_INCREMENT,
	`ino`	INT	NOT NULL	DEFAULT AUTO_INCREMENT
);

CREATE TABLE `tbl_fund_wish` (
	`fno`	INT	NOT NULL	DEFAULT AUTO_INCREMENT,
	`uno`	INT	NOT NULL	DEFAULT AUTO_INCREMENT
);

CREATE TABLE `tbl_dict_wish` (
	`uno`	INT	NOT NULL	DEFAULT AUTO_INCREMENT,
	`dno`	INT	NOT NULL	DEFAULT AUTO_INCREMENT
);

CREATE TABLE `tbl_installment` (
	`ino`	INT	NOT NULL	DEFAULT AUTO_INCREMENT,
	`installment_category`	VARCHAR(50)	NOT NULL	COMMENT '복리/단리',
	`installment_name`	VARCHAR(50)	NOT NULL	COMMENT 'fin_prdt_nm',
	`installment_bank`	VARCHAR(50)	NOT NULL	COMMENT 'kor_co_nm',
	`installment_max_rate`	TEXT	NULL	COMMENT 'spcl_cnd',
	`installment_normal_rate`	TEXT	NULL	COMMENT 'mtrt_int',
	`installment_join`	VARHCAR(50)	NULL	COMMENT 'join_deny',
	`installment_stream`	VARCHAR(50)	NULL	COMMENT 'etc_note',
	`installment_amount`	TEXT	NULL	COMMENT 'etc_note',
	`installment_way`	VARCHAR(50)	NULL	COMMENT 'join_way',
	`installment_target`	VARCHAR(50)	NULL	COMMENT 'join_member'
);

CREATE TABLE `tbl_exchange_wish` (
	`uno`	INT	NOT NULL	DEFAULT AUTO_INCREMENT,
	`eno`	INT	NOT NULL	DEFAULT AUTO_INCREMENT
);

ALTER TABLE `tbl_user` ADD CONSTRAINT `PK_TBL_USER` PRIMARY KEY (
	`uno`
);

ALTER TABLE `tbl_stock` ADD CONSTRAINT `PK_TBL_STOCK` PRIMARY KEY (
	`sno`
);

ALTER TABLE `tbl_deposit` ADD CONSTRAINT `PK_TBL_DEPOSIT` PRIMARY KEY (
	`dno`
);

ALTER TABLE `tbl_fund` ADD CONSTRAINT `PK_TBL_FUND` PRIMARY KEY (
	`fno`
);

ALTER TABLE `tbl_exchange` ADD CONSTRAINT `PK_TBL_EXCHANGE` PRIMARY KEY (
	`eno`
);

ALTER TABLE `tbl_dict` ADD CONSTRAINT `PK_TBL_DICT` PRIMARY KEY (
	`dno`
);

ALTER TABLE `tbl_board` ADD CONSTRAINT `PK_TBL_BOARD` PRIMARY KEY (
	`bno`
);

ALTER TABLE `tbl_stock_chart` ADD CONSTRAINT `PK_TBL_STOCK_CHART` PRIMARY KEY (
	`scno`
);

ALTER TABLE `tbl_fund_chart` ADD CONSTRAINT `PK_TBL_FUND_CHART` PRIMARY KEY (
	`fcno`
);

ALTER TABLE `tbl_exchange_chart` ADD CONSTRAINT `PK_TBL_EXCHANGE_CHART` PRIMARY KEY (
	`ecno`
);

ALTER TABLE `tbl_news` ADD CONSTRAINT `PK_TBL_NEWS` PRIMARY KEY (
	`nno`
);

ALTER TABLE `tbl_stock_wish` ADD CONSTRAINT `PK_TBL_STOCK_WISH` PRIMARY KEY (
	`uno`,
	`sno`
);

ALTER TABLE `tbl_deposit_wish` ADD CONSTRAINT `PK_TBL_DEPOSIT_WISH` PRIMARY KEY (
	`uno`,
	`dno`,
	`ino`
);

ALTER TABLE `tbl_fund_wish` ADD CONSTRAINT `PK_TBL_FUND_WISH` PRIMARY KEY (
	`fno`,
	`uno`
);

ALTER TABLE `tbl_dict_wish` ADD CONSTRAINT `PK_TBL_DICT_WISH` PRIMARY KEY (
	`uno`,
	`dno`
);

ALTER TABLE `tbl_installment` ADD CONSTRAINT `PK_TBL_INSTALLMENT` PRIMARY KEY (
	`ino`
);

ALTER TABLE `tbl_exchange_wish` ADD CONSTRAINT `PK_TBL_EXCHANGE_WISH` PRIMARY KEY (
	`uno`,
	`eno`
);

ALTER TABLE `tbl_stock_wish` ADD CONSTRAINT `FK_tbl_user_TO_tbl_stock_wish_1` FOREIGN KEY (
	`uno`
)
REFERENCES `tbl_user` (
	`uno`
);

ALTER TABLE `tbl_stock_wish` ADD CONSTRAINT `FK_tbl_stock_TO_tbl_stock_wish_1` FOREIGN KEY (
	`sno`
)
REFERENCES `tbl_stock` (
	`sno`
);

ALTER TABLE `tbl_deposit_wish` ADD CONSTRAINT `FK_tbl_user_TO_tbl_deposit_wish_1` FOREIGN KEY (
	`uno`
)
REFERENCES `tbl_user` (
	`uno`
);

ALTER TABLE `tbl_deposit_wish` ADD CONSTRAINT `FK_tbl_deposit_TO_tbl_deposit_wish_1` FOREIGN KEY (
	`dno`
)
REFERENCES `tbl_deposit` (
	`dno`
);

ALTER TABLE `tbl_deposit_wish` ADD CONSTRAINT `FK_tbl_installment_TO_tbl_deposit_wish_1` FOREIGN KEY (
	`ino`
)
REFERENCES `tbl_installment` (
	`ino`
);

ALTER TABLE `tbl_fund_wish` ADD CONSTRAINT `FK_tbl_fund_TO_tbl_fund_wish_1` FOREIGN KEY (
	`fno`
)
REFERENCES `tbl_fund` (
	`fno`
);

ALTER TABLE `tbl_fund_wish` ADD CONSTRAINT `FK_tbl_user_TO_tbl_fund_wish_1` FOREIGN KEY (
	`uno`
)
REFERENCES `tbl_user` (
	`uno`
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

ALTER TABLE `tbl_exchange_wish` ADD CONSTRAINT `FK_tbl_user_TO_tbl_exchange_wish_1` FOREIGN KEY (
	`uno`
)
REFERENCES `tbl_user` (
	`uno`
);

ALTER TABLE `tbl_exchange_wish` ADD CONSTRAINT `FK_tbl_exchange_TO_tbl_exchange_wish_1` FOREIGN KEY (
	`eno`
)
REFERENCES `tbl_exchange` (
	`eno`
);

