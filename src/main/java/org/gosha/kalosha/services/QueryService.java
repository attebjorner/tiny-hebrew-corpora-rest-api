package org.gosha.kalosha.services;

import org.gosha.kalosha.dto.SentenceDto;
import org.gosha.kalosha.dto.WordDto;

import java.util.List;
import java.util.TreeMap;

public interface QueryService
{
    List<SentenceDto> getByParameters(TreeMap<String, Object> query, int page, int maxResults);

    List<SentenceDto> getBySimpleQuery(String queryString, int page, int maxResults);

    List<WordDto> getWordlist(long id);
}
