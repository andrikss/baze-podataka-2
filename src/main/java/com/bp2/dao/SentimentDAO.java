package com.bp2.dao;

import com.bp2.dto.SentimentDTO;
import java.sql.*;
import java.util.*;

public class SentimentDAO {
    private Connection conn;

    public SentimentDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertSentiment(int id, int vrSent, double skor, int idVal, int idIzv) throws SQLException {
        String insert = "INSERT INTO Sentiment (id_sent, vr_sent, skor, Kriptovaluta_id_val, Izvor_Podataka_id_izv) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setInt(1, id);
        ps.setInt(2, vrSent);
        ps.setDouble(3, skor);
        ps.setInt(4, idVal);
        ps.setInt(5, idIzv);
        ps.executeUpdate();
    }
}
