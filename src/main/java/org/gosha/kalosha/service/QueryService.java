package org.gosha.kalosha.service;

import org.gosha.kalosha.dto.SentenceDto;
import org.gosha.kalosha.dto.WordDto;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface QueryService
{
    List<SentenceDto> getByParameters(Map<String, Object> query, Integer page, Integer maxResults);

    List<SentenceDto> getBySimpleQuery(String queryString, Integer page, Integer maxResults);

    List<WordDto> getWordlist(long id);
}
