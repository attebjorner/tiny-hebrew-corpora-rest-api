package org.gosha.kalosha.dto;

import lombok.Data;
import lombok.Getter;
import org.gosha.kalosha.model.Word;

import java.util.Map;

@Data
public class WordDto
{
    private String word;

    private String lemma;

    private String pos;

    private Map<String, String> gram;

    public WordDto()
    {
    }

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
