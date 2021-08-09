package org.gosha.kalosha.controller;

import org.gosha.kalosha.service.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class SentenceContoller
{
    private final SentenceService sentenceService;

    @Autowired
    public SentenceContoller(SentenceService sentenceService)
    {
        this.sentenceService = sentenceService;
    }
}
