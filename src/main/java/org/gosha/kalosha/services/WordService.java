package org.gosha.kalosha.services;

import org.gosha.kalosha.dao.WordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordService
{
    private final WordDao wordDao;

    @Autowired
    public WordService(WordDao wordDao)
    {
        this.wordDao = wordDao;
    }
}
