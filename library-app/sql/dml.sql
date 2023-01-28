# 데이터 넣기 , 조회 , 수정, 삭제 DML 데이터 조작 언어

insert into fruit(name, price, stocked_date)
values ('바나나', 1000, '2023-01-01');

# select * from fruit 전체 조회
select * from fruit where name = '바나나';

# 업데이트 하기
update fruit
set price = 1500
where name = '사과';

select * from fruit;

# 데이터의 삭제
delete from fruit
where name = '사과';
