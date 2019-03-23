package it.colletta.service;

import it.colletta.repository.CorrectionRepository;
import it.colletta.model.Correction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class CorrectionService {

    @Autowired
    private CorrectionRepository correctionRepository;

    // TODO
    public Correction findCorrection(String Id) {
        return new Correction();
    }
}
