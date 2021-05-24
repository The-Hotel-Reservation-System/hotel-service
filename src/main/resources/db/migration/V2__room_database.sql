CREATE TABLE room_status
(
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL
);

INSERT INTO room_status (id, name)
VALUES
('1', 'AVAILABLE'),
('2', 'NOT_AVAILABLE'),
('3', 'FIXING'),
('4', 'CLOSED');

CREATE TABLE room_type
(
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL,
    price varchar(255) NOT NULL
);

INSERT INTO room_type (id, name, price)
VALUES
('1', 'NORMAL', '1000'),
('2', 'VIP', '1300'),
('3', 'KING', '4000'),
('4', 'QUEEN', '2000'),
('5', 'DOUBLE', '1500'),
('6', 'SINGLE', '500');


CREATE TABLE room
(
    id bigserial PRIMARY KEY,
    type_id bigserial NOT NULL,
    hotel_id bigserial NOT NULL,
    status_id bigserial NOT NULL,
    FOREIGN KEY (hotel_id) REFERENCES hotel(id),
    FOREIGN KEY (status_id) REFERENCES room_status(id),
    FOREIGN KEY (type_id) REFERENCES room_type(id)
);