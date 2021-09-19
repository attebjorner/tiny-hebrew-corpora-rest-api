package org.gosha.kalosha.controller;

import org.gosha.kalosha.dto.WordDto;
import org.gosha.kalosha.model.Word;
import org.gosha.kalosha.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Map;

import static org.gosha.kalosha.util.Util.decodeJsonToObject;

@RestController
@RequestMapping("${api_version}" + "/word")
public class WordController
{
    private final WordService wordService;

    @Autowired
    public WordController(WordService wordService)
    {
        this.wordService = wordService;
    }

    @GetMapping("{id}")
    public WordDto getById(@PathVariable long id)
    {
        return wordService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Map<String, Long>> saveWord(@RequestBody Word word)
    {
        return new ResponseEntity<>(Map.of("id", wordService.save(word)), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public void updateWord(@PathVariable long id, @RequestBody Word word)
    {
        wordService.update(id, word);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWord(@PathVariable long id)
    {
        wordService.delete(id);
    }
}
