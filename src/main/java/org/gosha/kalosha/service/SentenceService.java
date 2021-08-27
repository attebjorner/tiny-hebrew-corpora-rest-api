package org.gosha.kalosha.service;

import org.gosha.kalosha.model.Sentence;

public interface SentenceService
{
    Sentence getById(long id);

    long save(Sentence sentence);

    void update(Sentence sentence);

    void delete(Sentence sentence);
}
