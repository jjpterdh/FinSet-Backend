use finset;

CREATE TABLE `tbl_stock` (
                             `sno` INT NOT NULL AUTO_INCREMENT COMMENT '주식 순번',
                             `stock_symbol` VARCHAR(10) NOT NULL COMMENT '종목코드',
                             `stock_name` VARCHAR(50) NOT NULL COMMENT '종목명',
                             `stock_price` INT NOT NULL COMMENT '주식 현재가',
                             `price_change_rate` DOUBLE NOT NULL COMMENT '변동율',
                             `stock_volume` INT NOT NULL COMMENT '판매량',
                             `stock_imgUrl` VARCHAR(255) NULL COMMENT '이미지 url',
                             PRIMARY KEY (`sno`)
);

CREATE TABLE `tbl_deposit` (
                               `dno` INT NOT NULL AUTO_INCREMENT COMMENT '예금 순번',
                               `deposit_category` VARCHAR(50) NOT NULL COMMENT '복리/단리',
                               `deposit_name` VARCHAR(50) NOT NULL COMMENT '상품명',
                               `deposit_bank` VARCHAR(50) NOT NULL COMMENT '은행',
                               `deposit_max_rate` TEXT NULL COMMENT '최고금리',
                               `deposit_normal_rate` TEXT NULL COMMENT '기본금리',
                               `deposit_join` VARCHAR(50) NULL COMMENT '가입제한',
                               `deposit_stream` TEXT NULL COMMENT '저축 기간(개월)',
                               `deposit_amount` TEXT NULL COMMENT '최대 금액',
                               `deposit_way` VARCHAR(50) NULL COMMENT '가입 방법',
                               `deposit_target` VARCHAR(50) NULL COMMENT '가입대상(개인 / 사업자)',
                               `deposit_link` TEXT NULL COMMENT '연결 링크',
                               `deposit_imgUrl` VARCHAR(255) NULL COMMENT '이미지 url',
                               PRIMARY KEY (`dno`)
);

CREATE TABLE `tbl_fund` (
                            `fno` INT NOT NULL AUTO_INCREMENT COMMENT '펀드 순번',
                            `fund_category` VARCHAR(50) NOT NULL COMMENT '펀드 카테고리',
                            `fund_name` VARCHAR(50) NOT NULL COMMENT '펀드명',
                            `fund_earning_ratio` DOUBLE NULL COMMENT '수익률',
                            `fund_standard` DOUBLE NULL COMMENT '기준가',
                            `fund_change_rate` DOUBLE NULL COMMENT '변동률',
                            `fund_scale` DOUBLE NULL COMMENT '규모',
                            `fund_company` VARCHAR(50) NULL COMMENT '운용사',
                            `fund_lisk` VARCHAR(50) NULL COMMENT '위험도',
                            `fund_type` VARCHAR(50) NULL COMMENT '유형',
                            `fund_charge` VARCHAR(50) NULL COMMENT '수수료',
                            `fund_regdate` DATE NULL COMMENT '설정일',
                            `fund_imgUrl` VARCHAR(255) NULL COMMENT '이미지 url',
                            `fund_link` TEXT NULL COMMENT '링크',
                            PRIMARY KEY (`fno`)
);

CREATE TABLE `tbl_forex` (
                             `feno` INT NOT NULL AUTO_INCREMENT COMMENT '외환 순번',
                             `forex_name` VARCHAR(50) NOT NULL COMMENT '외환명',
                             `forex_basic_rate` DOUBLE NOT NULL COMMENT '외환 평균가',
                             `forex_buy` DOUBLE NOT NULL COMMENT '구매가',
                             `forex_sell` DOUBLE NOT NULL COMMENT '판매가',
                             `forex_imgUrl` VARCHAR(255) NULL COMMENT '이미지 url',
                             PRIMARY KEY (`feno`)
);



CREATE TABLE `tbl_installment` (
                                   `ino` INT NOT NULL AUTO_INCREMENT COMMENT '적금 순번',
                                   `installment_category` VARCHAR(50) NOT NULL COMMENT '복리/단리',
                                   `installment_name` VARCHAR(50) NOT NULL COMMENT '상품명',
                                   `installment_bank` VARCHAR(50) NOT NULL COMMENT '은행',
                                   `installment_max_rate` TEXT NULL COMMENT '최대금리',
                                   `installment_normal_rate` TEXT NULL COMMENT '기본금리',
                                   `installment_join` VARCHAR(50) NULL COMMENT '가입 제한',
                                   `installment_stream` VARCHAR(50) NULL COMMENT '저축 기간',
                                   `installment_amount` TEXT NULL COMMENT '최대 금액',
                                   `installment_way` VARCHAR(50) NULL COMMENT '가입 방법',
                                   `installment_target` VARCHAR(50) NULL COMMENT '가입 대상',
                                   `installment_link` TEXT NULL COMMENT '링크',
                                   `installment_imgUrl` VARCHAR(255) NULL COMMENT '이미지 url',
                                   PRIMARY KEY (`ino`)
);

