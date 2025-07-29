package com.bp2.service;

import com.bp2.dao.KriptovalutaDAO;
import com.bp2.dto.KriptovalutaDTO;
import java.util.List;

public class KriptoService {
    private KriptovalutaDAO dao;

    public KriptoService(KriptovalutaDAO dao) {
        this.dao = dao;
    }

    public List<KriptovalutaDTO> prosecneCenePoTipu() throws Exception {
        return dao.getAvgCenaPoTipu();
    }
}
