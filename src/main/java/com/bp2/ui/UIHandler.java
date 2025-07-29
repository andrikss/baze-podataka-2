package com.bp2.ui;

import com.bp2.service.KriptoService;
import com.bp2.service.SentimentService;
import com.bp2.service.ComplexQueryService;
import com.bp2.service.BlokchainMrezaService;
import com.bp2.service.IzvorPodatakaService;
import com.bp2.service.ForumPostService;
import com.bp2.service.VestService;
import com.bp2.service.TipKriptovaluteService;
import com.bp2.dao.KriptovalutaDAO;
import com.bp2.dao.SentimentDAO;
import com.bp2.dao.BlokchainMrezaDAO;
import com.bp2.dao.IzvorPodatakaDAO;
import com.bp2.dao.ForumPostDAO;
import com.bp2.dao.VestDAO;
import com.bp2.dao.TipKriptovaluteDAO;
import com.bp2.dto.KriptovalutaDTO;

import java.sql.*;
import java.util.*;

public class UIHandler {

    public void start() {
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522/XEPDB1", "andrea", "andrea")) {
            KriptoService kriptoService = new KriptoService(new KriptovalutaDAO(conn));
            SentimentService sentimentService = new SentimentService(new SentimentDAO(conn));
            ComplexQueryService complexService = new ComplexQueryService(conn);
            BlokchainMrezaService blokService = new BlokchainMrezaService(new com.bp2.dao.BlokchainMrezaDAO(conn));
            IzvorPodatakaService izvorService = new IzvorPodatakaService(new com.bp2.dao.IzvorPodatakaDAO(conn));
            ForumPostService forumService = new ForumPostService(new com.bp2.dao.ForumPostDAO(conn));
            VestService vestService = new VestService(new com.bp2.dao.VestDAO(conn));
            TipKriptovaluteService tipService = new TipKriptovaluteService(new com.bp2.dao.TipKriptovaluteDAO(conn));

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\n=== MENI ===");
                System.out.println("1. TOP 3 najstabilnije kriptovalute po prosecnoj tacnosti analize, uz prikaz prosecnog sentiment skora, naziva blockchain mreze i parent mreze ako postoji");
                System.out.println("2. Trend prosecne tacnosti po danima za svaku valutu (7 dana najnovijih), ali samo ako ima bar 2 analize u tom danu");
                System.out.println("3. Analiza ucinka tipa izvora ('P' = Post, 'V' = Vest): Koji tip izvora u proseku daje najtacnije analize po valuti");
                System.out.println("4. Dodaj sentiment i analizu (transakcija)");
                System.out.println("5. Prosecna cena po tipu kriptovalute");
                System.out.println("6. Prosecna tacnost po valuti");
                System.out.println("7. Sentiment skorevi za valute i blockchain mreze");
                System.out.println("8. Prikazi sve blockchain mreze");
                System.out.println("9. Prikazi sve izvore podataka");
                System.out.println("10. Prikazi sve forum postove");
                System.out.println("11. Prikazi sve vesti");
                System.out.println("12. Prikazi sve tipove kriptovaluta");
                System.out.println("0. Izlaz");

                int izbor = scanner.nextInt();
                scanner.nextLine();
                switch (izbor) {
                    case 1:
                        System.out.println("-- 1. TOP 3 najstabilnije kriptovalute po prosecnoj tacnosti analize,\n--    uz prikaz prosecnog sentiment skora, naziva blockchain mreze i parent mreze ako postoji");
                        complexService.printTop3Najstabilnije();
                        break;
                    case 2:
                        System.out.println("-- 2. Trend prosecne tacnosti po danima za svaku valutu (7 dana najnovijih), ali samo ako ima bar 2 analize u tom danu");
                        complexService.printTrendTacnostiPoDanima();
                        break;
                    case 3:
                        System.out.println("-- 3. Analiza ucinka tipa izvora ('P' = Post, 'V' = Vest): Koji tip izvora u proseku daje najtacnije analize po valuti");
                        complexService.printAnalizaUcinakTipaIzvora();
                        break;
                    case 4:
                        System.out.println("-- 4. Dodaj sentiment i analizu (transakcija)");
                        System.out.print("ID sentimenta: ");
                        int idSent = scanner.nextInt();
                        System.out.print("Vrednost: ");
                        int vrSent = scanner.nextInt();
                        System.out.print("Skor: ");
                        double skorSent = scanner.nextDouble();
                        System.out.print("ID valute: ");
                        int idVal = scanner.nextInt();
                        System.out.print("ID izvora: ");
                        int idIzv = scanner.nextInt();
                        System.out.print("ID analize: ");
                        int idAn = scanner.nextInt();
                        System.out.print("Tacnost analize: ");
                        int tacAn = scanner.nextInt();
                        boolean ok = complexService.addSentimentAndAnaliza(idSent, vrSent, skorSent, idVal, idIzv, idAn, tacAn);
                        System.out.println(ok ? "Uspesno dodato!" : "Greska u transakciji!");
                        System.out.println("-- Svi sentimenti u bazi (provera transakcije):");
                        complexService.printAllSentiments();
                        break;
                    case 5:
                        System.out.println("-- 5. Prosecna cena po tipu kriptovalute");
                        for (KriptovalutaDTO k : kriptoService.prosecneCenePoTipu()) {
                            System.out.println(k.getNaziv() + " - " + k.getCena() + " USD");
                        }
                        break;
                    case 6:
                        System.out.println("-- 6. Prosecna tacnost po valuti");
                        complexService.getAvgTacnostPoValuti().forEach((ime, avg) ->
                            System.out.println(ime + ": " + avg));
                        break;
                    case 7:
                        System.out.println("-- 7. Sentiment skorevi za valute i blockchain mreze");
                        for (String s : complexService.getSentimentSkorevi()) {
                            System.out.println(s);
                        }
                        break;
                    case 8:
                        System.out.println("-- 8. Prikazi sve blockchain mreze");
                        blokService.getAll().forEach(b ->
                            System.out.println(b.getId() + ": " + b.getIme() + ", tip: " + b.getTip() + ", parent: " + b.getParentId()));
                        break;
                    case 9:
                        System.out.println("-- 9. Prikazi sve izvore podataka");
                        izvorService.getAll().forEach(System.out::println);
                        break;
                    case 10:
                        System.out.println("-- 10. Prikazi sve forum postove");
                        forumService.getAll().forEach(System.out::println);
                        break;
                    case 11:
                        System.out.println("-- 11. Prikazi sve vesti");
                        vestService.getAll().forEach(System.out::println);
                        break;
                    case 12:
                        System.out.println("-- 12. Prikazi sve tipove kriptovaluta");
                        tipService.getAll().forEach(System.out::println);
                        break;
                    case 0:
                        System.out.println("Zatvaranje aplikacije...");
                        return;
                    default:
                        System.out.println("Nepoznata opcija.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
