package org.gosha.kalosha.models;

import javax.persistence.*;
import java.util.HashMap;

@Entity
@Table
public class WordForm
{
    @Id
    @GeneratedValue
    private int id;
    private String wordForm;
    private String lemma;
    private String pos;
    @ElementCollection
    private HashMap<String, String> gram;
    private String translation;

    public WordForm() {}

    public WordForm(String wordForm, String lemma, String pos, HashMap<String, String> gram, String translation)
    {
        this.wordForm = wordForm;
        this.lemma = lemma;
        this.pos = pos;
        this.gram = gram;
        this.translation = translation;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getWordForm()
    {
        return wordForm;
    }

    public void setWordForm(String wordForm)
    {
        this.wordForm = wordForm;
    }

    public String getLemma()
    {
        return lemma;
    }

    public void setLemma(String lemma)
    {
        this.lemma = lemma;
    }

    public String getPos()
    {
        return pos;
    }

    public void setPos(String pos)
    {
        this.pos = pos;
    }

    public HashMap<String, String> getGram()
    {
        return gram;
    }

    public void setGram(HashMap<String, String> gram)
    {
        this.gram = gram;
    }

    public String getTranslation()
    {
        return translation;
    }

    public void setTranslation(String translation)
    {
        this.translation = translation;
    }
}
