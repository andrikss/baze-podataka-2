package com.bp2.dao;

import java.sql.*;
import java.util.*;

public class VestDAO {
    private Connection conn;
    public VestDAO(Connection conn) { this.conn = conn; }

    public List<String> getAll() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT id_izv, naslov FROM Vest";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getInt(1) + ": " + rs.getString(2));
            }
        }
        return list;
    }
}
