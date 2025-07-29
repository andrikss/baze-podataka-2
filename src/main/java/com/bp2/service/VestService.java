package com.bp2.service;

import com.bp2.dao.VestDAO;
import java.util.*;

public class VestService {
    private VestDAO dao;
    public VestService(VestDAO dao) { this.dao = dao; }

    public List<String> getAll() throws Exception {
        return dao.getAll();
    }
}
