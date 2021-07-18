package org.gosha.kalosha.service;

import org.gosha.kalosha.dao.SentenceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultSentenceService implements SentenceService
{
    private final SentenceDao sentenceDao;

    @Autowired
    public DefaultSentenceService(SentenceDao sentenceDao)
    {
        this.sentenceDao = sentenceDao;
    }
}
