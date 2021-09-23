------------------------------------------------------------------------------------------------------------------------
-- Primo test vacanza

INSERT INTO FAMIGLIA
(nome_capo_famiglia, cognome_capo_famiglia, recapito_telefonico_capo_famiglia, numero_componenti,
 presenza_animali_domestici, numero_camere_disponibili, numero_bagni, distanza_centro_citta_km, utente_inserimento,
 utente_modifica)
VALUES ('Luigi', 'Grosso', '0457321234', 4, 'N', 3, 2, 1, 'automatico1', 'automatico1')
;

INSERT INTO COLLEGE
    (nome, indirizzo, utente_inserimento, utente_modifica)
VALUES ('College veronese', 'Verona, Corso Porta Nuova, 15', 'automatico1', 'automatico1')
;

INSERT INTO ATTIVITA_COLLEGE
(id_college, nome_attivita, descrizione_attivita, utente_inserimento, utente_modifica)
VALUES ((SELECT ID_COLLEGE FROM COLLEGE WHERE UTENTE_INSERIMENTO = 'automatico1'),
        'Camminata lunga lunga', 'Andiamo a camminare tutti insieme tanti km', 'automatico1', 'automatico1')
;

INSERT INTO ATTIVITA_COLLEGE
(id_college, nome_attivita, descrizione_attivita, utente_inserimento, utente_modifica)
VALUES ((SELECT ID_COLLEGE FROM COLLEGE WHERE UTENTE_INSERIMENTO = 'automatico1'),
        'Corsa breve breve', 'Andiamo a correre per pochi metri', 'automatico1', 'automatico1')
;

INSERT INTO VACANZA
(id_college, id_famiglia, citta_di_permanenza, data_di_partenza, numero_di_settimane_durata, lingua_straniera_studiata,
 utente_inserimento, utente_modifica)
VALUES ((SELECT ID_COLLEGE FROM COLLEGE WHERE UTENTE_INSERIMENTO = 'automatico1'),
        (SELECT ID_FAMIGLIA FROM FAMIGLIA WHERE UTENTE_INSERIMENTO = 'automatico1'),
        'Verona',
        TO_DATE('01/10/2021', 'dd/MM/yyyy'), 2, 'Inglese', 'automatico1', 'automatico1')
;

INSERT INTO GITA
(id_vacanza, destinazione, costo_euro, numero_ore, descrizione, utente_inserimento, utente_modifica)
VALUES ((SELECT ID_VACANZA FROM VACANZA WHERE UTENTE_INSERIMENTO = 'automatico1'),
        'Corso Porta Nuova', 75.34, 5, 'Visita monumenti', 'automatico1', 'automatico1')
;

INSERT INTO GITA
(id_vacanza, destinazione, costo_euro, numero_ore, descrizione, utente_inserimento, utente_modifica)
VALUES ((SELECT ID_VACANZA FROM VACANZA WHERE UTENTE_INSERIMENTO = 'automatico1'),
        'Piazza Erbe', 123.50, 7, 'Camminata lunga', 'automatico1', 'automatico1')
;

INSERT INTO GITA
(id_vacanza, destinazione, costo_euro, numero_ore, descrizione, utente_inserimento, utente_modifica)
VALUES ((SELECT ID_VACANZA FROM VACANZA WHERE UTENTE_INSERIMENTO = 'automatico1'),
        'Stadio', 70.00, 3, 'Partita di calcio', 'automatico1', 'automatico1')
;

------------------------------------------------------------------------------------------------------------------------
-- Secondo test vacanza

INSERT INTO FAMIGLIA
(nome_capo_famiglia, cognome_capo_famiglia, recapito_telefonico_capo_famiglia, numero_componenti,
 presenza_animali_domestici, numero_camere_disponibili, numero_bagni, distanza_centro_citta_km, utente_inserimento,
 utente_modifica)
VALUES ('Renato', 'Signorini', '3491231212', 3, 'S', 2, 1, 3.1, 'automatico2', 'automatico2')
;

INSERT INTO COLLEGE
    (nome, indirizzo, utente_inserimento, utente_modifica)
VALUES ('College erbetano', 'Erbè, Via Aldo Moro, 16', 'automatico2', 'automatico2')
;

INSERT INTO ATTIVITA_COLLEGE
(id_college, nome_attivita, descrizione_attivita, utente_inserimento, utente_modifica)
VALUES ((SELECT ID_COLLEGE FROM COLLEGE WHERE UTENTE_INSERIMENTO = 'automatico2'),
        'Partita di calcio', 'Sfida di calcio, maschi contro femmine', 'automatico2', 'automatico2')
;

INSERT INTO ATTIVITA_COLLEGE
(id_college, nome_attivita, descrizione_attivita, utente_inserimento, utente_modifica)
VALUES ((SELECT ID_COLLEGE FROM COLLEGE WHERE UTENTE_INSERIMENTO = 'automatico2'),
        'Gara in bici', 'Gara con bici sportive, under 20 contro over 20', 'automatico2', 'automatico2')
;

INSERT INTO VACANZA
(id_college, id_famiglia, citta_di_permanenza, data_di_partenza, numero_di_settimane_durata, lingua_straniera_studiata,
 utente_inserimento, utente_modifica)
VALUES ((SELECT ID_COLLEGE FROM COLLEGE WHERE UTENTE_INSERIMENTO = 'automatico2'),
        (SELECT ID_FAMIGLIA FROM FAMIGLIA WHERE UTENTE_INSERIMENTO = 'automatico2'),
        'Erbè',
        TO_DATE('03/04/2022', 'dd/MM/yyyy'), 4, 'Spagnolo', 'automatico2', 'automatico2')
;

INSERT INTO GITA
(id_vacanza, destinazione, costo_euro, numero_ore, descrizione, utente_inserimento, utente_modifica)
VALUES ((SELECT ID_VACANZA FROM VACANZA WHERE UTENTE_INSERIMENTO = 'automatico2'),
        'Parco due tioni', 1.20, 2, 'Passeggiata in mezzo alla natura', 'automatico2', 'automatico2')
;

INSERT INTO GITA
(id_vacanza, destinazione, costo_euro, numero_ore, descrizione, utente_inserimento, utente_modifica)
VALUES ((SELECT ID_VACANZA FROM VACANZA WHERE UTENTE_INSERIMENTO = 'automatico2'),
        'Erbedello', 543.99, 5, 'Messa domenicale e spiegazione storia Erbedello', 'automatico2', 'automatico2')
;
------------------------------------------------------------------------------------------------------------------------
COMMIT;
------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------
--UTENTI
INSERT INTO UTENTE
(NOME, COGNOME, DATA_DI_NASCITA, COMUNE_DI_NASCITA, PROVINCIA_DI_NASCITA, CODICE_FISCALE, INDIRIZZO,
 RECAPITO_TELEFONICO, EMAIL, PASSWORD, IS_ADMIN, UTENTE_INSERIMENTO, UTENTE_MODIFICA)
VALUES ('Mario', 'Rossi', TO_DATE('06/08/1980', 'dd/MM/yyyy'), 'Verona', 'Verona', 'MARRSS80T24E349R',
        'Via Giuseppe Garibaldi, 3', '3401122333', 'rossimario80@gmail.com', 'admin', 'S', 'automatico', 'automatico')
;
COMMIT;