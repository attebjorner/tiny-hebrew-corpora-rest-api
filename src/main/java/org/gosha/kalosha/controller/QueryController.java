package org.gosha.kalosha.controller;

import org.gosha.kalosha.dto.SentenceDto;
import org.gosha.kalosha.dto.WordDto;
import org.gosha.kalosha.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.TreeMap;

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

    @GetMapping("simple")
    public List<SentenceDto> makeSimpleQuery(@RequestParam String query,
                                             @RequestParam(required = false) Integer page,
                                             @RequestParam(required = false, name = "max_results") Integer maxResults)
    {
        return queryService.getBySimpleQuery(
                query, (page == null) ? 0 : page - 1, (maxResults == null) ? 10 : maxResults
        );
    }

    @GetMapping("complex")
    public List<SentenceDto> makeComplexQuery(@RequestBody TreeMap<String, Object> query,
                                              @RequestParam(required = false) Integer page,
                                              @RequestParam(required = false, name = "max_results") Integer maxResults)
    {
        return queryService.getByParameters(
                query, (page == null) ? 0 : page - 1, (maxResults == null) ? 10 : maxResults
        );
    }

    @GetMapping("wordlist/{id}")
    public List<WordDto> getWordlist(@PathVariable long id)
    {
        return queryService.getWordlist(id);
    }
}
