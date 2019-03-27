package it.colletta.repository.phrase;

import it.colletta.model.PhraseModel;

import java.util.List;

// interface for custom query on phrases collections

public interface PhraseCustomQueryInterface {
    public List<PhraseModel> findAllByAuthor(String  authorId);

}

