USE mvp_hotel_booking;

-- GRANT SELECT, INSERT, UPDATE, DELETE ON mvp_hotel_booking.* TO `admin`@`localhost`;

INSERT INTO city (city_id, name, postal_code, state) 
VALUES 
  (1, 'Paris', '75000', 'France'),
  (2, 'Hanoi', '120000', 'Vietnam'), 
  (3, 'Ho Chi Minh', '70000', 'Vietnam'),
  (4, 'Vung Tau', '78000', 'Vietnam');

INSERT INTO hotel (hotel_id, name, address, description, city_id) 
VALUES
  (11, 'Shilton Paris', '123 rue du Test', 'Un hôtel de test fameux', 1),
  (12, 'UBU Paris', '45 rue des Essais', 'Un hôtel pas si fameux', 1),
  (13, 'Le Fritz Paris', '99 avenue de la Validation', 'Un hôtel de test cher', 1),
  (14, 'Le Georges Saint', '77 faubourg des Tests', 'Un hôtel de test royal', 1),
  (21, 'Shilton Al Hanoi', '44 Đ. Mạc Thiên Tích', 'Un hôtel de test fameux', 2),
  (22, 'UBU Hanoi', '45 Đ. Mạc Tích Thiên', 'Un hôtel pas si fameux', 2),
  (23, 'Le Fritz Hanoi', '66 Đ. Trần Hưng', 'Un hôtel de test cher', 2),
  (24, 'Le Victoria Palace', '77 Đ. Trần Hưng Đạo', 'Un hôtel de test royal', 2),
  (31, 'Shilton HCM', '442 Đ. Mạc Thiên Tích', 'Un hôtel de test fameux', 3),
  (32, 'UBU HCM', '453 Đ. Mạc Tích Thiên', 'Un hôtel pas si fameux', 3),
  (33, 'Le Fritz HCM', '667 Đ. Trần Hưng', 'Un hôtel de test cher', 3),
  (34, 'Marine Hotel', '1234 Đ. Trần Hưng Đạo, Phường 10, Quận 5', 'Un hôtel bateau', 3),
  (41, 'Shilton Ba Ria', '440 Đ. Mạc Thiên Tích', 'Un hôtel de test fameux', 4),
  (42, 'UBU Vung Tau', '405 Đ. Mạc Tích Thiên', 'Un hôtel pas trop trop chic', 4),
  (43, 'Le Fritz Ba Ria', '606 Đ. Trần Hưng', 'Un hôtel de test cher', 4),
  (44, 'Le Savoy Bien', '747 Đ. Trần Hưng Đạo', 'Un hôtel de test frais', 4);

INSERT INTO customer (customer_id, firstname, lastname, phone, city_id) 
VALUES 
  (1, 'Igor', 'FRANCK', '+33123456789', 1),
  (2, 'Vy', 'TRAN', '+84987654321', 2), 
  (3, 'Bernardo', 'PLOPO', '+24135792468', 4),
  (4, 'Minh', 'NGUYEN', '+84112233445', 3);

INSERT INTO booking (booking_id, customer_id, hotel_id, begin_date, days, details, guests, price) 
VALUES 
  (101, 1, 31, '2023-09-12', 14, 'No breakfast', 2, 456.78),
  (102, 1, 34, '2024-02-02', 11, 'No detail', 1, 135.79);

