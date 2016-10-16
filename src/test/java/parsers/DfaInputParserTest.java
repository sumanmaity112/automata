package parsers;

import finiteAutomata.DFA;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DfaInputParserTest {

    private String jsonString;
    private DfaInputParser dfaInputParser;

    @Before
    public void setUp() throws Exception {
        dfaInputParser = new DfaInputParser();
        jsonString = "{\n" +
                "    \"name\": \"alternate ones and zeroes beginning with zero\",\n" +
                "    \"type\": \"dfa\",\n" +
                "    \"tuple\": {\n" +
                "      \"states\": [\n" +
                "        \"q1\",\n" +
                "        \"q2\",\n" +
                "        \"q3\",\n" +
                "        \"q4\"\n" +
                "      ],\n" +
                "      \"alphabets\": [\n" +
                "        \"0\",\n" +
                "        \"1\"\n" +
                "      ],\n" +
                "      \"delta\": {\n" +
                "        \"q1\": {\n" +
                "          \"0\": \"q2\",\n" +
                "          \"1\": \"q4\"\n" +
                "        },\n" +
                "        \"q2\": {\n" +
                "          \"0\": \"q4\",\n" +
                "          \"1\": \"q3\"\n" +
                "        },\n" +
                "        \"q3\": {\n" +
                "          \"0\": \"q2\",\n" +
                "          \"1\": \"q4\"\n" +
                "        },\n" +
                "        \"q4\": {\n" +
                "          \"0\": \"q4\",\n" +
                "          \"1\": \"q4\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"start-state\": \"q1\",\n" +
                "      \"final-states\": [\n" +
                "        \"q3\",\n" +
                "        \"q2\"\n" +
                "      ]\n" +
                "    },\n" +
                "    \"pass-cases\": [\n" +
                "      \"0\",\n" +
                "      \"01\",\n" +
                "      \"010\",\n" +
                "      \"0101\",\n" +
                "      \"010101\"\n" +
                "    ],\n" +
                "    \"fail-cases\": [\n" +
                "      \"\",\n" +
                "      \"1\",\n" +
                "      \"10\",\n" +
                "      \"101\",\n" +
                "      \"11\",\n" +
                "      \"00\",\n" +
                "      \"0100\",\n" +
                "      \"011\"\n" +
                "    ]\n" +
                "  }";

    }

    @Test
    public void testShouldGenerateNewDfaFromGivenJsonObject() throws Exception {
        DFA dfa = dfaInputParser.generateDfa(new JSONObject(jsonString));
        assertTrue(dfa.getClass().getName().equals("finiteAutomata.DFA"));

    }
}