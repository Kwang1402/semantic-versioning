package com.example;

import junit.framework.TestCase;

public class SemanticVersionTest extends TestCase {
    public void testParseInvalidRawValue() {
        String[] testCases = {"", null, ".", "1.0.", ".1.0", "1.01", "1..0", "1.a"};
        for(String testCase : testCases) {
            try {
                SemanticVersion.parse(testCase);
                fail("Expected IllegalArgumentException for invalid raw value: " + testCase);
            } catch(IllegalArgumentException e) {
                // Expected
            }
        }
    }

    public void testRepresentation() {
        String[] testCases = {"4.13.3", "5.0", "3.8"};
        for(String testCase : testCases) {
            SemanticVersion semanticVersion = SemanticVersion.parse(testCase);
            assertEquals(testCase, semanticVersion.toString());
        }
    }

    public void testComparison() {
        SemanticVersion semanticVersion1 = SemanticVersion.parse("4.13");
        SemanticVersion semanticVersion2 = SemanticVersion.parse("4.13.0");
        SemanticVersion semanticVersion3 = SemanticVersion.parse("4.13.1");
        SemanticVersion semanticVersion4 = SemanticVersion.parse("4.14");

        // Test equal versions
        assertTrue(semanticVersion1.compareTo(semanticVersion2) == 0);

        // Test less than
        assertTrue(semanticVersion1.compareTo(semanticVersion3) < 0);

        // Test greater than
        assertTrue(semanticVersion4.compareTo(semanticVersion3) > 0);
    }
}
