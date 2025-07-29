package com.bp2.service;

import java.sql.*;
import java.util.*;

public class ComplexQueryService {
    // Prints the top 3 most stable cryptocurrencies by average analysis accuracy
    public void printTop3Najstabilnije() {
        String sql = "SELECT k.ime_val, ROUND(AVG(a.tac_an),2), ROUND(AVG(s.skor),2), b.ime_blok, CASE WHEN b.tip_blok = 'Private' THEN pb.ime_blok ELSE NULL END, b.tip_blok " +
                "FROM Kriptovaluta k JOIN Blokchain_Mreza b ON k.Blokchain_Mreza_id_blok = b.id_blok " +
                "LEFT JOIN Blokchain_Mreza pb ON b.Blokchain_Mreza_id_blok = pb.id_blok " +
                "JOIN Sentiment s ON s.Kriptovaluta_id_val = k.id_val " +
                "JOIN Analiza_Sentimenta a ON a.Sentiment_id_sent = s.id_sent " +
                "GROUP BY k.ime_val, b.ime_blok, b.tip_blok, pb.ime_blok " +
                "ORDER BY AVG(a.tac_an) DESC FETCH FIRST 3 ROWS ONLY";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            System.out.printf("%-12s %-15s %-20s %-18s %-18s %-10s\n", "Kriptovaluta", "Prosecna tacnost", "Prosecni sentiment skor", "Blockchain mreza", "Parent mreza", "Tip mreze");
            while (rs.next()) {
                System.out.printf("%-12s %-15.2f %-20.2f %-18s %-18s %-10s\n",
                        rs.getString(1), rs.getDouble(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getString(6));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Prints trend of average accuracy per day for each currency (last 7 days, only if at least 2 analyses per day)
    public void printTrendTacnostiPoDanima() {
        String sql = "SELECT k.ime_val, a.dat_an, COUNT(*), ROUND(AVG(a.tac_an),2) " +
                "FROM Kriptovaluta k JOIN Sentiment s ON s.Kriptovaluta_id_val = k.id_val " +
                "JOIN Analiza_Sentimenta a ON a.Sentiment_id_sent = s.id_sent " +
                "WHERE a.dat_an >= (SELECT MAX(dat_an) - 6 FROM Analiza_Sentimenta) " +
                "GROUP BY k.ime_val, a.dat_an HAVING COUNT(*) >= 2 " +
                "ORDER BY a.dat_an DESC, 4 DESC";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            System.out.printf("%-12s %-12s %-14s %-18s\n", "Kriptovaluta", "Datum", "Broj analiza", "Prosecna tacnost");
            while (rs.next()) {
                System.out.printf("%-12s %-12s %-14d %-18.2f\n",
                        rs.getString(1), rs.getDate(2), rs.getInt(3), rs.getDouble(4));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Debug: Print all joined Analiza_Sentimenta, Sentiment, Kriptovaluta for last 7 days
    public void printAnalizaSentimentaDebug() {
        String sql = "SELECT a.id_an, a.tac_an, a.dat_an, a.Sentiment_id_sent, s.Kriptovaluta_id_val, k.ime_val " +
                "FROM Analiza_Sentimenta a " +
                "JOIN Sentiment s ON a.Sentiment_id_sent = s.id_sent " +
                "JOIN Kriptovaluta k ON s.Kriptovaluta_id_val = k.id_val " +
                "WHERE a.dat_an >= (SELECT MAX(dat_an) - 6 FROM Analiza_Sentimenta) " +
                "ORDER BY a.dat_an DESC, k.ime_val";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            System.out.println("DEBUG: Analiza_Sentimenta joined with Sentiment and Kriptovaluta for last 7 days:");
            System.out.printf("%-6s %-8s %-12s %-14s %-14s %-12s\n", "id_an", "tac_an", "dat_an", "sent_id", "val_id", "ime_val");
            while (rs.next()) {
                System.out.printf("%-6d %-8d %-12s %-14d %-14d %-12s\n",
                        rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Prints analysis of source type performance ('P' = Post, 'V' = Vest)
    public void printAnalizaUcinakTipaIzvora() {
        String sql = "SELECT k.ime_val, i.tip_izv, ROUND(AVG(a.tac_an),2), COUNT(DISTINCT a.id_an) " +
                "FROM Kriptovaluta k JOIN Sentiment s ON s.Kriptovaluta_id_val = k.id_val " +
                "JOIN Izvor_Podataka i ON i.id_izv = s.Izvor_Podataka_id_izv " +
                "JOIN Analiza_Sentimenta a ON a.Sentiment_id_sent = s.id_sent " +
                "GROUP BY k.ime_val, i.tip_izv ORDER BY 3 DESC";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            System.out.printf("%-12s %-10s %-24s %-14s\n", "Kriptovaluta", "Tip izvora", "Prosecna tacnost izvora", "Broj analiza");
            while (rs.next()) {
                System.out.printf("%-12s %-10s %-24.2f %-14d\n",
                        rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
    private Connection conn;
    public ComplexQueryService(Connection conn) { this.conn = conn; }

    // Print all sentiments for demo/proof
    public void printAllSentiments() {
        String sql = "SELECT id_sent, vr_sent, skor, Kriptovaluta_id_val, Izvor_Podataka_id_izv FROM Sentiment ORDER BY id_sent";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            System.out.printf("%-8s %-8s %-8s %-16s %-16s\n", "ID", "Vrednost", "Skor", "ID Valute", "ID Izvora");
            while (rs.next()) {
                System.out.printf("%-8d %-8d %-8.2f %-16d %-16d\n",
                        rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getInt(4), rs.getInt(5));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Prosecna tacnost po valuti
    public Map<String, Double> getAvgTacnostPoValuti() throws SQLException {
        Map<String, Double> result = new LinkedHashMap<>();
        String sql = "SELECT k.ime_val, AVG(a.tac_an) AS avg_tacnost FROM Analiza_Sentimenta a JOIN Sentiment s ON a.Sentiment_id_sent = s.id_sent JOIN Kriptovaluta k ON s.Kriptovaluta_id_val = k.id_val GROUP BY k.ime_val";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                result.put(rs.getString(1), rs.getDouble(2));
            }
        }
        return result;
    }

    // Sentiment skorevi za valute i njihove blokčejn mreže
    public List<String> getSentimentSkorevi() throws SQLException {
        List<String> result = new ArrayList<>();
        String sql = "SELECT b.ime_blok, k.ime_val, s.vr_sent, s.skor FROM Sentiment s JOIN Kriptovaluta k ON s.Kriptovaluta_id_val = k.id_val JOIN Blokchain_Mreza b ON k.Blokchain_Mreza_id_blok = b.id_blok ORDER BY s.skor DESC";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                result.add(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getInt(3) + " | " + rs.getDouble(4));
            }
        }
        return result;
    }

    // Transakcija: Dodavanje sentimenta i povezane analize
    public boolean addSentimentAndAnaliza(int idSent, int vrSent, double skor, int idVal, int idIzv, int idAn, int tacAn) {
        String insertSent = "INSERT INTO Sentiment VALUES (?, ?, ?, ?, ?)";
        String insertAn = "INSERT INTO Analiza_Sentimenta VALUES (?, ?, SYSDATE, ?)";
        try {
            conn.setAutoCommit(false);
            try (PreparedStatement ps1 = conn.prepareStatement(insertSent); PreparedStatement ps2 = conn.prepareStatement(insertAn)) {
                ps1.setInt(1, idSent);
                ps1.setInt(2, vrSent);
                ps1.setDouble(3, skor);
                ps1.setInt(4, idVal);
                ps1.setInt(5, idIzv);
                ps1.executeUpdate();
                ps2.setInt(1, idAn);
                ps2.setInt(2, tacAn);
                ps2.setInt(3, idSent);
                ps2.executeUpdate();
                conn.commit();
                return true;
            }
        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            String msg = e.getMessage();
            if (msg != null && msg.contains("unique constraint") && msg.contains("SENTIMENT")) {
                System.out.println("GRESKA: Sentiment sa id_sent=" + idSent + " vec postoji!");
            } else if (msg != null && msg.contains("unique constraint") && msg.contains("ANALIZA_SENTIMENTA")) {
                System.out.println("GRESKA: Analiza_Sentimenta sa id_an=" + idAn + " i sentiment_id_sent=" + idSent + " vec postoji!");
            } else if (msg != null && msg.contains("parent key not found")) {
                if (msg.contains("KRIPTOVALUTA"))
                    System.out.println("GRESKA: Kriptovaluta sa id_val=" + idVal + " ne postoji!");
                if (msg.contains("IZVOR_PODATAKA"))
                    System.out.println("GRESKA: Izvor_Podataka sa id_izv=" + idIzv + " ne postoji!");
            } else {
                System.out.println("GRESKA: " + msg);
            }
            return false;
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }
}
