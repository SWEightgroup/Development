package it.colletta.service;

import it.colletta.repository.PhraseRepository;
import it.colletta.model.Phrase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhraseService {

    @Autowired
    private PhraseRepository phraseRepository;

    public void insertPhrase(Phrase phrase) {
        // TODO, controllare che la frase sia stata inserita o meno
        phraseRepository.save(phrase);
    }
}
