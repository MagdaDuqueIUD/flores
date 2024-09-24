package com.florescon.floresdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.florescon.floresdm.model.Flor;
import com.florescon.floresdm.repository.FlorRepository;

@Service
public class FlorService {

    @Autowired
    private FlorRepository florRepository;

    public List<Flor> getAllFlores() {
        return florRepository.findAll();
    }

    public void saveFlor(Flor flor) {
        florRepository.save(flor);
    }
}
