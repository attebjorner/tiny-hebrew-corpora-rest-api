package org.gosha.kalosha.controllers;

import org.gosha.kalosha.services.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentenceContoller
{
    private SentenceService sentenceService;

    @Autowired
    public SentenceContoller(SentenceService sentenceService)
    {
        this.sentenceService = sentenceService;
    }
}
