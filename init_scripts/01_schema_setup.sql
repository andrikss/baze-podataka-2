ALTER SESSION SET CONTAINER = XEPDB1;

-- Use andrea schema
ALTER SESSION SET CURRENT_SCHEMA = andrea;

CREATE TABLE Blokchain_Mreza (
    id_blok NUMBER PRIMARY KEY,
    ime_blok VARCHAR2(255),
    tip_blok VARCHAR2(255),
    Blokchain_Mreza_id_blok NUMBER REFERENCES Blokchain_Mreza(id_blok)
);

CREATE TABLE Tip_Kriptovalute ( 
     id_tip   NUMBER PRIMARY KEY, 
     naz_tip  VARCHAR2 (255) , 
     opis_tip VARCHAR2 (255) 
);

CREATE TABLE Kriptovaluta (
    id_val NUMBER PRIMARY KEY,
    ime_val VARCHAR2(255),
    cena NUMBER,
    Tip_Kriptovalute_id_tip NUMBER,
    Blokchain_Mreza_id_blok NUMBER REFERENCES Blokchain_Mreza(id_blok)
);

CREATE TABLE Izvor_Podataka (
    id_izv NUMBER PRIMARY KEY,
    dat_izv DATE,
    tip_izv VARCHAR2(255) NOT NULL,
    sadrzaj_izv VARCHAR2(255),
    CONSTRAINT CH_INH_Izvor_Podataka CHECK (tip_izv IN ('Izvor_Podataka', 'P', 'V'))
);

CREATE TABLE Forum_Post (
    id_izv NUMBER PRIMARY KEY REFERENCES Izvor_Podataka(id_izv),
    naslov VARCHAR2(255),
    autor VARCHAR2(255)
);

CREATE TABLE Vest (
    id_izv NUMBER PRIMARY KEY REFERENCES Izvor_Podataka(id_izv),
    naslov VARCHAR2(255)
);

CREATE TABLE Sentiment (
    id_sent NUMBER PRIMARY KEY,
    vr_sent NUMBER,
    skor NUMBER,
    Kriptovaluta_id_val NUMBER REFERENCES Kriptovaluta(id_val),
    Izvor_Podataka_id_izv NUMBER REFERENCES Izvor_Podataka(id_izv)
);

CREATE TABLE Analiza_Sentimenta (
    id_an NUMBER,
    tac_an NUMBER,
    dat_an DATE,
    Sentiment_id_sent NUMBER REFERENCES Sentiment(id_sent),
    PRIMARY KEY (id_an, Sentiment_id_sent)
);


-- Tipovi kriptovaluta
INSERT INTO Tip_Kriptovalute VALUES (1, 'Token', 'Standardni token na mreži');
INSERT INTO Tip_Kriptovalute VALUES (2, 'Coin', 'Osnovna valuta mreže');
INSERT INTO Tip_Kriptovalute VALUES (3, 'Stablecoin', 'Valuta stabilne vrednosti');
INSERT INTO Tip_Kriptovalute VALUES (4, 'NFT', 'Token nezamenjivosti');
INSERT INTO Tip_Kriptovalute VALUES (5, 'Utility', 'Token za usluge');
INSERT INTO Tip_Kriptovalute VALUES (6, 'Governance', 'Token za upravljanje');
INSERT INTO Tip_Kriptovalute VALUES (7, 'Wrapped', 'Token vezan za drugi token');

-- Blokchain mreze
INSERT INTO Blokchain_Mreza VALUES (1, 'Ethereum', 'Public', NULL);
INSERT INTO Blokchain_Mreza VALUES (3, 'Binance Smart Chain', 'Public', NULL);
INSERT INTO Blokchain_Mreza VALUES (4, 'Solana', 'Public', NULL);
INSERT INTO Blokchain_Mreza VALUES (5, 'Avalanche', 'Public', NULL);
INSERT INTO Blokchain_Mreza VALUES (7, 'Cardano', 'Public', NULL);
INSERT INTO Blokchain_Mreza VALUES (2, 'Polygon', 'Private', 1); 
INSERT INTO Blokchain_Mreza VALUES (6, 'Fantom', 'Private', 3);  

INSERT INTO Kriptovaluta VALUES (1, 'ETH', 2000, 1, 1);
INSERT INTO Kriptovaluta VALUES (2, 'MATIC', 1.2, 2, 2);
INSERT INTO Kriptovaluta VALUES (3, 'BNB', 300, 3, 3);
INSERT INTO Kriptovaluta VALUES (4, 'SOL', 35, 4, 4);
INSERT INTO Kriptovaluta VALUES (5, 'AVAX', 15, 5, 5);
INSERT INTO Kriptovaluta VALUES (6, 'FTM', 0.5, 6, 6);
INSERT INTO Kriptovaluta VALUES (7, 'ADA', 0.4, 7, 7);

INSERT INTO Izvor_Podataka VALUES (1, DATE '2025-07-01', 'P', 'Post sadrzaj 1');
INSERT INTO Izvor_Podataka VALUES (2, DATE '2025-07-02', 'V', 'Vest sadrzaj 2');
INSERT INTO Izvor_Podataka VALUES (3, DATE '2025-07-03', 'P', 'Post sadrzaj 3');
INSERT INTO Izvor_Podataka VALUES (4, DATE '2025-07-04', 'V', 'Vest sadrzaj 4');
INSERT INTO Izvor_Podataka VALUES (5, DATE '2025-07-05', 'P', 'Post sadrzaj 5');
INSERT INTO Izvor_Podataka VALUES (6, DATE '2025-07-06', 'V', 'Vest sadrzaj 6');
INSERT INTO Izvor_Podataka VALUES (7, DATE '2025-07-07', 'P', 'Post sadrzaj 7');

INSERT INTO Forum_Post VALUES (1, 'Forum naslov 1', 'Autor1');
INSERT INTO Forum_Post VALUES (3, 'Forum naslov 3', 'Autor2');
INSERT INTO Forum_Post VALUES (5, 'Forum naslov 5', 'Autor3');
INSERT INTO Forum_Post VALUES (7, 'Forum naslov 7', 'Autor4');

INSERT INTO Vest VALUES (2, 'Vest naslov 2');
INSERT INTO Vest VALUES (4, 'Vest naslov 4');
INSERT INTO Vest VALUES (6, 'Vest naslov 6');

INSERT INTO Sentiment VALUES (1, 90, 0.95, 1, 1);
INSERT INTO Sentiment VALUES (2, 80, 0.85, 2, 2);
INSERT INTO Sentiment VALUES (3, 70, 0.75, 3, 3);
INSERT INTO Sentiment VALUES (4, 60, 0.65, 4, 4);
INSERT INTO Sentiment VALUES (5, 50, 0.55, 5, 5);
INSERT INTO Sentiment VALUES (6, 40, 0.45, 6, 6);
INSERT INTO Sentiment VALUES (7, 30, 0.35, 7, 7);


INSERT INTO Sentiment VALUES (8, 85, 0.92, 1, 3);
INSERT INTO Sentiment VALUES (9, 78, 0.82, 2, 2);
INSERT INTO Sentiment VALUES (10, 72, 0.78, 3, 3);
INSERT INTO Sentiment VALUES (11, 65, 0.68, 4, 4);
INSERT INTO Sentiment VALUES (12, 52, 0.58, 5, 4);
INSERT INTO Sentiment VALUES (13, 43, 0.48, 6, 5);
INSERT INTO Sentiment VALUES (14, 33, 0.38, 7, 7);

INSERT INTO Analiza_Sentimenta VALUES (1, 70, DATE '2025-07-10', 1);
INSERT INTO Analiza_Sentimenta VALUES (2, 75, DATE '2025-07-11', 2);
INSERT INTO Analiza_Sentimenta VALUES (3, 65, DATE '2025-07-12', 3);
INSERT INTO Analiza_Sentimenta VALUES (4, 60, DATE '2025-07-13', 4);
INSERT INTO Analiza_Sentimenta VALUES (5, 55, DATE '2025-07-14', 5);
INSERT INTO Analiza_Sentimenta VALUES (6, 50, DATE '2025-07-15', 6);
INSERT INTO Analiza_Sentimenta VALUES (7, 45, DATE '2025-07-16', 7);


INSERT INTO Analiza_Sentimenta VALUES (11, 72, DATE '2025-07-17', 1);
INSERT INTO Analiza_Sentimenta VALUES (12, 74, DATE '2025-07-18', 1);
INSERT INTO Analiza_Sentimenta VALUES (13, 77, DATE '2025-07-19', 2);
INSERT INTO Analiza_Sentimenta VALUES (14, 79, DATE '2025-07-20', 2);
INSERT INTO Analiza_Sentimenta VALUES (15, 67, DATE '2025-07-21', 3);
INSERT INTO Analiza_Sentimenta VALUES (16, 69, DATE '2025-07-22', 3);
INSERT INTO Analiza_Sentimenta VALUES (17, 62, DATE '2025-07-23', 4);
INSERT INTO Analiza_Sentimenta VALUES (18, 64, DATE '2025-07-24', 4);
INSERT INTO Analiza_Sentimenta VALUES (19, 57, DATE '2025-07-25', 5);
INSERT INTO Analiza_Sentimenta VALUES (20, 59, DATE '2025-07-26', 5);
INSERT INTO Analiza_Sentimenta VALUES (21, 52, DATE '2025-07-27', 6);
INSERT INTO Analiza_Sentimenta VALUES (22, 54, DATE '2025-07-28', 6);
INSERT INTO Analiza_Sentimenta VALUES (23, 47, DATE '2025-07-29', 7);
INSERT INTO Analiza_Sentimenta VALUES (24, 49, DATE '2025-07-30', 7);

INSERT INTO Analiza_Sentimenta VALUES (25, 88, DATE '2025-07-31', 8);
INSERT INTO Analiza_Sentimenta VALUES (26, 86, DATE '2025-08-01', 8);
INSERT INTO Analiza_Sentimenta VALUES (27, 80, DATE '2025-08-02', 9);
INSERT INTO Analiza_Sentimenta VALUES (28, 82, DATE '2025-08-03', 9);
INSERT INTO Analiza_Sentimenta VALUES (29, 74, DATE '2025-08-04', 10);
INSERT INTO Analiza_Sentimenta VALUES (30, 76, DATE '2025-08-05', 10);
INSERT INTO Analiza_Sentimenta VALUES (31, 68, DATE '2025-08-06', 11);
INSERT INTO Analiza_Sentimenta VALUES (32, 70, DATE '2025-08-07', 11);
INSERT INTO Analiza_Sentimenta VALUES (33, 59, DATE '2025-08-08', 12);
INSERT INTO Analiza_Sentimenta VALUES (34, 61, DATE '2025-08-09', 12);
-- Add more analyses for AVAX (sentiment 12) on 2025-08-09 and 2025-08-08
INSERT INTO Analiza_Sentimenta VALUES (62, 60, DATE '2025-08-09', 12);
INSERT INTO Analiza_Sentimenta VALUES (63, 58, DATE '2025-08-08', 12);
INSERT INTO Analiza_Sentimenta VALUES (35, 50, DATE '2025-08-10', 13);
INSERT INTO Analiza_Sentimenta VALUES (36, 52, DATE '2025-08-11', 13);
-- Add more analyses for FTM (sentiment 13) on 2025-08-11 and 2025-08-10
INSERT INTO Analiza_Sentimenta VALUES (60, 53, DATE '2025-08-11', 13);
INSERT INTO Analiza_Sentimenta VALUES (61, 51, DATE '2025-08-10', 13);
INSERT INTO Analiza_Sentimenta VALUES (37, 39, DATE '2025-08-12', 14);
INSERT INTO Analiza_Sentimenta VALUES (38, 41, DATE '2025-08-13', 14);
-- Add more analyses for ADA (sentiment 14) on 2025-08-13 and 2025-08-12
INSERT INTO Analiza_Sentimenta VALUES (58, 44, DATE '2025-08-13', 14);
INSERT INTO Analiza_Sentimenta VALUES (59, 42, DATE '2025-08-12', 14);


-- Jednostavan upit: Prosečna tačnost po valuti
SELECT k.ime_val, AVG(a.tac_an) AS avg_tacnost
FROM Analiza_Sentimenta a
JOIN Sentiment s ON a.Sentiment_id_sent = s.id_sent
JOIN Kriptovaluta k ON s.Kriptovaluta_id_val = k.id_val
GROUP BY k.ime_val;

-- 1. TOP 3 najstabilnije kriptovalute po prosečnoj tačnosti analize,
--    uz prikaz prosečnog sentiment skora, naziva blockchain mreže i parent mreže ako postoji

SELECT 
    k.ime_val AS kriptovaluta,
    ROUND(AVG(a.tac_an), 2) AS prosecna_tacnost,
    ROUND(AVG(s.skor), 2) AS prosecni_sentiment_skor,
    b.ime_blok AS blockchain_mreza,
    CASE 
        WHEN b.tip_blok = 'Private' THEN pb.ime_blok
        ELSE NULL
    END AS parent_mreza,
    b.tip_blok AS tip_mreze
FROM 
    Kriptovaluta k
JOIN 
    Blokchain_Mreza b ON k.Blokchain_Mreza_id_blok = b.id_blok
LEFT JOIN 
    Blokchain_Mreza pb ON b.Blokchain_Mreza_id_blok = pb.id_blok
JOIN 
    Sentiment s ON s.Kriptovaluta_id_val = k.id_val
JOIN 
    Analiza_Sentimenta a ON a.Sentiment_id_sent = s.id_sent
GROUP BY 
    k.ime_val, b.ime_blok, b.tip_blok, pb.ime_blok
ORDER BY 
    AVG(a.tac_an) DESC
FETCH FIRST 3 ROWS ONLY;

-- 2. Trend prosečne tačnosti po danima za svaku valutu (7 dana najnovijih),
--    ali samo ako ima bar 2 analize u tom danu

SELECT 
    k.ime_val AS kriptovaluta,
    a.dat_an AS datum,
    COUNT(*) AS broj_analiza,
    ROUND(AVG(a.tac_an), 2) AS prosecna_tacnost
FROM 
    Kriptovaluta k
JOIN 
    Sentiment s ON s.Kriptovaluta_id_val = k.id_val
JOIN 
    Analiza_Sentimenta a ON a.Sentiment_id_sent = s.id_sent
WHERE 
    a.dat_an >= (SELECT MAX(dat_an) - 6 FROM Analiza_Sentimenta)
GROUP BY 
    k.ime_val, a.dat_an
HAVING 
    COUNT(*) >= 2
ORDER BY 
    datum DESC, prosecna_tacnost DESC;


-- 3. Analiza učinka tipa izvora ('P' = Post, 'V' = Vest):
--    Koji tip izvora u proseku daje najtačnije analize po valuti

SELECT 
    k.ime_val AS kriptovaluta,
    i.tip_izv AS tip_izvora,
    ROUND(AVG(a.tac_an), 2) AS prosecna_tacnost_izvora,
    COUNT(DISTINCT a.id_an) AS broj_analiza
FROM 
    Kriptovaluta k
JOIN 
    Sentiment s ON s.Kriptovaluta_id_val = k.id_val
JOIN 
    Izvor_Podataka i ON i.id_izv = s.Izvor_Podataka_id_izv
JOIN 
    Analiza_Sentimenta a ON a.Sentiment_id_sent = s.id_sent
GROUP BY 
    k.ime_val, i.tip_izv
ORDER BY 
    prosecna_tacnost_izvora DESC;


-- TRANSAKCIJA: Dodavanje novog tipa valute, nove valute tog tipa, izvora i sentiment analize

BEGIN
  -- 1. Novi tip kriptovalute
  INSERT INTO Tip_Kriptovalute (id_tip, naz_tip, opis_tip)
  VALUES (99, 'Stablecoin', 'Valuta stabilne vrednosti, vezana za fiat');

  -- 2. Nova valuta tog tipa
  INSERT INTO Kriptovaluta (id_val, ime_val, cena, Tip_Kriptovalute_id_tip, Blokchain_Mreza_id_blok)
  VALUES (99, 'USDT', 1.00, 99, 1);  -- Ethereum mreža

  -- 3. Novi izvor kao post
  INSERT INTO Izvor_Podataka (id_izv, dat_izv, tip_izv, sadrzaj_izv)
  VALUES (99, SYSDATE, 'P', 'Diskusija o stabilnosti USDT tokena');

  -- 4. Novi sentiment
  INSERT INTO Sentiment (id_sent, vr_sent, skor, Kriptovaluta_id_val, Izvor_Podataka_id_izv)
  VALUES (99, 90, 0.90, 99, 99);

  -- 5. Nova analiza sentimenta
  INSERT INTO Analiza_Sentimenta (id_an, tac_an, dat_an, Sentiment_id_sent)
  VALUES (99, 85, SYSDATE, 99);

  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    RAISE;
END;
