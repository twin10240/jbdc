select t1.no, t1.title, t1.state, t2.name from book t1, author t2 where t1.author_no = t2.no;

insert into book values(seq_book.nextval, title, state, author_no);

select * from author;

select * from book;

update book set state = '대여가능' where no = 10;
commit;
rollback;