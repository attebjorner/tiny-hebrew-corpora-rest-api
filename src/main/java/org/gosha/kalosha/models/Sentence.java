package org.gosha.kalosha.models;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table
public class Sentence
{
    @Id
    private int id;
    @ManyToMany
    private ArrayList<WordForm> wordlist;
    private String translation;

    public Sentence() {}

    public Sentence(int id, ArrayList<WordForm> wordlist, String translation)
    {
        this.id = id;
        this.wordlist = wordlist;
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

    public ArrayList<WordForm> getWordlist()
    {
        return wordlist;
    }

    public void setWordlist(ArrayList<WordForm> wordlist)
    {
        this.wordlist = wordlist;
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
