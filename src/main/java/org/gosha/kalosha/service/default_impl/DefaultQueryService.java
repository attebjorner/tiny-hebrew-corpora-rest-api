package org.gosha.kalosha.service.default_impl;

import org.gosha.kalosha.dao.SentenceDao;
import org.gosha.kalosha.exception_handing.NoSentencesFoundException;
import org.gosha.kalosha.model.Sentence;
import org.gosha.kalosha.dto.SentenceDto;
import org.gosha.kalosha.dto.WordDto;
import org.gosha.kalosha.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.gosha.kalosha.model.ParameterType.*;

@Service
public class DefaultQueryService implements QueryService
{
    private final SentenceDao sentenceDao;

    private static final Map<Set<String>, Function<Map<String, Object>, List<Sentence>>> COMPLEX_QUERY_METHODS = new HashMap<>();

    @Autowired
    public DefaultQueryService(SentenceDao sentenceDao)
    {
        this.sentenceDao = sentenceDao;
    }

    @Override
    @Transactional
    public List<SentenceDto> getByParameters(Map<String, Object> query, Integer page, Integer maxResults)
    {
        var queryFunction = COMPLEX_QUERY_METHODS.get(query.keySet());
        if (queryFunction == null)
        {
            throw new IllegalStateException("Wrong query parameters");
        }
        query.put(PAGE, (page == null) ? 0 : page - 1);
        query.put(MAX_RESULTS, (maxResults == null) ? 10 : maxResults);
        List<Sentence> sentences = queryFunction.apply(query);
        if (sentences.isEmpty())
        {
            throw new NoSentencesFoundException("No sentences found");
        }
        return sentences.stream().map(SentenceDto::fromSentence).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<SentenceDto> getBySimpleQuery(String queryString, Integer page, Integer maxResults)
    {
        List<Sentence> sentences = sentenceDao.getByQuery(
                queryString, (page == null) ? 0 : page - 1, (maxResults == null) ? 10 : maxResults
        );
        if (sentences.isEmpty())
        {
            throw new NoSentencesFoundException("No sentences found");
        }
        return sentences.stream().map(SentenceDto::fromSentence).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<WordDto> getWordlist(long id)
    {
        Sentence s = sentenceDao.getById(id);
        if (s == null)
        {
            throw new NoSentencesFoundException("Sentence with id " + id + " does not exist");
        }
        return s.getWordList().stream().map(WordDto::fromWord).collect(Collectors.toList());
    }

    @PostConstruct
    private void fillComplexQueryMethodsMap()
    {
        COMPLEX_QUERY_METHODS.putAll(Map.of(
                Set.of(LEMMA), x -> sentenceDao.getByLemma(
                        (String) x.get(LEMMA), (Integer) x.get(PAGE), (Integer) x.get(MAX_RESULTS)
                ),
                Set.of(POS), x -> sentenceDao.getByPos(
                        (String) x.get(POS), (Integer) x.get(PAGE), (Integer) x.get(MAX_RESULTS)
                ),
                Set.of(GRAM), x -> sentenceDao.getByGram(
                        (Map<String, String>) x.get(GRAM), (Integer) x.get(PAGE), (Integer) x.get(MAX_RESULTS)
                ),
                Set.of(LEMMA, POS), x -> sentenceDao.getByLemmaPos(
                        (String) x.get(LEMMA), (String) x.get(POS),
                        (Integer) x.get(PAGE), (Integer) x.get(MAX_RESULTS)
                ),
                Set.of(LEMMA, GRAM), x -> sentenceDao.getByLemmaGram(
                        (String) x.get(LEMMA), (Map<String, String>) x.get(GRAM),
                        (Integer) x.get(PAGE), (Integer) x.get(MAX_RESULTS)
                ),
                Set.of(POS, GRAM), x -> sentenceDao.getByPosGram(
                        (String) x.get(POS), (Map<String, String>) x.get(GRAM),
                        (Integer) x.get(PAGE), (Integer) x.get(MAX_RESULTS)
                ),
                Set.of(LEMMA, POS, GRAM), x -> sentenceDao.getByLemmaPosGram(
                        (String) x.get(LEMMA), (String) x.get(POS), (Map<String, String>) x.get(GRAM),
                        (Integer) x.get(PAGE), (Integer) x.get(MAX_RESULTS)
                )
        ));
    }
}
