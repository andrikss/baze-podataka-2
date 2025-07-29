package com.bp2.service;

import com.bp2.dao.SentimentDAO;

public class SentimentService {
    private SentimentDAO dao;

    public SentimentService(SentimentDAO dao) {
        this.dao = dao;
    }

    public void unesiNoviSentiment(int id, int vrSent, double skor, int idVal, int idIzv) throws Exception {
        dao.insertSentiment(id, vrSent, skor, idVal, idIzv);
    }
}
