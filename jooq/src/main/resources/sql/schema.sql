create table author(
    id bigserial primary key,
    name varchar(30) not null
);

create table book(
    id bigserial primary key,
    title varchar(255) not null,
    author_id bigint not null references author(id),

    created_date timestamp not null default current_timestamp,
    updated_date timestamp not null default current_timestamp
);
