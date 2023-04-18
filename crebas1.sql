/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2023/4/14 20:53:00                           */
/*==============================================================*/


drop
table if exists dish_info;

drop table if exists _order;

drop table if exists address;

drop table if exists canteen;

drop table if exists category;

drop table if exists dish;

drop table if exists flavour;

drop table if exists order_dish;

drop table if exists stall;

drop table if exists user;

drop table if exists user_address;

/*==============================================================*/
/* Table: _order                                                */
/*==============================================================*/
create table _order
(
   order_id             int not null auto_increment,
   user_id              int not null,
   stall_id             int not null,
   address_id           int not null,
   order_create_time    datetime not null,
   order_price          int not null,
   order_status         smallint not null,
   stall_accept_time    datetime,
   last_update          datetime not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   send_time            datetime,
   arrive_time          datetime,
   primary key (order_id)
);

/*==============================================================*/
/* Table: address                                               */
/*==============================================================*/
create table address
(
   address_id           int not null auto_increment,
   address_name         varchar(64) not null,
   last_update          datetime not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (address_id)
);

/*==============================================================*/
/* Table: canteen                                               */
/*==============================================================*/
create table canteen
(
   canteen_id           int not null auto_increment,
   user_id              int not null,
   address_id           int not null,
   canteen_name         varchar(20) not null,
   last_update          datetime not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (canteen_id),
   unique key AK_canteen_name (canteen_name)
);

/*==============================================================*/
/* Table: category                                              */
/*==============================================================*/
create table category
(
   category_id          int not null auto_increment,
   stall_id             int not null,
   category_name        varchar(64) not null,
   last_update          datetime not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (category_id)
);

/*==============================================================*/
/* Table: dish                                                  */
/*==============================================================*/
create table dish
(
   dish_id              int not null auto_increment,
   category_id          int not null,
   dish_name            varchar(50) not null,
   last_update          datetime not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   price                real not null,
   dish_desp            varchar(128) not null,
   inventory            int not null,
   primary key (dish_id)
);

/*==============================================================*/
/* Table: flavour                                               */
/*==============================================================*/
create table flavour
(
   flavour_id           int not null auto_increment,
   dish_id              int not null,
   flavour_desp         varchar(32) not null,
   last_update          datetime not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (flavour_id)
);

/*==============================================================*/
/* Table: order_dish                                            */
/*==============================================================*/
create table order_dish
(
   order_id             int not null,
   dish_id              int not null,
   flavour_id           int not null,
   dish_number          int not null,
   last_update          datetime not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (order_id, dish_id, flavour_id)
);

/*==============================================================*/
/* Table: stall                                                 */
/*==============================================================*/
create table stall
(
   stall_id             int not null auto_increment,
   user_id              int not null,
   canteen_id           int not null,
   stall_name           varchar(50) not null,
   stall_desp           varchar(128) not null,
   last_update          datetime not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (stall_id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   user_id              int not null auto_increment,
   username             varchar(20) not null,
   user_type            int not null,
   password             varchar(30) not null,
   cellphone            varchar(11) not null,
   user_create_time     datetime not null,
   last_update          datetime not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (user_id),
   unique key AK_cellphone (cellphone)
);

/*==============================================================*/
/* Index: username                                              */
/*==============================================================*/
create unique index username on user
(
   username
);

/*==============================================================*/
/* Table: user_address                                          */
/*==============================================================*/
create table user_address
(
   user_id              int not null,
   address_id           int not null,
   primary key (user_id, address_id)
);

/*==============================================================*/
/* View: dish_info                                              */
/*==============================================================*/
create VIEW  dish_info
as
select `dblab4`.`dish`.`dish_id`     AS `dish_id`,
       `dblab4`.`dish`.`category_id` AS `category_id`,
       `dblab4`.`dish`.`dish_name`   AS `dish_name`,
       `dblab4`.`dish`.`last_update` AS `last_update`,
       `dblab4`.`dish`.`price`       AS `price`,
       `dblab4`.`dish`.`dish_desp`   AS `dish_desp`,
       `dblab4`.`dish`.`inventory`   AS `inventory`,
       `c`.`category_name`           AS `category_name`,
       `s`.`stall_name`              AS `stall_name`,
       `s`.`stall_id`                AS `stall_id`,
       `c2`.`canteen_name`           AS `canteen_name`
from (((`dblab4`.`dish` left join `dblab4`.`category` `c` on ((`dblab4`.`dish`.`category_id` = `c`.`category_id`))) left join `dblab4`.`stall` `s` on ((`s`.`stall_id` = `c`.`stall_id`)))
         left join `dblab4`.`canteen` `c2` on ((`c2`.`canteen_id` = `s`.`canteen_id`)));

alter table _order add constraint FK_order_address foreign key (user_id, address_id)
      references user_address (user_id, address_id) on delete restrict on update cascade;

alter table _order add constraint FK_order_address2 foreign key (address_id)
      references address (address_id) on delete restrict on update cascade;

alter table _order add constraint FK_stall_order foreign key (stall_id)
      references stall (stall_id) on delete restrict on update cascade;

alter table _order add constraint FK_user_order foreign key (user_id)
      references user (user_id) on delete restrict on update cascade;

alter table canteen add constraint FK_canteen_address foreign key (address_id)
      references address (address_id) on delete cascade on update cascade;

alter table canteen add constraint FK_user_canteen foreign key (user_id)
      references user (user_id) on delete restrict on update cascade;

alter table category add constraint FK_stall_category foreign key (stall_id)
      references stall (stall_id) on delete cascade on update cascade;

alter table dish add constraint FK_dish_category foreign key (category_id)
      references category (category_id) on delete cascade on update cascade;

alter table flavour add constraint FK_dish_flavour foreign key (dish_id)
      references dish (dish_id) on delete cascade on update cascade;

alter table order_dish add constraint FK_order_dish foreign key (flavour_id)
      references flavour (flavour_id) on delete restrict on update cascade;

alter table order_dish add constraint FK_order_dish2 foreign key (order_id)
      references _order (order_id) on delete cascade on update cascade;

alter table order_dish add constraint FK_order_dish3 foreign key (dish_id)
      references dish (dish_id) on delete restrict on update cascade;

alter table stall add constraint FK_canteen_stall foreign key (canteen_id)
      references canteen (canteen_id) on delete restrict on update cascade;

alter table stall add constraint FK_user_stall foreign key (user_id)
      references user (user_id) on delete restrict on update cascade;

alter table user_address add constraint FK_user_address foreign key (user_id)
      references user (user_id) on delete cascade on update cascade;

alter table user_address add constraint FK_user_address2 foreign key (address_id)
      references address (address_id) on delete cascade on update cascade;


DELIMITER ;;
create trigger upd_order_accept_time before update on _order for each row begin
	if(new.order_status = 2) then
		set new.stall_accept_time = now();
	end if;
end;;


DELIMITER ;;
create trigger upd_order_arrive_time before update on _order for each row begin
	if(new.order_status = 4) then
		set new.arrive_time = now();
	end if;
end;;


DELIMITER ;;
create trigger upd_order_send_time before update on _order for each row begin
	if(new.order_status = 3) then
		set new.send_time = now();
	end if;
end;;

