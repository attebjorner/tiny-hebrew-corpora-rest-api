package org.gosha.kalosha.dao;

import org.gosha.kalosha.models.Word;
import org.springframework.stereotype.Repository;

@Repository
public class PsqlWordDao implements WordDao
{
    @Override
    public Word getById(long id)
    {
        return null;
    }
}
