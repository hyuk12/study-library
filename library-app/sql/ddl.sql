# DDL 데이터베이스의 데이터를 정의하는 언어

# create database library; 데이터 베이스 만들기

# show databases; 데이터 베이스 모두 보기

# use library;  이데이터베이스를 사용하겠다

# show tables; 테이블 보기
# option command l 자동 줄 맞춤

create table `fruit`
(
    `id`           bigint auto_increment,
    `name`         varchar(20),
    `price`        int,
    `stocked_date` date,
    primary key (`id`)
);


