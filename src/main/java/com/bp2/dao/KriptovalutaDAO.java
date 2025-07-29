package com.bp2.dao;

import com.bp2.dto.KriptovalutaDTO;
import java.sql.*;
import java.util.*;

public class KriptovalutaDAO {
    private Connection conn;

    public KriptovalutaDAO(Connection conn) {
        this.conn = conn;
    }

    public List<KriptovalutaDTO> getAvgCenaPoTipu() throws SQLException {
        List<KriptovalutaDTO> list = new ArrayList<>();
        String query = "SELECT t.naz_tip, AVG(k.cena) AS prosecna_cena " +
                       "FROM Kriptovaluta k " +
                       "JOIN Tip_Kriptovalute t ON k.Tip_Kriptovalute_id_tip = t.id_tip " +
                       "GROUP BY t.naz_tip";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            list.add(new KriptovalutaDTO(0, rs.getString("naz_tip"), rs.getDouble("prosecna_cena")));
        }
        return list;
    }
}
