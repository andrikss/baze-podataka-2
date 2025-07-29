package com.bp2.dao;

import com.bp2.dto.BlokchainMrezaDTO;
import java.sql.*;
import java.util.*;

public class BlokchainMrezaDAO {
    private Connection conn;
    public BlokchainMrezaDAO(Connection conn) { this.conn = conn; }

    public List<BlokchainMrezaDTO> getAll() throws SQLException {
        List<BlokchainMrezaDTO> list = new ArrayList<>();
        String sql = "SELECT id_blok, ime_blok, tip_blok, Blokchain_Mreza_id_blok FROM Blokchain_Mreza";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Integer parentId = null;
                Object parentObj = rs.getObject(4);
                if (parentObj != null) {
                    if (parentObj instanceof java.math.BigDecimal) {
                        parentId = ((java.math.BigDecimal) parentObj).intValue();
                    } else if (parentObj instanceof Number) {
                        parentId = ((Number) parentObj).intValue();
                    }
                }
                list.add(new BlokchainMrezaDTO(
                    rs.getInt(1), rs.getString(2), rs.getString(3), parentId));
            }
        }
        return list;
    }

    // Add more methods as needed for queries
}
