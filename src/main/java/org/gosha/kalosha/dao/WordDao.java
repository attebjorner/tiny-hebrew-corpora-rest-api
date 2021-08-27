package org.gosha.kalosha.dao;

import org.gosha.kalosha.model.Word;

public interface WordDao
{
    Word getById(long id);

    long save(Word word);

    void update(Word word);

    void delete(Word word);
}
