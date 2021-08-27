package org.gosha.kalosha.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.gosha.kalosha.model.Word;

import java.util.Map;

@RequiredArgsConstructor
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

    public static WordDto fromWord(Word w)
    {
        return new WordDto(w.getWord(), w.getLemma(), w.getPos(), w.getGram());
    }
}
