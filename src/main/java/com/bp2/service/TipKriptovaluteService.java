package com.bp2.service;

import com.bp2.dao.TipKriptovaluteDAO;
import java.util.*;

public class TipKriptovaluteService {
    private TipKriptovaluteDAO dao;
    public TipKriptovaluteService(TipKriptovaluteDAO dao) { this.dao = dao; }

    public List<String> getAll() throws Exception {
        return dao.getAll();
    }
}
