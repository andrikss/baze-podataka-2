package com.bp2.dao;

import java.sql.*;
import java.util.*;

public class IzvorPodatakaDAO {
    private Connection conn;
    public IzvorPodatakaDAO(Connection conn) { this.conn = conn; }

    public List<String> getAll() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT id_izv, dat_izv, tip_izv, sadrzaj_izv FROM Izvor_Podataka";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getInt(1) + ": " + rs.getDate(2) + ", " + rs.getString(3) + ", " + rs.getString(4));
            }
        }
        return list;
    }
}
