package org.gosha.kalosha.controller;

import org.gosha.kalosha.service.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentenceContoller
{
    private final SentenceService sentenceService;

    @Autowired
    public SentenceContoller(SentenceService sentenceService)
    {
        this.sentenceService = sentenceService;
    }
}
