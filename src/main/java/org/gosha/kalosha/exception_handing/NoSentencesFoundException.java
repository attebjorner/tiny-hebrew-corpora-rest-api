package org.gosha.kalosha.exception_handing;

public class NoSentencesFoundException extends RuntimeException
{
    public NoSentencesFoundException(String message)
    {
        super(message);
    }
}
