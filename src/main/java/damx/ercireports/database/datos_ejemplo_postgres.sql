
-- Eliminar datos previos (si se desea reiniciar)
DELETE FROM gastos;
DELETE FROM actividades;
DELETE FROM telefonos;
DELETE FROM pasaportes;
DELETE FROM personas;

-- Insertar personas
INSERT INTO personas (nombre, fecha_nacimiento, email, avatarurl)
VALUES 
('Ana Gómez', '1990-03-25', 'ana.gomez@example.com', 'https://example.com/avatars/ana.png'),
('Luis Torres', '1985-07-14', 'luis.torres@example.com', 'https://example.com/avatars/luis.jpg'),
('Marta Ruiz', '2001-01-09', 'marta.ruiz@example.com', 'https://example.com/avatars/marta.webp');

-- Insertar pasaportes
INSERT INTO pasaportes (numero, fecha_emision, persona_id)
VALUES 
('X1234567', '2015-06-01', 1),
('Y7654321', '2018-04-15', 2),
('Z9876543', '2020-10-10', 3);

-- Insertar teléfonos
INSERT INTO telefonos (numero, tipo, persona_id)
VALUES 
('612345678', 'móvil', 1),
('987654321', 'fijo', 2),
('699998877', 'móvil', 3);

-- Insertar actividades
INSERT INTO actividades (id_persona, descripcion, fecha)
VALUES 
(1, 'Inscripción a curso de inglés', '2024-01-15'),
(1, 'Participación en voluntariado', '2024-02-20'),
(2, 'Asistencia a conferencia de tecnología', '2024-03-12'),
(3, 'Curso de cocina saludable', '2024-03-25');

-- Insertar gastos
INSERT INTO gastos (id_persona, descripcion, fecha, total)
VALUES 
(1, 'Compra de libros', '2024-01-16', 49.99),
(1, 'Transporte urbano', '2024-02-01', 15.50),
(2, 'Inscripción a evento', '2024-03-13', 75.00),
(3, 'Material para clase', '2024-03-26', 32.75);
