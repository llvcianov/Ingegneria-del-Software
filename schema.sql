CREATE TABLE navi (
  nome VARCHAR(100) PRIMARY KEY,
  capienza_passeggeri INT NOT NULL,
  capienza_veicoli INT NOT NULL,
  tipo VARCHAR(50) NOT NULL
);

CREATE TABLE corse (
  codice INT PRIMARY KEY AUTO_INCREMENT,
  ora TIME NOT NULL,
  data DATE NOT NULL,
  prezzo FLOAT NOT NULL,
  porto_partenza VARCHAR(100) NOT NULL,
  porto_arrivo VARCHAR(100) NOT NULL,
  nave VARCHAR(100),
  FOREIGN KEY (nave) REFERENCES navi(nome)
);

CREATE TABLE PRENOTAZIONI (
  codice INT PRIMARY KEY AUTO_INCREMENT,
  tipologia VARCHAR(20) NOT NULL,
  targa VARCHAR(10),
  corsa INT,
  FOREIGN KEY (corsa) REFERENCES corse(codice)
);

INSERT INTO navi (nome, capienza_passeggeri, capienza_veicoli, tipo) VALUES
  ('Jolly Nero', 200, 50, 'TRAGHETTO'),
  ('Freccia del Sud', 150, 0, 'ALISCAFO');

INSERT INTO corse (ora, data, prezzo, porto_partenza, porto_arrivo, nave) VALUES
  ('10:00:00', CURDATE(), 25.50, 'Napoli', 'Palermo', 'Jolly Nero'),
  ('15:30:00', CURDATE(), 40.00, 'Napoli', 'Capri', 'Freccia del Sud');
