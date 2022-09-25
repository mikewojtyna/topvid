package pl.wojtyna.topvid.hr;

import lombok.Data;
import pl.wojtyna.topvid.patterns.BuilderPattern;

import java.util.List;

@Data
@BuilderPattern
public class CvDto {

    private String header;
    private List<String> paragraphs;
    private String footer;
}
