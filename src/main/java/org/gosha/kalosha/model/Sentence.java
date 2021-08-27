package org.gosha.kalosha.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "sentences")
@Data
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
}
