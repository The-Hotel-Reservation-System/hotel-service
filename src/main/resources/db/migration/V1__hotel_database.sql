CREATE TABLE hotel
(
    id bigserial PRIMARY KEY,
    address varchar(255) NOT NULL,
    name varchar(36) NOT NULL,
    phone varchar(15),
    created_date TIMESTAMP WITH TIME ZONE
);