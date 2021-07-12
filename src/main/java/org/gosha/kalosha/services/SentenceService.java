package org.gosha.kalosha.services;

import org.gosha.kalosha.dao.SentenceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SentenceService
{
    private SentenceDao sentenceDao;

    @Autowired
    public SentenceService(SentenceDao sentenceDao)
    {
        this.sentenceDao = sentenceDao;
    }
}
