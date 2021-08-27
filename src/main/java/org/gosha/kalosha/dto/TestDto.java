package org.gosha.kalosha.dto;

import lombok.Data;

import java.util.Map;

@Data
public class TestDto
{
    private String word;

    private String lemma;

    private String pos;

    private Map<String, String> gram;
}
