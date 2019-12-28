--advertisementsize
create table if not exists ad_size(
id_size int not null primary key auto_increment,
id_ad int not null,
url varchar(100),
size varchar(50)
);

--advertisement
create table if not exists ad(
index int not null primary key auto_increment,
name varchar(100),
desc varchar(100),
so varchar(50),
foreign key(index) references ad_size(id_ad)
);




