package com.bp2.service;

import com.bp2.dao.IzvorPodatakaDAO;
import java.util.*;

public class IzvorPodatakaService {
    private IzvorPodatakaDAO dao;
    public IzvorPodatakaService(IzvorPodatakaDAO dao) { this.dao = dao; }

    public List<String> getAll() throws Exception {
        return dao.getAll();
    }
}
