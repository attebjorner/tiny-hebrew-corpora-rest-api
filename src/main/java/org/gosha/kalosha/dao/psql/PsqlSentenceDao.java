package org.gosha.kalosha.dao.psql;

import org.gosha.kalosha.dao.SentenceDao;
import org.gosha.kalosha.model.Sentence;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PsqlSentenceDao implements SentenceDao
{
    private final SessionFactory sessionFactory;

    @Autowired
    public PsqlSentenceDao(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Sentence getById(long id)
    {
        return sessionFactory.getCurrentSession().get(Sentence.class, id);
    }

    @Override
    public long save(Sentence sentence)
    {
        return (long) sessionFactory.getCurrentSession().save(sentence);
    }

    @Override
    public void update(Sentence sentence)
    {
        sessionFactory.getCurrentSession().update(sentence);
    }

    @Override
    public void delete(Sentence sentence)
    {
        sessionFactory.getCurrentSession().delete(sentence);
    }

    @Override
    public List<Sentence> getByQuery(String queryString, int page, int maxResults)
    {
        return sessionFactory.getCurrentSession()
                .createQuery("select distinct s from Sentence s where s.originalSentence like :query", Sentence.class)
                .setParameter("query", "%" + queryString + "%")
                .setFirstResult(page * maxResults)
                .setMaxResults(maxResults)
                .getResultList();
    }

    @Override
    public List<Sentence> getByLemma(String lemma, int page, int maxResults)
    {
        return sessionFactory.getCurrentSession()
                .createQuery("select distinct s from Word w join w.sentences s where w.lemma = :lemma", Sentence.class)
                .setParameter("lemma", lemma)
                .setFirstResult(page * maxResults)
                .setMaxResults(maxResults)
                .getResultList();
    }

    @Override
    public List<Sentence> getByPos(String pos, int page, int maxResults)
    {
        return sessionFactory.getCurrentSession()
                .createQuery("select distinct s from Word w join w.sentences s where w.pos = :pos", Sentence.class)
                .setParameter("pos", pos)
                .setFirstResult(page * maxResults)
                .setMaxResults(maxResults)
                .getResultList();
    }

    @Override
    public List<Sentence> getByGram(Map<String, String> gram, int page, int maxResults)
    {
        var query = buildGramQuery(
                new StringBuilder("select distinct s from Word w join w.sentences s where "), gram
        );
        return query.setFirstResult(page * maxResults)
                .setMaxResults(maxResults)
                .getResultList();
    }

    @Override
    public List<Sentence> getByLemmaPos(String lemma, String pos, int page, int maxResults)
    {
        return sessionFactory.getCurrentSession()
                .createQuery("select distinct s from Word w join w.sentences s where w.lemma = :lemma and w.pos = :pos", Sentence.class)
                .setParameter("lemma", lemma)
                .setParameter("pos", pos)
                .setFirstResult(page * maxResults)
                .setMaxResults(maxResults)
                .getResultList();
    }

    @Override
    public List<Sentence> getByLemmaGram(String lemma, Map<String, String> gram, int page, int maxResults)
    {
        var query = buildGramQuery(
                new StringBuilder("select distinct s from Word w join w.sentences s where w.lemma = :lemma and "),
                gram
        );
        return query.setParameter("lemma", lemma)
                .setFirstResult(page * maxResults)
                .setMaxResults(maxResults)
                .getResultList();
    }

    @Override
    public List<Sentence> getByPosGram(String pos, Map<String, String> gram, int page, int maxResults)
    {
        var query = buildGramQuery(
                new StringBuilder("select distinct s from Word w join w.sentences s where w.pos = :pos and "),
                gram
        );
        return query.setParameter("pos", pos)
                .setFirstResult(page * maxResults)
                .setMaxResults(maxResults)
                .getResultList();
    }

    @Override
    public List<Sentence> getByLemmaPosGram(String lemma, String pos, Map<String, String> gram, int page, int maxResults)
    {
        var query = buildGramQuery(
                new StringBuilder("select distinct s from Word w join w.sentences s where w.lemma = :lemma and w.pos = :pos and "),
                gram
        );
        return query.setParameter("lemma", lemma)
                .setParameter("pos", pos)
                .setFirstResult(page * maxResults)
                .setMaxResults(maxResults)
                .getResultList();
    }

    private Query<Sentence> buildGramQuery(StringBuilder queryString, Map<String, String> gram)
    {
        var properties = new String[gram.size()];
        int i;
        for (i = 0; i < properties.length; ++i)
        {
            properties[i] = "w.gram[:k" + i + "] = :v" + i;
        }
        queryString.append("(").append(String.join(" or ", properties)).append(")");
        var query = sessionFactory.getCurrentSession().createQuery(queryString.toString(), Sentence.class);
        i = 0;
        for (var key : gram.keySet())
        {
            query.setParameter("k" + i, key).setParameter("v" + i++, gram.get(key));
        }
        return query;
    }
}
