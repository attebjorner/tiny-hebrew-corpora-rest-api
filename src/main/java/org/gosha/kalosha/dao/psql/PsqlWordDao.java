package org.gosha.kalosha.dao.psql;

import org.gosha.kalosha.dao.WordDao;
import org.gosha.kalosha.model.Word;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PsqlWordDao implements WordDao
{
    private final SessionFactory sessionFactory;

    @Autowired
    public PsqlWordDao(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Word getById(long id)
    {
        return sessionFactory.getCurrentSession().get(Word.class, id);
    }

    @Override
    public Long getIdByWord(Word word)
    {
        return sessionFactory
                .getCurrentSession()
                .createQuery("select w.id from Word w where w.word = :word and w.lemma = :lemma " +
                        "and w.pos = :pos and w.gram = :gram", Long.class)
                .setParameter("word", word.getWord())
                .setParameter("lemma", word.getLemma())
                .setParameter("pos", word.getPos())
                .setParameter("gram", word.getGram())
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public long save(Word word)
    {
        return (long) sessionFactory.getCurrentSession().save(word);
    }

    @Override
    public void update(Word word)
    {
        sessionFactory.getCurrentSession().update(word);
    }

    @Override
    public void delete(Word word)
    {
        sessionFactory.getCurrentSession().delete(word);
    }
}
