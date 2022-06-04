create table if not exists t_commodity
(
    commodity_id   bigint         default 0    not null comment '商品ID'
        primary key,
    commodity_name varchar(64)    default ''   not null comment '商品名称',
    price          decimal(10, 2) default 0.00 not null comment '商品价格',
    remain         bigint         default 0    not null comment '库存',
    create_time    datetime                    not null,
    creator        bigint         default 0    not null,
    update_time    datetime                    not null,
    updater        bigint         default 0    not null
)
    comment '商品';

create table if not exists t_order
(
    order_id       bigint                      not null comment '订单ID',
    user_id        bigint                      not null comment '用户ID',
    commodity_id   bigint                      not null comment '货物ID',
    commodity_name varchar(64)                 not null comment '货物名称',
    unit_price     decimal(10, 2) default 0.00 not null comment '单价',
    quantity       int            default 1    not null comment '数量',
    total_amount   decimal(10, 2) default 0.00 not null comment '总价',
    real_amount    decimal(10, 2) default 0.00 not null comment '成交价',
    create_time    datetime                    not null,
    creator        bigint                      not null,
    update_time    datetime                    not null,
    updater        bigint                      not null
)
    comment '订单表';

create table if not exists t_user
(
    user_id     bigint      default 0  not null comment '用户ID'
        primary key,
    user_name   varchar(32) default '' not null comment '用户名',
    user_pwd    varchar(32) default '' not null comment '密码',
    create_time datetime               not null,
    creator     bigint      default 0  not null,
    update_time datetime               not null,
    updater     bigint      default 0  not null
)
    comment '用户表';

