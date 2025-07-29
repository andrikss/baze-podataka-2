package com.bp2.service;

import com.bp2.dao.ForumPostDAO;
import java.util.*;

public class ForumPostService {
    private ForumPostDAO dao;
    public ForumPostService(ForumPostDAO dao) { this.dao = dao; }

    public List<String> getAll() throws Exception {
        return dao.getAll();
    }
}
