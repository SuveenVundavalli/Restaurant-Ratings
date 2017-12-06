create database Urbanspoon;

use Urbanspoon;

create table restaurant(
id int(11) not null auto_increment,
govt_registration_id varchar(256) not null unique,
name varchar(256) not null unique,
password varchar(100)  not null,
logo_name varchar(256) unique, 
primary key(id)
);

insert into restaurant(govt_registration_id,name,password,logo_name) 
values('IND 01 TS 01','Hotel Swagath Grand',PASSWORD('A1B2C3!$'),'1.jpg');
insert into restaurant(govt_registration_id,name,password,logo_name) 
values('IND 01 TS 02','Paradise',PASSWORD('A1B2C3!$'),'2.jpg');


create table branch(
id int(11) not null auto_increment,
location varchar(256) not null,
city varchar(256) not null,
state varchar(256) not null,
country varchar(256) not null,
postal_code int(11) not null,
restaurant_id int not null,
foreign key(restaurant_id) references restaurant(id),
primary key(id)
);

insert into branch values(1,'2-36 Kothaguda Cross Roads Kondapur','Hyderabad','Telangana','India',500032,1);

insert into branch values(2,'13-6-432/31 Srinivas Nagar Colony Pillar No. 92 Attapur Ring Road  Mehdipatnam','Hyderabad','Telangana','India',500030,1);

insert into branch values(3,'Plot no 22 & 23 Vinayaknagar Gachibowli Main Road','Hyderabad','Telangana','India',500030,2);

insert into branch values(4,'Paradise Circle S D Road','Secunderabad','Telangana','India',500030,2);

create table branch_images(branch_id int not null,
image_name varchar(256) not null,
foreign key(branch_id) references branch(id)
);

insert into branch_images values(1,'1_1.jpg');
insert into branch_images values(1,'1_2.jpg');
insert into branch_images values(1,'1_3.jpg');
insert into branch_images values(1,'1_4.jpg');

insert into branch_images values(2,'2_1.jpg');
insert into branch_images values(2,'2_2.jpg');
insert into branch_images values(2,'2_3.jpg');
insert into branch_images values(2,'2_4.jpg');

insert into branch_images values(3,'3_1.jpg');
insert into branch_images values(3,'3_2.jpg');
insert into branch_images values(3,'3_3.jpg');
insert into branch_images values(3,'3_4.jpg');

insert into branch_images values(4,'4_1.jpg');
insert into branch_images values(4,'4_2.jpg');
insert into branch_images values(4,'4_3.jpg');
insert into branch_images values(4,'4_4.jpg');


create table cuisine(
id int(11) not null auto_increment,
name varchar(256) not null unique,
country varchar(256) not null,
primary key(id)
);

insert into cuisine values(1,'North Indian','India');
insert into cuisine values(2,'South Indian','India');

create table recipe(
id int(11) not null auto_increment,
name varchar(256) not null unique,
description varchar(256) not null,
cuisine_id int(11) not null,
is_veg boolean not null,
foreign key(cuisine_id) references cuisine(id),
primary key(id)
);


insert into recipe(name,description,cuisine_id,is_veg) 
values('Paneer Kofta','Paneer Kofta',1,true);
insert into recipe(name,description,cuisine_id,is_veg) 
values('Bhindi Bhaji','Bhindi Bhaji',1,true);
insert into recipe(name,description,cuisine_id,is_veg) 
values('Methi Aloo','Methi Aloo',1,true);
insert into recipe(name,description,cuisine_id,is_veg) 
values('Shahi Paneer','Shahi Paneer',1,true);

insert into recipe(name,description,cuisine_id,is_veg) 
values('Chicken Shawarma','Chicken Shawarma',1,false);

insert into recipe(name,description,cuisine_id,is_veg) 
values('Kolhapuri Chicken','Kolhapuri Chicken',1,false);

insert into recipe(name,description,cuisine_id,is_veg) 
values('Chicken Shami kabab','Chicken Shami kabab',1,false);

insert into recipe(name,description,cuisine_id,is_veg) 
values('Garlic Chicken','Garlic Chicken',1,false);


insert into recipe(name,description,cuisine_id,is_veg) 
values('Bendakaya Vepudu','Bendakaya Vepudu',2,true);
insert into recipe(name,description,cuisine_id,is_veg) 
values('Kakarakaya Vepudu','Kakarakaya Vepudu',2,true);
insert into recipe(name,description,cuisine_id,is_veg) 
values('Bendakaya Pulusu','Bendakaya Pulusu',2,true);
insert into recipe(name,description,cuisine_id,is_veg) 
values('Kakarakaya Pulusu','Kakarakaya Pulusu',2,true);
insert into recipe(name,description,cuisine_id,is_veg) 
values('Korrameenu Vepudu','Korrameenu Vepudu',2,false);
insert into recipe(name,description,cuisine_id,is_veg) 
values('Royaa Vepudu','Royaa Vepudu',2,false);
insert into recipe(name,description,cuisine_id,is_veg) 
values('Korrameenu Pulusu','Korrameenu Pulusu',2,false);
insert into recipe(name,description,cuisine_id,is_veg) 
values('Royya Vepudu Pulusu','Royya Vepudu Pulusu',2,false);


create table serve(
branch_id int(11) not null,
recipe_id int(11) not null,
price int(11) not null,
image_name varchar(256) not null unique,
foreign key(branch_id) references branch(id),
foreign key(recipe_id) references recipe(id),
primary key(branch_id, recipe_id)
);

insert into serve(branch_id,recipe_id,price,image_name) values(1,1,100.00,'1_1.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(1,2,100.00,'1_2.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(1,3,100.00,'1_3.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(1,4,100.00,'1_4.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(1,5,100.00,'1_5.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(1,6,100.00,'1_6.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(1,7,100.00,'1_7.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(1,8,100.00,'1_8.jpg');

insert into serve(branch_id,recipe_id,price,image_name) values(2,1,100.00,'2_1.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(2,2,100.00,'2_2.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(2,3,100.00,'2_3.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(2,4,100.00,'2_4.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(2,5,100.00,'2_5.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(2,6,100.00,'2_6.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(2,7,100.00,'2_7.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(2,8,100.00,'2_8.jpg');

insert into serve(branch_id,recipe_id,price,image_name) values(3,1,100.00,'3_1.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(3,2,100.00,'3_2.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(3,3,100.00,'3_3.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(3,4,100.00,'3_4.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(3,5,100.00,'3_5.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(3,6,100.00,'3_6.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(3,7,100.00,'3_7.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(3,8,100.00,'3_8.jpg');

insert into serve(branch_id,recipe_id,price,image_name) values(4,1,100.00,'4_1.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(4,2,100.00,'4_2.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(4,3,100.00,'4_3.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(4,4,100.00,'4_4.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(4,5,100.00,'4_5.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(4,6,100.00,'4_6.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(4,7,100.00,'4_7.jpg');
insert into serve(branch_id,recipe_id,price,image_name) values(4,8,100.00,'4_8.jpg');


create table user(
id int(11) not null auto_increment,
name varchar(256) not null,
gender varchar(20) not null,
email varchar(256) not null unique,
password varchar(256) not null,
mobile_number bigint(12) not null unique,
primary key(id)
);


create table feedback_type(
id int(11) not null auto_increment,
description varchar(256) not null,
primary key(id)
);

insert into feedback_type values(1,'Overall');
insert into feedback_type values(2,'Quality');
insert into feedback_type values(3,'Quantity');
insert into feedback_type values(4,'Ambience');
insert into feedback_type values(5,'Service');

create table feedback(
id int(11) not null auto_increment,
user_id	int(11) not null,
branch_id int(11) not null,
feedback_type_id int(11)  null,
recipe_id int(11)  null,
feedback_date date,
visited_date date,
comments varchar(256) not null,
ratings int(11) not null,
foreign key(user_id) references user(id),
foreign key(branch_id) references branch(id),
foreign key(recipe_id) references recipe(id),
foreign key(feedback_type_id) references feedback_type(id),
primary key(id)
);

