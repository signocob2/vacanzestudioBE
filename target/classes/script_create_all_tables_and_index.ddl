--STUDENTE
CREATE TABLE STUDENTE
(
    id_studente           NUMBER GENERATED BY DEFAULT AS IDENTITY,
    nome                  VARCHAR2(30)                        NOT NULL,
    cognome               VARCHAR2(30)                        NOT NULL,
    data_di_nascita       DATE                                NOT NULL,
    comune_di_nascita     VARCHAR2(30)                        NOT NULL,
    provincia_di_nascita  VARCHAR2(30)                        NOT NULL,
    codice_fiscale        VARCHAR2(16)                        NOT NULL,
    indirizzo             VARCHAR2(150)                       NOT NULL,
    recapito_telefonico   VARCHAR2(20),
    email                 VARCHAR2(50)                        NOT NULL,
    password              VARCHAR2(30)                        NOT NULL,
    timestamp_inserimento TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    utente_inserimento    VARCHAR2(50),
    timestamp_modifica    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    utente_modifica       VARCHAR2(50),
    PRIMARY KEY (id_studente)
);

CREATE UNIQUE INDEX
    STUDENTE_UNQ_IDX1
    ON
        STUDENTE (email);

CREATE UNIQUE INDEX
    STUDENTE_UNQ_IDX2
    ON
        STUDENTE (codice_fiscale);

---------------------------------------------------------------------
--GENITORE
CREATE TABLE GENITORE
(
    id_genitore           NUMBER GENERATED BY DEFAULT AS IDENTITY,
    id_studente           NUMBER                              NOT NULL,
    nome                  VARCHAR2(30)                        NOT NULL,
    cognome               VARCHAR2(30)                        NOT NULL,
    recapito_telefonico   VARCHAR2(20)                        NOT NULL,
    email                 VARCHAR2(50)                        NOT NULL,
    timestamp_inserimento TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    utente_inserimento    VARCHAR2(50),
    timestamp_modifica    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    utente_modifica       VARCHAR2(50),
    PRIMARY KEY (id_genitore)
);

CREATE UNIQUE INDEX
    GENITORE_UNQ_IDX1
    ON
        GENITORE (email);

---------------------------------------------------------------------
--HOBBY
CREATE TABLE HOBBY
(
    id_hobby              NUMBER GENERATED BY DEFAULT AS IDENTITY,
    id_studente           NUMBER                              NOT NULL,
    nome                  VARCHAR2(50)                        NOT NULL,
    timestamp_inserimento TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    utente_inserimento    VARCHAR2(50),
    timestamp_modifica    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    utente_modifica       VARCHAR2(50),
    PRIMARY KEY (id_hobby)
);

CREATE UNIQUE INDEX
    HOBBY_UNQ_IDX1
    ON
        HOBBY (id_studente, nome);

---------------------------------------------------------------------
--ALLERGIA
CREATE TABLE ALLERGIA
(
    id_allergia                        NUMBER GENERATED BY DEFAULT AS IDENTITY,
    id_studente                        NUMBER                              NOT NULL,
    nome_allergene                     VARCHAR2(50)                        NOT NULL,
    descrizione_precauzioni_necessarie VARCHAR2(300)                       NOT NULL,
    timestamp_inserimento              TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    utente_inserimento                 VARCHAR2(50),
    timestamp_modifica                 TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    utente_modifica                    VARCHAR2(50),
    PRIMARY KEY (id_allergia)
);

CREATE UNIQUE INDEX
    ALLERGIA_UNQ_IDX1
    ON
        ALLERGIA (id_studente, nome_allergene);

---------------------------------------------------------------------
--VACANZA
CREATE TABLE VACANZA
(
    id_vacanza                 NUMBER GENERATED BY DEFAULT AS IDENTITY,
    id_college                 NUMBER                              NOT NULL,
    id_famiglia                NUMBER                              NOT NULL,
    citta_di_permanenza        VARCHAR2(30)                        NOT NULL,
    data_di_partenza           DATE                                NOT NULL,
    numero_di_settimane_durata NUMBER(4)                           NOT NULL,
    lingua_straniera_studiata  VARCHAR2(30)                        NOT NULL,
    timestamp_inserimento      TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    utente_inserimento         VARCHAR2(50),
    timestamp_modifica         TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    utente_modifica            VARCHAR2(50),
    PRIMARY KEY (id_vacanza)
);

---------------------------------------------------------------------
--GITA
CREATE TABLE GITA
(
    id_gita               NUMBER GENERATED BY DEFAULT AS IDENTITY,
    id_vacanza            NUMBER                              NOT NULL,
    destinazione          VARCHAR2(50)                        NOT NULL,
    costo_euro            NUMBER                              NOT NULL,
    numero_ore            NUMBER(3)                           NOT NULL,
    descrizione           VARCHAR2(300)                       NOT NULL,
    timestamp_inserimento TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    utente_inserimento    VARCHAR2(50),
    timestamp_modifica    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    utente_modifica       VARCHAR2(50),
    PRIMARY KEY (id_gita)
);

CREATE UNIQUE INDEX
    GITA_UNQ_IDX1
    ON
        GITA (id_vacanza, destinazione);

---------------------------------------------------------------------
--PRENOTAZIONE_VACANZA_COLLEGE
CREATE TABLE PRENOTAZIONE_VACANZA_COLLEGE
(
    id_prenotazione_vacanza_college NUMBER GENERATED BY DEFAULT AS IDENTITY,
    id_vacanza                      NUMBER                                NOT NULL,
    id_studente                     NUMBER                                NOT NULL,
    singola_condivisa               VARCHAR2(1) DEFAULT 'S'               NOT NULL,
    CONSTRAINT VC_FLAG_CHK_SING_COND CHECK (SINGOLA_CONDIVISA IN ('S', 'C')),
    timestamp_inserimento           TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    utente_inserimento              VARCHAR2(50),
    timestamp_modifica              TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    utente_modifica                 VARCHAR2(50),
    PRIMARY KEY (id_prenotazione_vacanza_college)
);

---------------------------------------------------------------------
--COLLEGE
CREATE TABLE COLLEGE
(
    id_college            NUMBER GENERATED BY DEFAULT AS IDENTITY,
    nome                  VARCHAR2(100)                       NOT NULL,
    indirizzo             VARCHAR2(50)                        NOT NULL,
    timestamp_inserimento TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    utente_inserimento    VARCHAR2(50),
    timestamp_modifica    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    utente_modifica       VARCHAR2(50),
    PRIMARY KEY (id_college)
);

CREATE UNIQUE INDEX
    COLLEGE_UNQ_IDX1
    ON
        COLLEGE (indirizzo);

---------------------------------------------------------------------
--ATTIVITA_COLLEGE
CREATE TABLE ATTIVITA_COLLEGE
(
    id_attivita_college   NUMBER GENERATED BY DEFAULT AS IDENTITY,
    id_college            NUMBER                              NOT NULL,
    nome_attivita         VARCHAR2(50)                        NOT NULL,
    descrizione_attivita  VARCHAR2(50)                        NOT NULL,
    timestamp_inserimento TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    utente_inserimento    VARCHAR2(50),
    timestamp_modifica    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    utente_modifica       VARCHAR2(50),
    PRIMARY KEY (id_attivita_college)
);

CREATE UNIQUE INDEX
    ATTIVITA_COLLEGE_UNQ_IDX1
    ON
        ATTIVITA_COLLEGE (id_college, nome_attivita);

---------------------------------------------------------------------
--PRENOTAZIONE_VACANZA_FAMIGLIA
CREATE TABLE PRENOTAZIONE_VACANZA_FAMIGLIA
(
    id_prenotazione_vacanza_famiglia NUMBER GENERATED BY DEFAULT AS IDENTITY,
    id_vacanza                       NUMBER                              NOT NULL,
    id_studente                      NUMBER                              NOT NULL,
    nome_amico                       VARCHAR2(30)                        NOT NULL,
    cognome_amico                    VARCHAR2(30)                        NOT NULL,
    email_amico                      VARCHAR2(50)                        NOT NULL,
    timestamp_inserimento            TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    utente_inserimento               VARCHAR2(50),
    timestamp_modifica               TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    utente_modifica                  VARCHAR2(50),
    PRIMARY KEY (id_prenotazione_vacanza_famiglia)
);

CREATE UNIQUE INDEX
    PRENOTAZIONE_VACANZA_FAMIGLIA_UNQ_IDX1
    ON
        PRENOTAZIONE_VACANZA_FAMIGLIA (id_vacanza, email_amico);

---------------------------------------------------------------------
--FAMIGLIA
CREATE TABLE FAMIGLIA
(
    id_famiglia                       NUMBER GENERATED BY DEFAULT AS IDENTITY,
    nome_capo_famiglia                VARCHAR2(30)                          NOT NULL,
    cognome_capo_famiglia             VARCHAR2(30)                          NOT NULL,
    recapito_telefonico_capo_famiglia VARCHAR2(20)                          NOT NULL,
    numero_componenti                 NUMBER(2)                             NOT NULL,
    presenza_animali_domestici        VARCHAR2(1) DEFAULT 'N'               NOT NULL,
    CONSTRAINT VF_FLAG_CHK_ANIM_DOM CHECK (PRESENZA_ANIMALI_DOMESTICI IN ('N', 'S')),
    numero_camere_disponibili         NUMBER(2)                             NOT NULL,
    numero_bagni                      NUMBER(2)                             NOT NULL,
    distanza_centro_citta_km          NUMBER(3, 2)                          NOT NULL,
    timestamp_inserimento             TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    utente_inserimento                VARCHAR2(50),
    timestamp_modifica                TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    utente_modifica                   VARCHAR2(50),
    PRIMARY KEY (id_famiglia)
);