-- GENRE's
insert into GENRE(name) values('Science fiction');
insert into GENRE(name) values('Romance');
insert into GENRE(name) values('Drama');
insert into GENRE(name) values('Mystery');
insert into GENRE(name) values('Horror');
insert into GENRE(name) values('Childrens');
insert into GENRE(name) values('History');

-- AUTHOR's
insert into AUTHOR(name,nationality) values('Yann Martel','Cannada');
insert into AUTHOR(name,nationality) values('Amish Tripathi','India');
insert into AUTHOR(name,nationality) values('J. R. R. Tolkien','United Kingdom');
insert into AUTHOR(name,nationality) values('Chetan Bhagat','India');

-- PUBLISHER's
insert into PUBLISHER(name,country) values('Random House of Canada','Cannada');
insert into PUBLISHER(name,country) values('Rupa Publications','India');
insert into PUBLISHER(name,country) values('Westland Publications','India');
insert into PUBLISHER(name,country) values('Westland Publications','United Kingdom');
insert into PUBLISHER(name,country) values('Allen & Unwin','Australia');

-- BOOK's
insert into BOOKS(name,author_id,genre_id,published_date,publisher_id,language,cost,currency) 
values('Life of Pi',1, 1, TO_DATE('11-09-2001', 'DD-MM-YYY'), 1,'English', 63, 'INR');
insert into BOOKS(name,author_id,genre_id,published_date,publisher_id,language,cost,currency)
values('The Secret of the Nagas',2, 1, TO_DATE('21-07-2011', 'DD-MM-YYY'), 2,'English', 257, 'INR');
insert into BOOKS(name,author_id,genre_id,published_date,publisher_id,language,cost,currency)
values('Lord of the Rings',3, 3, TO_DATE('21-12-2001', 'DD-MM-YYY'), 4,'English', 1500, 'INR');
insert into BOOKS(name,author_id,genre_id,published_date,publisher_id,language,cost,currency)
values('2 States',4, 2, TO_DATE('21-08-2010', 'DD-MM-YYY'), 2,'English', 249, 'INR');
insert into BOOKS(name,author_id,genre_id,published_date,publisher_id,language,cost,currency)
values('half girl friend',4, 2, TO_DATE('21-08-2012', 'DD-MM-YYY'), 2,'English', 249, 'INR');

-- ENTITIE'S
insert into ENTITY(name) values('Book');
insert into ENTITY(name) values('Author');
insert into ENTITY(name) values('Publisher');
insert into ENTITY(name) values('Genre');

-- ENTITY VIEW's
insert into ENTITY_VIEW(entity_id,attribute,attribute_label,input_type,is_entity)
values(1,'name','Book Name','text',false);
insert into ENTITY_VIEW(entity_id,attribute,attribute_label,input_type,is_entity,composed_entity)
values(1,'author','Author Name','select',true,'author');
insert into ENTITY_VIEW(entity_id,attribute,attribute_label,input_type,is_entity)
values(1,'publishedDate','Published Date','date',false);
insert into ENTITY_VIEW(entity_id,attribute,attribute_label,input_type,is_entity,composed_entity)
values(1,'publisher','Publisher','select',true,'genre');
insert into ENTITY_VIEW(entity_id,attribute,attribute_label,input_type,is_entity)
values(1,'language','Language','text',false);
insert into ENTITY_VIEW(entity_id,attribute,attribute_label,input_type,is_entity)
values(1,'cost','Price','text',false);
insert into ENTITY_VIEW(entity_id,attribute,attribute_label,input_type,is_entity)
values(1,'currency','Currency','text',false);



