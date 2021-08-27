package org.gosha.kalosha.dto;

import lombok.Getter;
import org.gosha.kalosha.model.Word;

import java.util.Map;

public class WordDto
{
    @Getter
    private final String word;

    @Getter
    private final String lemma;

    @Getter
    private final String pos;

    @Getter
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
}
