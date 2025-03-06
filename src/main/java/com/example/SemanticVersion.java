package com.example;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SemanticVersion implements Comparable<SemanticVersion> {
    private List<Integer> versionParts;
    public SemanticVersion(List<Integer> versionParts) {
        this.versionParts = versionParts;    
    }

    private static boolean validateSemanticVersionRawValue(String rawValue) {
        if(rawValue == null || rawValue.isEmpty()) {
            return false;
        }
        
        for(char c : rawValue.toCharArray()) {
            if(!Character.isDigit(c) && c != '.') {
                return false;
            }
        }

        if(rawValue.startsWith(".") || rawValue.endsWith(".")) {
            return false;
        }

        String[] parts = rawValue.split("\\.");
        for(String part : parts) {
            if(part.isEmpty() || (part.startsWith("0") && part.length() > 1)) {
                return false;
            }
        }

        return true;
    }
    
    public static SemanticVersion parse(String rawValue) {
        if(!validateSemanticVersionRawValue(rawValue)) {
            throw new IllegalArgumentException("Invalid Semantic Version value");
        }

        List<Integer> versionParts = Arrays.stream(rawValue.split("\\."))
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        return new SemanticVersion(versionParts);
    }


    @Override
    public int compareTo(SemanticVersion other) {
        int maxSize = Math.max(this.versionParts.size(), other.versionParts.size());
        for(int i = 0; i < maxSize; i++) {
            int thisPart = (i < this.versionParts.size()) ? this.versionParts.get(i) : 0;
            int otherPart = (i < other.versionParts.size()) ? other.versionParts.get(i) : 0;
            if(thisPart != otherPart) {
                Integer.compare(thisPart, otherPart);
            }
        }
        return 0;
    }
}