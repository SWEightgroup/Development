import it.colletta.repository.CorrectionRepository;

import it.colletta.model.Correction;

public class CorrectionService {

    private CorrectionRepository correctionRepository;

    public CorrectionService(CorrectionRepository correctionRepository) {
        this.correctionRepository = correctionRepository;
    }

    public Correction test(String Id) {
        return new Correction();
    }
}