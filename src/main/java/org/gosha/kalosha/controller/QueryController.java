package org.gosha.kalosha.controller;

import lombok.SneakyThrows;
import org.gosha.kalosha.dto.SentenceDto;
import org.gosha.kalosha.dto.WordDto;
import org.gosha.kalosha.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.gosha.kalosha.util.Util.decodeJsonToObject;

@RestController
@RequestMapping("${api_version}" + "/query")
public class QueryController
{
    private final QueryService queryService;

    @Autowired
    public QueryController(QueryService queryService)
    {
        this.queryService = queryService;
    }

    /**
     * @param query Query string
     * @param page Page's numeration starts from 1, treated as 1 if absent
     * @param maxResults Maximum number of results per page, default value 10
     * @return List of sentences if any is found
     */
    @GetMapping("simple")
    public List<SentenceDto> makeSimpleQuery(@RequestParam String query,
                                             @RequestParam(required = false) Integer page,
                                             @RequestParam(required = false, name = "max_results") Integer maxResults)
    {
        return queryService.getBySimpleQuery(query, page, maxResults);
    }

    /**
     * @param encoded Base64 encoded query map
     * @param page Page's numeration starts from 1, treated as 1 if absent
     * @param maxResults Maximum number of results per page, default value 10
     * @return List of sentences if any is found
     */
    @SneakyThrows
    @GetMapping("complex")
    public List<SentenceDto> makeComplexQuery(@RequestParam String encoded,
                                              @RequestParam(required = false) Integer page,
                                              @RequestParam(required = false, name = "max_results") Integer maxResults)
    {
        Map<String, Object> query = new TreeMap<String, Object>(decodeJsonToObject(encoded, Map.class));
        return queryService.getByParameters(query, page, maxResults);
    }

    /**
     * @param id Sentence's id
     * @return List of words in the order they appear in the sentence
     */
    @GetMapping("wordlist/{id}")
    public List<WordDto> getWordlist(@PathVariable long id)
    {
        return queryService.getWordlist(id);
    }
}
