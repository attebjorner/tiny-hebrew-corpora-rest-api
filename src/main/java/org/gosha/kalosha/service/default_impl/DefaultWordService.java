package org.gosha.kalosha.service.default_impl;

import org.gosha.kalosha.dao.WordDao;
import org.gosha.kalosha.dto.WordDto;
import org.gosha.kalosha.exception_handing.NoWordsFoundException;
import org.gosha.kalosha.model.Word;
import org.gosha.kalosha.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultWordService implements WordService
{
    private final WordDao wordDao;

    @Autowired
    public DefaultWordService(WordDao wordDao)
    {
        this.wordDao = wordDao;
    }

    @Override
    @Transactional
    public WordDto getById(long id)
    {
        return WordDto.fromWord(wordDao.getById(id));
    }

    @Override
    @Transactional
    public long getIdByWord(Word word)
    {
        Long id = wordDao.getIdByWord(word);
        if (id == null)
        {
            throw new NoWordsFoundException("No word with such parameters found");
        }
        return id;
    }

    @Override
    @Transactional
    public long save(Word word)
    {
        return wordDao.save(word);
    }

    @Override
    @Transactional
    public void update(Word word)
    {
        wordDao.update(word);
    }

    @Override
    @Transactional
    public void delete(Word word)
    {
        wordDao.delete(word);
    }
}
