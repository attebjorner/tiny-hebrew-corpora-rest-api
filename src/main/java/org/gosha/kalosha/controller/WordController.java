package org.gosha.kalosha.controller;

import org.gosha.kalosha.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api_version}")
public class WordController
{
    private final WordService wordService;

    @Autowired
    public WordController(WordService wordService)
    {
        this.wordService = wordService;
    }
}
