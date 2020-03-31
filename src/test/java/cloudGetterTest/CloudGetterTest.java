package cloudGetterTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CloudGetterTest {

    CloudGetter cloudGetter;

    @BeforeEach
    void setup() { cloudGetter = new CloudGetter(); }

    @Test
    void getTodoItemJsonString() throws IOException {
            var result = cloudGetter.getTodoItemJsonString(1);
            assertEquals("{\n" +
                    "  \"id\": 1,\n" +
                    "  \"title\": \"Explain the project\",\n" +
                    "  \"owner\": \"hergin\"\n" +
                    "}", result);
        }
    }
