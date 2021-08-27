package org.gosha.kalosha.service;

import org.gosha.kalosha.dto.WordDto;
import org.gosha.kalosha.model.Word;

public interface WordService
{
    WordDto getById(long id);

    long getIdByWord(Word word);
}
