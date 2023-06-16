CREATE TABLE IF NOT EXISTS authorities (
id INT NOT NULL AUTO_INCREMENT,
authority VARCHAR(25) NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY (authority)
);

CREATE TABLE IF NOT EXISTS rutina (
id INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(25) NOT NULL,
PRIMARY KEY (id)
);

INSERT IGNORE INTO authorities (id,authority) VALUES
(1,"ROLE_ADMIN"),
(2,"ROLE_CLIENTE");

INSERT IGNORE INTO rutina (id,nombre) VALUES
(1,"Bajar de peso"),
(2,"Subir masa muscular"),
(3, "Estandar");
