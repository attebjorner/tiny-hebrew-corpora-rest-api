package org.gosha.kalosha.dto;

import org.gosha.kalosha.models.Word;

import java.util.Map;

public class WordDto
{
    private final String word;

    private final String lemma;

    private final String pos;

    private final Map<String, String> gram;

    public WordDto(String word, String lemma, String pos, Map<String, String> gram)
    {
        this.word = word;
        this.lemma = lemma;
        this.pos = pos;
        this.gram = gram;
    }

    public static WordDto fromWord(Word w)
    {
        return new WordDto(w.getWord(), w.getLemma(), w.getPos(), w.getGram());
    }

    public String getWord()
    {
        return word;
    }

    public String getLemma()
    {
        return lemma;
    }

    public String getPos()
    {
        return pos;
    }

    public Map<String, String> getGram()
    {
        return gram;
    }
}
