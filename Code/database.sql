DROP DATABASE IF EXISTS easylease;
CREATE DATABASE easylease;
USE easylease;

CREATE TABLE users(
    id_user VARCHAR(7) NOT NULL,
    account_type ENUM('Cliente', 'Amministratore', 'Consulente') NOT NULL,
    birth_place VARCHAR(16),
    birth_date DATE,
    kind ENUM('Uomo', 'Donna', 'Altro', 'Preferisco non specificarlo'),
    first_name VARCHAR(16) NOT NULL,
    surname VARCHAR(16) NOT NULL,
    email VARCHAR(30) NOT NULL,
    pwd VARCHAR(40) NOT NULL,
    street VARCHAR(32),
    city VARCHAR(16),
    pc VARCHAR(5),  /*Postal Code*/
    hire_date DATE,
    recovery_email VARCHAR(30),
    PRIMARY KEY (id_user), UNIQUE (email)
);

CREATE TABLE car(
    id_car VARCHAR(7) NOT NULL,
    brand VARCHAR(16) NOT NULL,
    model VARCHAR(16) NOT NULL,
    price FLOAT(8,2) NOT NULL,
    car_type VARCHAR(16) NOT NULL,
    power_supply VARCHAR(16) NOT NULL,  /* Alimentazione*/
    img_path VARCHAR(40) NOT NULL,
    visibility BOOLEAN NOT NULL,
    doors INTEGER NOT NULL,
    transmission VARCHAR(16) NOT NULL,
    avg_consumption FLOAT(8,2) NOT NULL,  /*Consumo Medio*/
    horse_power INTEGER NOT NULL,  /*Abbreviabile con HP, da valutare*/
    emission_class VARCHAR(7) NOT NULL,  /*Classe energetica in EURO (EURO6, EURO5)*/
    co2_emissions INTEGER NOT NULL,
    cc INTEGER NOT NULL,  /*Cilindrata*/
    PRIMARY KEY (id_car)
);

CREATE TABLE estimate(
    id_estimate VARCHAR(7) NOT NULL,
    price FLOAT(8,2),
    id_client VARCHAR(7) NOT NULL,
    id_advisor VARCHAR(7) NOT NULL,
    id_car VARCHAR(7) NOT NULL,
    period INTEGER NOT NULL,
    visibility BOOLEAN NOT NULL,
    state VARCHAR (16) NOT NULL, 
    response_date DATE, /* Data in cui l'advisor invia il preventivo*/
    request_date DATE NOT NULL,
    PRIMARY KEY (id_estimate),
    FOREIGN KEY (id_client) REFERENCES users(id_user) on update cascade on delete cascade,
    FOREIGN KEY (id_advisor) REFERENCES users(id_user) on update cascade on delete cascade,
    FOREIGN KEY (id_car) REFERENCES car(id_car) on update cascade on delete cascade
);

CREATE TABLE optional(
    optional_code VARCHAR(7) NOT NULL,
    optional_type VARCHAR(16) NOT NULL,
    optional_name VARCHAR(64) NOT NULL,
    PRIMARY KEY (optional_code)
);

CREATE TABLE included(
    optional_code VARCHAR(7) NOT NULL,
    id_estimate VARCHAR(7) NOT NULL,
	price FLOAT(8,2),
    FOREIGN KEY (optional_code) REFERENCES optional(optional_code) on update cascade on delete cascade,
    FOREIGN KEY (id_estimate) REFERENCES estimate(id_estimate) on update cascade on delete cascade
);

CREATE TABLE orders(
    id_order VARCHAR(7) NOT NULL,
    id_estimate VARCHAR(7) NOT NULL,
    start_date DATE,
    end_date DATE,
    confirm_date DATE,
    visibility BOOLEAN NOT NULL,
    state VARCHAR(16) NOT NULL,
    creation_date DATE NOT NULL,
    PRIMARY KEY (id_order),
    FOREIGN KEY (id_estimate) REFERENCES estimate(id_estimate) on update cascade on delete cascade
);

/*Inserimento Clienti*/
INSERT INTO users (id_user, account_type, birth_place, birth_date, kind, first_name, surname, email, pwd, street, city, pc, hire_date, recovery_email) VALUES
("CLEE8BD", "Cliente", "Avellino", "1998-09-08", "Uomo", "Mattia", "Caprio", "mattia.caprio@unisa.com", "9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684", "Via Nazionale", "Patierno", "83020", null, null),
/*CLIENTE 1*/
("CLcapNk", "Cliente", "Atripalda", "1994-12-23", "Donna", "Cliente1", " ", "cliente1@gmail.com", "17805d8fd9614019c99b5f1207faad6a44aa49a1", "Via della Spiga", "Milano", "90909", null, null),
("CLBGqLi", "Cliente", "Monteriggioni", "1965-04-15", "Uomo", "Michele", "Iodice", "iodice.michele@gmail.com", "9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684", "Contrada Petrulli 3", "Sestri Levante", "80090", null, null),
/*RIPORTA NEL CODICE*/
("ADJdybc", "Consulente", null, null, null, "Marco", "Greco", "marcoGreco@easylease.com", "9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684", null, null, null, "2020-08-12", null),
 /*CONSULENTE1*/
 ("ADclp0c", "Consulente", null, null, null, "Consulente1", " ", "consulente1@easylease.com", "b50298a499ff1b37681d8890a1443f6f90b3aa3b", null, null, null, "2020-08-12", null),
/*ADVISOR FITTIZIO*/
("ADfake0", "Consulente", null, null, null, "Fake", "Advisor", "fake.advisor@easylease.com", "9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684", null, null, null, "2020-08-12", null),
/*RIPORTARE NEL CODICE!!! - ADMIN1*/
("00CfR8I", "Amministratore", null, null, null, "Luca", "Verdi", "lucaVerdi@easylease.com", "9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684", null, null, null, null, "lucaVerdi09@gmail.com"),
/*ADMIN1*/
("CLGatQk", "Amministratore", null, null, null, "Admin1", " ", "admin1@easylease.com", "6b0949118006b7cbb911dbfb858336bf5f130e31", null, null, null, null, "admin1@gmail.com"),

("CL0MbMy", "Cliente", "Caserta", "1997-05-04", "Uomo", "Mario", "Bianchi", "MarioBianchi97@gmail.com", "55a13634553a9d4a6804f1312aec0cc789413ff9", "Corso Umberto 3", "Caserta", 81050, null, null);
/* Inserimento Auto*/
INSERT INTO car(id_car, brand, model, price, car_type, power_supply, img_path, visibility, doors, transmission, avg_consumption, horse_power, emission_class, co2_emissions, cc) VALUES 
("CAbj0kk", "Peugeot", "3008", 249.00, "SUV", "Diesel", "peugeot_3008.jpg", true, 5, "Automatico", 3.9, 130, "Euro 6", 104, 1499),
("CAA504T", "Peugeot", "208", 189.00, "Berlina", "Benzina", "peugeot_208.jpg", true, 5, "Manuale", 4.1, 75, "Euro 6", 94, 1199),
("CA6fSIJ", "Peugeot", "2008", 269.00, "SUV", "Diesel", "peugeot_2008.jpg", true, 5, "Manuale", 3.6, 100, "Euro 6", 96, 1499),
("CAR2aON", "Peugeot", "308", 309.00, "Station Wagon", "Diesel", "peugeot_308.jpg", true, 5, "Manuale", 3.4, 102, "Euro 6", 91, 1499),
("CA5ezEH", "Peugeot", "5008", 369.00, "SUV", "Diesel", "peugeot_5008.jpg", true, 5, "Automatico", 4.1, 130, "Euro 6", 109, 1499),
("CAT5MQO", "Volvo", "XC40", 519.00, "SUV", "Diesel", "volvo_xc40.jpg", true, 5, "Manuale", 5, 150, "Euro 6", 128, 1969),
("CAurRpN", "Volvo", "V60", 379.00, "Station Wagon", "Diesel", "volvo_v60.jpg", true, 5, "Automatico", 3.8, 150, "Euro 6", 126, 1969),
("CAqpUBN", "Volvo", "XC60", 499.00, "SUV", "Diesel", "volvo_xc60.jpg", true, 5, "Manuale", 5, 150, "Euro 6", 131, 1969),
("CACtCYy", "Volvo", "S90", 659.00, "Berlina", "Diesel", "volvo_s90.jpg", true, 5, "Automatico", 4.8, 150, "Euro 6", 127, 1969),
("CApcoJ8", "Volvo", "XC90", 659.00, "SUV", "Ibrida", "volvo_xc90.jpg", true, 5, "Automatico", 5.8, 235, "Euro 6", 151, 1969),
("CApytHf", "Mercedes", "GLC", 449.00, "SUV", "Diesel", "mercedes_glc.jpg", true, 5, "Automatico", 5.2, 163, "Euro 6", 137, 1951),
("CAxdaNk", "Mercedes", "CLA", 449.00, "berlina", "Diesel", "mercedes_cla.jpg", true, 5, "Automatico", 3.7, 109, "Euro 6", 120, 1461),
("CAJ6kir", "Mercedes", "Classe C ", 509.00, "Station Wagon", "Diesel", "mercedes_classec.jpg", true, 5, "Automatico", 4.3, 116, "Euro 6", 120, 1598),
("CAkFY2T", "Mercedes", "AMG GT", 799.00, "Sport", "Benzina", "mercedes_amggt.jpg", true, 3, "Sequenziale", 7.8, 730, "Euro 6", 292, 3982),
("CAmOpi3", "Mercedes", "Classe E", 650.00, "Cabriolet", "Benzina", "mercedes_classee.jpg", true, 3, "Automatico", 8.7, 367, "Euro 6", 198, 2996),
("CAo3PLy", "Fiat", "500X", 229.00, "Sport", "Benzina", "fiat_500x.jpg", true, 5, "Manuale", 5.4, 120, "Euro 6", 122, 999),
("CAj3y1A", "Fiat", "500L", 269.00, "SUV", "Diesel", "fiat_500l.jpg", true, 5, "Manuale", 6.2, 95, "Euro 6", 140, 1368),
("CAB0xYK", "Fiat", "Tipo", 259.00, "Station Wagon", "Benzina", "fiat_tipo.jpg", true, 5, "Manuale", 18.9, 120, "Euro 6", 98, 1598),
("CA0EUZR", "Fiat", "Spider 124", 359.00, "Cabriolet", "Benzina", "fiat_spider124.jpg", true, 3, "Manuale", 12.4, 140, "Euro 6", 148, 1368),
("CAmTMob", "Fiat", "500", 159.00, "Berlina", "Benzina", "fiat_500.jpg", true, 3, "Manuale", 4.9, 69, "Euro 6", 114, 1242),
("CA6qSDe", "Ford" ,"Puma", 219.00, "SUV", "Ibrida", "ford_puma.jpg", true, 5, "Manuale", 4.2, 125, "Euro 6", 99, 998),
("CAyMCNs", "Ford" ,"Kuga", 319.00, "SUV", "Ibrida", "ford_kuga.jpg", true, 5, "Automatico", 1.0, 225, "Euro 6", 26, 1481),
("CAS1EPG", "Ford" ,"Focus", 229.00, "Berlina", "Benzina", "ford_focus.jpg", true, 5, "Manuale", 4.9, 125, "Euro 6", 111, 999),
("CAaQf4d", "Ford" ,"Mondeo", 399.00, "Station Wagon", "Ibrida", "ford_mondeo.jpg", true, 5, "Automatico", 4.4, 187, "Euro 6", 101, 1999),
("CAvaXV0", "Ford" ,"Fiesta", 195.00, "Sport", "Benzina", "ford_fiesta.jpg", true, 5, "Manuale", 5.9, 200, "Euro 6", 136, 1497),
("CAP6Yh6", "Toyota" ,"RAV4", 359.00, "SUV", "Ibrida", "toyota_rav4.jpg", true, 5, "Automatico", 5.0, 218, "Euro 6", 102, 2487),
("CAjzpir", "Toyota" ,"Corolla", 259.00, "Station Wagon", "Ibrida", "toyota_corolla.jpg", true, 5, "Automatico", 3.4, 122, "Euro 6", 76, 1798),
("CAgd6Gk", "Toyota" ,"C-HR", 259.00, "SUV", "Ibrida", "toyota_chr.jpg", true, 5, "Automatico", 3.0, 122, "Euro 6", 86, 1798),
("CAKtSeo", "Toyota" ,"GT86", 310.00, "Sport", "Benzina", "toyota_gt86.jpg", true, 2, "Manuale", 6.9, 200, "Euro 6", 156, 1998),
("CAbWHtX", "Toyota" ,"GR Supra", 349.00, "Sport", "Benzina", "toyota_grsupra.jpg", true, 2, "Manuale", 8.2, 258, "Euro 6", 172, 3000),
("CAvBk9d", "Audi", "A3", 319.00, "Berlina", "Diesel","audi_a3.jpg", true, 5, "Manuale", 4.4, 116, "Euro 6", 114, 1598),
("CAM6z4C", "Audi", "A5", 459.00, "Berlina", "Diesel","audi_a5.jpg",  true, 5, "Manuale", 4.4, 190, "Euro 6", 114, 1968),
("CAPkO8X", "Audi", "Q2", 329.00, "SUV", "Benzina","audi_q2.jpg", true, 5, "Automatico", 5.9, 118, "Euro 6", 118, 1598),
("CA4eP93", "Audi", "Q3", 409.00, "SUV", "Diesel", "audi_q3.jpg", true, 5, "Automatico", 5.8, 150, "Euro 6", 124, 1968),
("CARdXnW", "Audi", "Q8", 748.00, "SUV",  "Diesel", "audi_q8.jpg", true, 5, "Automatico", 4.4, 170, "Euro 6", 114, 1598),
("CA2jGG2", "Maserati", "Ghibli", 789.00, "Berlina", "Diesel", "maserati_ghibli.jpg", true, 5, "Automatico", 5.9, 250, "Euro 6", 220, 2987),
("CAbpqgV", "Maserati", "Gran Turismo", 809.00, "Berlina", "Diesel", "maserati_granturismo.jpg", true, 5, "Automatico", 5.0, 200, "Euro 6", 200, 2987),
("CAvdPzG", "Maserati", "Levante", 899.00, "SUV", "Diesel", "maserati_levante.jpg", true, 5, "Automatico", 8.2, 275, "Euro 6", 220,  2987),
("CAfM9Mv", "Maserati", "MC20", 319.00, "Sportiva", "Diesel" ,"maserati_mc20.jpg", true, 5, "Automatico", 8.4, 275, "Euro 6", 250, 2987),
("CATnzoK", "Maserati", "Quattro Porte", 319.00, "SUV", "Diesel", "maserati_quattroporte.jpg", true, 5, "Automatico", 7.4, 250, "Euro 6", 220, 2987),
("CAH5612", "Opel", "Astra", 299.00, "Station Wagon", "Diesel", "opel_astra.jpg", true, 5, "Manuale", 3.5, 110, "Euro 6", 113, 1598),
("CA84J01", "Opel", "Grandland X", 249.00, "SUV", "Diesel", "opel_grandlandx.jpg", true, 5, "Manuale", 4.2, 130, "Euro 6", 113, 1499),
("CA5K601", "Opel", "Mokka X", 219.00, "SUV", "GPL", "opel_mokkax.jpg", true, 5, "Manuale", 4.2, 130, "Euro 6", 95, 1598),
("CA18700", "Opel", "Corsa", 189.00, "Berlina", "Benzina", "opel_corsa.jpg", true, 5, "Manuale", 4.1, 75, "Euro 6", 95, 1199),
("CA1550M", "Renault", "Clio", 189.00, "Berlina", "Benzina", "renault_clio.jpg", true, 5, "Manuale", 4.4, 100, "Euro 6", 100, 999),
("CA0T753", "Renault", "Sporter", 309.00, "Sport", "Diesel", "renault_sporter.jpg", true, 5, "Manuale", 4.4, 75, "Euro 6", 113, 1461),
("CA97Q21", "Renault", "Megane", 289.00, "Sport", "Diesel", "renault_megane.jpg", true, 5, "Manuale", 3.9, 95, "Euro 6", 102, 1461),
("CAC0101", "Renault", "Kadjar", 319.00, "SUV", "Diesel", "renault_kadjar.jpg", true, 5, "Manuale", 4.3, 115, "Euro 6", 115, 1461),
("CABKAIF", "Alfa Romeo", "Giulia", 399.00, "Berlina", "Benzina", "alfaromeo_giulia.jpg", true, 5, "Manuale", 4.8, 187, "Euro 6", 127, 2143),
("CAOpsF7", "Jaguar", "F-Pace", 629.00, "SUV", "Diesel", "jaguar_fpace.jpg", true, 5, "Manuale", 3.9, 150, "Euro 6", 122, 1999),
("CAfjak7", "Seat", "Leon", 229.00, "Station Wagon", "Diesel", "seat_leon.jpg", true, 5, "Manuale", 3.8, 115, "Euro 6", 104, 1598),
("CAghial", "BMW", "Serie 3 Touring", 379.00, "Station Wagon", "Ibrida", "bmw_serie3touring.jpg", true, 5, "Automatico", 3.7, 122, "Euro 6", 105, 1995),
("CAmlmf5", "BMW", "X5", 689.00, "SUV", "Diesel", "bmw_x5.jpg", true, 5, "Automatico", 5.4, 231, "Euro 6", 108, 1995),
("CAG67A1", "BMW", "Serie 1", 269.00, "Berlina", "Diesel", "bmw_serie1.jpg", true, 5, "Manuale", 7.0, 140, "Euro 6", 121, 1499),
("CAlLlP0", "Jaguar", "XE", 659.00, "Sport", "Diesel", "jaguar_xe.jpg", true, 5, "Automatico", 6.5, 137, "Euro 6", 141, 1999), 
("CAbasv5", "Volkswagen", "Polo", 189.00, "CityCar", "Diesel", "volkswagen_polo.jpg", true, 5, "Manuale", 8.9, 110, "Euro 6", 104, 1272),
("CAaaaa2", "Volkswagen", "Up!", 179.00, "CityCar","Diesel", "volkswagen_up!.jpg", true, 5, "Manuale", 4.5, 60, "Euro 6", 87, 999);

/*Inserimento Preventivi*/
INSERT INTO estimate (id_estimate, price, id_client, id_advisor, id_car, period, visibility, state, response_date, request_date) VALUES
("EShKs85", 8184.00, "CLEE8BD", "ADJdybc", "CAA504T", 24, false, "Confermato", "2018-12-19", "2018-12-17"),
("ESH6f5E", 9414.00, "CLcapNK", "ADJdybc", "CApytHf", 18, false, "Confermato", "2021-02-10", "2021-02-10"),
("ESBv65r", 12969.00, "CLBGqLi", "ADJdybc", "CApytHf", 24, false, "Confermato", "2021-02-10", "2021-02-10"),
("ESr643K", 7002.00, "CLEE8BD", "ADJdybc", "CA0EUZR", 18, false, "Confermato", "2020-03-03", "2020-03-03"),
("ESbVG67", 25164.00, "CLcapNK", "ADJdybc", "CAmOpi3", 36, false, "Confermato", "2021-02-07", "2021-02-05"),
("ESty6ja", 13416.00, "CLBGqLi", "ADJdybc", "CAJ6kir", 24, false, "Confermato", "2020-07-03", "2020-07-01"),
("ES76tRE", 8424.00, "CLBGqLi", "ADJdybc", "CAmTMob", 36, true, "Richiesto", null, "2021-02-02"),
("ES70OUi", 8100.00, "CLEE8BD", "ADJdybc", "CAP6Yh6", 18, true, "Richiesto", null, "2021-02-02"),
("ES71OUi", 8100.00, "CLEE8BD", "ADfake0", "CA0EUZR", 18, true, "Richiesto", null, "2021-02-02"),
("ESAASd4", 9096.00, "CLcapNK", "ADJdybc", "CA0EUZR", 24, true, "Stipulato", "2021-02-10", "2021-02-10"), 
("EShg76T", 14832.00, "CLBGqLi", "ADJdybc", "CAR2aON", 36, true, "Stipulato", "2021-02-10", "2021-02-09"),
("ESdnA9G", 13644.00, "CLEE8BD", "ADJdybc", "CAvBk9d", 36, true, "Stipulato", "2021-02-10", "2021-02-08"),
("ESgY65R", 6456.00, "CLEE8BD", "ADJdybc", "CAo3PLy", 24, true, "Preso in carico", null, "2021-02-10"),
("EShfna8", 7896.00, "CLEE8BD", "ADJdybc", "CAG67A1", 24, false, "Confermato", "2021-02-10", "2021-02-10"),
/*NON TOCCARE*/
("ESfn9IO", 22644.00, "CLcapNK", "ADJdybc", "CAT5MQO", 36, true, "Preso in carico", null, "2018-12-22"),
("ESjg9I7", 22644.00, "CLcapNK", "ADJdybc", "CAM6z4C", 36, true, "Preso in carico", null, "2020-01-27");

/*Inserimento Optionals*/
INSERT INTO optional (optional_code, optional_type, optional_name) VALUES
("OPUi78M", "Auto", "Vetri brillantinati in madreperla"), 
("OPtYR43", "Auto", "Sedili riscaldati"),
("OPtxeYx", "Auto", "Volante riscaldato"),
("OPOmSgD", "Auto", "Sensori di parcheggio"),
("OPFUbzN", "Auto", "Navigatore satellitare"),
("OPLLJLN", "Auto", "Sedile con schermo"),
("OPbbL0Z", "Auto", "Bluethooth"),
("OPKmC2Q", "Auto", "Avviso del cambio di corsia"),
("OPhK6yG", "Auto", "Vernice metallizzata"),
("OPOfa34", "Auto", "Cerchi in lega"),

("OPhbN65", "Contratto", "Assistenza stradale h24"),
("OPJ0Z1i", "Contratto", "Assicurazione e bollo incluso"),
("OPYxG9z", "Contratto", "Cambio pneumatici"),
("OPAwT0p", "Contratto", "Tagliando incluso"),
("OPKtQmj", "Contratto", "Auto sostitutiva in caso di problemi"),
("OPYHG7v", "Contratto", "Assicurazione furto");

INSERT INTO included (optional_code, id_estimate, price) VALUES
("OPUi78M", "ESH6f5E", 0),
("OPhbN65", "ESH6f5E", 0),
("OPtYR43", "EShKs85", 0),
("OPYHG7v", "EShKs85", 0),
("OPUi78M", "ESdnA9G", 0),
("OPhbN65", "ESdnA9G", 0),
("OPUi78M", "ES76tRE", 0),
("OPhbN65", "ES76tRE", 0),
("OPUi78M", "ES70OUi", 0),
("OPhbN65", "ES70OUi", 0),
("OPUi78M", "ESAASd4", 0),
("OPhbN65", "ESAASd4", 0),
("OPUi78M", "EShg76T", 0),
("OPhbN65", "EShg76T", 0),
("OPUi78M", "ESdnA9G", 0),
("OPhbN65", "ESdnA9G", 0),
("OPUi78M", "ESgY65R", 0),
("OPhbN65", "ESgY65R", 0),
("OPUi78M", "ESfn9IO", 0),
("OPUi78M", "ESjg9I7", 0),
("OPYHG7v", "ESjg9I7", 0),
("OPhbN65", "ESfn9IO", 0);

INSERT INTO orders (id_order, id_estimate, start_date, end_date, confirm_date, visibility, state, creation_date) VALUES

("OR1ER4T", "EShKs85", "2018-12-29", "2020-12-29", "2021-02-03", false, "Completato", "2018-12-19"),
("ORlk7Bn", "ESH6f5E", null, null, null, true, "Attesa", "2021-02-10"),
("ORbG567", "ESBv65r", null, null, "2021-02-10", true, "Confermato", "2021-02-10"),
("ORnj86K", "ESR643K", "2021-03-12", "2022-09-12", "2021-03-05", true, "Convalidato", "2020-03-03"),
("ORJ827t", "ESty6ja", null, null, "2020-07-04", false, "Non convalidato", "2020-07-03"),
("ORhfga2", "EShfna8", null, null, null, true, "Attesa", "2021-02-10"),
("ORd3Jks", "ESbVG67", null, null, "2021-02-08", true, "Pagato", "2021-02-08");

