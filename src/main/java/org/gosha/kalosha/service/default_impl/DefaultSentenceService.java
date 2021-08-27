package org.gosha.kalosha.service.default_impl;

import org.gosha.kalosha.dao.SentenceDao;
import org.gosha.kalosha.model.Sentence;
import org.gosha.kalosha.service.SentenceService;
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

    @Override
    public Sentence getById(long id)
    {
        return sentenceDao.getById(id);
    }

    @Override
    public long save(Sentence sentence)
    {
        return sentenceDao.save(sentence);
    }

    @Override
    public void update(Sentence sentence)
    {
        sentenceDao.update(sentence);
    }

    @Override
    public void delete(Sentence sentence)
    {
        sentenceDao.delete(sentence);
    }
}
