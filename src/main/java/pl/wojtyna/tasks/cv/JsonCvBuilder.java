package pl.wojtyna.tasks.cv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JsonCvBuilder implements CvBuilder {

    private String jsonResult;
    private Map<String, Object> json;
    private List<String> paragraphs;

    public String getJsonResult() {
        return jsonResult;
    }

    @Override
    public void handleHeader(String header) {
        json.put("header", header);
    }

    @Override
    public void handleParagraph(String paragraph) {
        paragraphs.add(paragraph);
    }

    @Override
    public void handleFooter(String footer) {
        json.put("paragraphs", paragraphs);
        json.put("footer", footer);
    }

    @Override
    public void build() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonResult = objectMapper.writeValueAsString(json);
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
