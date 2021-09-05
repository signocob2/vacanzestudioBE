INSERT INTO FAMIGLIA
(nome_capo_famiglia, cognome_capo_famiglia, recapito_telefonico_capo_famiglia, numero_componenti,
 presenza_animali_domestici, numero_camere_disponibili, numero_bagni, distanza_centro_citta_km, utente_inserimento,
 utente_modifica)
VALUES ('Luigi', 'Grosso', '0457321234', 4, 'N', 3, 2, 1, 'automatico', 'automatico')
;

INSERT INTO COLLEGE
    (nome, indirizzo, utente_inserimento, utente_modifica)
VALUES ('College veronese', 'Verona, Corso Porta Nuova, 15', 'automatico', 'automatico')
;

INSERT INTO ATTIVITA_COLLEGE
(id_college, nome_attivita, descrizione_attivita, utente_inserimento, utente_modifica)
VALUES ((SELECT ID_COLLEGE FROM COLLEGE WHERE UTENTE_INSERIMENTO = 'automatico'),
        'CamminataLunga', 'Andiamo a camminare tutti insieme tanti km', 'automatico', 'automatico')
;

INSERT INTO ATTIVITA_COLLEGE
(id_college, nome_attivita, descrizione_attivita, utente_inserimento, utente_modifica)
VALUES ((SELECT ID_COLLEGE FROM COLLEGE WHERE UTENTE_INSERIMENTO = 'automatico'),
        'CorsaBreve', 'Andiamo a correre per pochi metri', 'automatico', 'automatico')
;

INSERT INTO VACANZA
(id_college, id_famiglia, citta_di_permanenza, data_di_partenza, numero_di_settimane_durata, lingua_straniera_studiata,
 utente_inserimento, utente_modifica)
VALUES ((SELECT ID_COLLEGE FROM COLLEGE WHERE UTENTE_INSERIMENTO = 'automatico'),
        (SELECT ID_FAMIGLIA FROM FAMIGLIA WHERE UTENTE_INSERIMENTO = 'automatico'),
        'Verona',
        TO_DATE('01/10/2021', 'dd/MM/yyyy'), 2, 'Inglese', 'automatico', 'automatico')
;

INSERT INTO GITA
(id_vacanza, destinazione, costo_euro, numero_ore, descrizione, utente_inserimento, utente_modifica)
VALUES ((SELECT ID_VACANZA FROM VACANZA WHERE UTENTE_INSERIMENTO = 'automatico'),
        'Corso Porta Nuova', 75.34, 5, 'Visita monumenti', 'automatico', 'automatico')
;

INSERT INTO GITA
(id_vacanza, destinazione, costo_euro, numero_ore, descrizione, utente_inserimento, utente_modifica)
VALUES ((SELECT ID_VACANZA FROM VACANZA WHERE UTENTE_INSERIMENTO = 'automatico'),
        'Piazza Erbe', 123.50, 7, 'Camminata lunga', 'automatico', 'automatico')
;

INSERT INTO GITA
(id_vacanza, destinazione, costo_euro, numero_ore, descrizione, utente_inserimento, utente_modifica)
VALUES ((SELECT ID_VACANZA FROM VACANZA WHERE UTENTE_INSERIMENTO = 'automatico'),
        'Stadio', 70.00, 3, 'Partita di calcio', 'automatico', 'automatico')
;