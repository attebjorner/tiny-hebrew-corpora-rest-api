package org.gosha.kalosha.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sentences")
public class Sentence
{
    @Id
    @SequenceGenerator(name = "sentence_sequence", sequenceName = "sentence_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sentence_sequence")
    private long id;

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST,
                    CascadeType.DETACH, CascadeType.REFRESH}
    )
    @JoinTable(
            name = "sentences_wordforms",
            joinColumns = @JoinColumn(name = "sentence_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "wordform_id", nullable = false)
    )
    @OrderColumn
    private List<Word> wordList;

    @Column(name = "original_sentence")
    private String originalSentence;

    private String translation;

    @Enumerated
    @Column(columnDefinition = "int")
    private LanguageType lang;

    public Sentence()
    {
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public List<Word> getWordList()
    {
        return wordList;
    }

    public void setWordList(List<Word> wordList)
    {
        this.wordList = wordList;
    }

    public String getOriginalSentence()
    {
        return originalSentence;
    }

    public void setOriginalSentence(String originalSentence)
    {
        this.originalSentence = originalSentence;
    }

    public String getTranslation()
    {
        return translation;
    }

    public void setTranslation(String translation)
    {
        this.translation = translation;
    }

    public LanguageType getLang()
    {
        return lang;
    }

    public void setLang(LanguageType lang)
    {
        this.lang = lang;
    }
}
