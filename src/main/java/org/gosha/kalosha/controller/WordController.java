package org.gosha.kalosha.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.gosha.kalosha.dto.TestDto;
import org.gosha.kalosha.dto.WordDto;
import org.gosha.kalosha.model.Word;
import org.gosha.kalosha.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Base64;
import java.util.Map;

import static org.gosha.kalosha.util.Util.decodeJsonToObject;

@RestController
@RequestMapping("${api_version}" + "/word")
@RolesAllowed("ADMIN")
public class WordController
{
    private final WordService wordService;

    @Autowired
    public WordController(WordService wordService)
    {
        this.wordService = wordService;
    }

    @RequestMapping("{id}")
    public WordDto getById(@PathVariable long id)
    {
        return wordService.getById(id);
    }

    @RequestMapping("find-id")
    public ResponseEntity<Map<String, Long>> getIdByWord(@RequestParam String query)
    {
//        return ResponseEntity.ok(Map.of("id", wordService.getIdByWord(word)));
        TestDto testDto2 = decodeJsonToObject(query, TestDto.class);
        return ResponseEntity.ok(Map.of("abc", 2L));
    }

    @RequestMapping("save")
    public ResponseEntity<Map<String, Long>> saveWord(@RequestParam(name = "word") String encodedWord)
    {
        Word word = decodeJsonToObject(encodedWord, Word.class);
        return new ResponseEntity<>(Map.of("id", wordService.save(word)), HttpStatus.CREATED);
    }

    public void updateWord(@RequestParam(name = "word") String encodedWord)
    {

    }

    @RequestMapping("delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWord(@RequestParam(name = "word") String encodedWord)
    {
        Word word = decodeJsonToObject(encodedWord, Word.class);
        wordService.delete(word);
    }
}
