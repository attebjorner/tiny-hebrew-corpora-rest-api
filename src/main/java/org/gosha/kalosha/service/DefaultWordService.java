package org.gosha.kalosha.service;

import org.gosha.kalosha.dao.WordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultWordService implements WordService
{
    private final WordDao wordDao;

    @Autowired
    public DefaultWordService(WordDao wordDao)
    {
        this.wordDao = wordDao;
    }
}