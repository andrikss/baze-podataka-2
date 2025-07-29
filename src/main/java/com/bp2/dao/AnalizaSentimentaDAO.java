package com.bp2.dao;

import java.sql.*;

public class AnalizaSentimentaDAO {
    private Connection conn;
    public AnalizaSentimentaDAO(Connection conn) { this.conn = conn; }

    public void insertAnaliza(int idAn, int tacAn, int sentimentId) throws SQLException {
        String sql = "INSERT INTO Analiza_Sentimenta VALUES (?, ?, SYSDATE, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAn);
            ps.setInt(2, tacAn);
            ps.setInt(3, sentimentId);
            ps.executeUpdate();
        }
    }
}
