create table koomini_memeber(
	m_id varchar2(10 char) primary key,
	m_pw varchar2(24 char) not null,
	m_name varchar2(10 char) not null,
	m_phone varchar2(13 char) not null,
	m_addr varchar2(200 char) not null,
	m_pic varchar2(200 char) not null,
	m_role char(1 char)
);

select * from koomini_memeber;

drop table koomini_memeber cascade constraint;

insert into koomini_memeber values('admin', 'admin11', '운영자', '010-0000-0000', '비공개', 'asdf', '1');

------------------

create table koomini_post(
	p_num number(5) primary key,
	p_writer_id varchar2(10 char),
	p_txt varchar2(300 char) not null,
	p_when date not null,
	p_color char(7) not null,
	constraint post_writer
		foreign key(p_writer_id) references koomini_memeber(m_id) 
		on delete set null
);
create sequence koomini_post_num;
drop table koomini_post cascade constraint
drop sequence koomini_post_num

select * from koomini_post;

insert into koomini_post values (koomini_post_num.nextval, 'admin', 'testtest', sysdate, '#aa0000')

select * from (
select rownum as rn, p_num, p_writer_id, p_txt, p_when, p_color, m_pic from (
select * from koomini_memeber, koomini_post where m_id = p_writer and (p_writer like '%'||''||'%' or p_txt like '%'||''||'%') order by p_when desc))
where rn > = 1 and rn < = 4

--------------------

create table koomini_post_comment(
	c_num number(5) primary key,
	c_p_num number(5) not null,
	c_writer_id varchar2(10 char),
	c_txt varchar2(200 char) not null,
	c_when date not null,
	constraint post_comment
		foreign key(c_p_num) references koomini_post(p_num)
		on delete cascade,
	constraint comment_writer
		foreign key(c_writer_id) references koomini_memeber(m_id)
		on delete set null
);
create sequence koomini_comment_num;
drop table koomini_post_comment cascade constraint

select * from koomini_post_comment;

insert into koomini_post_comment values (koomini_comment_num.nextval, 4, 'admin', 'test_comment', sysdate)
