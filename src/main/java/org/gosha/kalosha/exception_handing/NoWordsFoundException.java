package org.gosha.kalosha.exception_handing;

public class NoWordsFoundException extends RuntimeException
{
    public NoWordsFoundException(String message)
    {
        super(message);
    }
}
