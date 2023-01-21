CREATE TABLE customer(
    id integer  not null,
    firstName varchar(30) not null,
    lastName varchar(30) not null,
    identityId varchar(11) not null,
    address varchar(11) not null,
    CONSTRAINT pk_customer_id PRIMARY KEY (id)
)