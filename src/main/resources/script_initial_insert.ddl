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

INSERT INTO VACANZA
(citta_di_permanenza, id_college, id_famiglia, data_di_partenza, numero_di_settimane_durata, lingua_straniera_studiata,
 utente_inserimento, utente_modifica)
VALUES ('Verona',
        (SELECT ID_COLLEGE FROM COLLEGE WHERE UTENTE_INSERIMENTO = 'automatico'),
        (SELECT ID_FAMIGLIA FROM FAMIGLIA WHERE UTENTE_INSERIMENTO = 'automatico'),
        TO_DATE('01/10/2021', 'dd/MM/yyyy'), 2, 'Inglese', 'automatico', 'automatico')
;