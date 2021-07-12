package org.gosha.kalosha.models.dto;

import org.gosha.kalosha.models.Sentence;

public class SentenceDto
{
    private final long id;

    private final String originalSentence;

    public SentenceDto(long id, String originalSentence)
    {
        this.id = id;
        this.originalSentence = originalSentence;
    }

    public static SentenceDto fromSentence(Sentence s)
    {
        return new SentenceDto(s.getId(), s.getOriginalSentence());
    }

    public long getId()
    {
        return id;
    }

    public String getOriginalSentence()
    {
        return originalSentence;
    }
}
