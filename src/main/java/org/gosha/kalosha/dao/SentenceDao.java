package org.gosha.kalosha.dao;

import org.gosha.kalosha.model.Sentence;
import org.gosha.kalosha.model.Word;

import java.util.List;
import java.util.Map;

public interface SentenceDao
{
    Sentence getById(long id);

    long save(Sentence sentence);

    void update(Sentence sentence);

    void delete(Sentence sentence);

    List<Sentence> getByQuery(String queryString, int page, int maxResults);

    List<Sentence> getByLemma(String lemma, int page, int maxResults);

    List<Sentence> getByPos(String pos, int page, int maxResults);

    List<Sentence> getByGram(Map<String, String> gram, int page, int maxResults);

    List<Sentence> getByLemmaPos(String lemma, String pos, int page, int maxResults);

    List<Sentence> getByLemmaGram(String lemma, Map<String, String> gram, int page, int maxResults);

    List<Sentence> getByPosGram(String pos, Map<String, String> gram, int page, int maxResults);

    List<Sentence> getByLemmaPosGram(String lemma, String pos, Map<String, String> gram, int page, int maxResults);
}
