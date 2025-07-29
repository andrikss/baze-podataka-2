package com.bp2.dao;

import java.sql.*;
import java.util.*;

public class ForumPostDAO {
    private Connection conn;
    public ForumPostDAO(Connection conn) { this.conn = conn; }

    public List<String> getAll() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT id_izv, naslov, autor FROM Forum_Post";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getInt(1) + ": " + rs.getString(2) + ", autor: " + rs.getString(3));
            }
        }
        return list;
    }
}
