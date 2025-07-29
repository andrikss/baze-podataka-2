package com.bp2.service;

import com.bp2.dao.BlokchainMrezaDAO;
import com.bp2.dto.BlokchainMrezaDTO;
import java.util.*;

public class BlokchainMrezaService {
    private BlokchainMrezaDAO dao;
    public BlokchainMrezaService(BlokchainMrezaDAO dao) { this.dao = dao; }

    public List<BlokchainMrezaDTO> getAll() throws Exception {
        return dao.getAll();
    }
    // Add more service methods as needed
}
