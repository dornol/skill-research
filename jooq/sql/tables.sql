create table bid
(
    id           serial primary key,
    title        varchar(200) not null,

    start_date   date         not null,
    end_date     date         not null,

    state        varchar(20)  not null,

    created_date date         not null,
    created_by   integer,
)

create table item
(
    id serial primary key,
    name varchar(200) not null,
    sales_unit varchar(30) not null,

    created_date date         not null,
    created_by   integer,
)


create table bid_item
(
    id      serial primary key,
    bid_id  integer not null references bid(id),
    item_id integer not null references item(id),
    count   int     not null,

    created_date date         not null,
    created_by   integer,
)