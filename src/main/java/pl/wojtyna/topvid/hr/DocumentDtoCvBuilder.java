package pl.wojtyna.topvid.hr;

import pl.wojtyna.topvid.patterns.BuilderPattern;

import java.util.ArrayList;

@BuilderPattern
public class DocumentDtoCvBuilder implements CvBuilder {

    private final CvDto dto;

    public DocumentDtoCvBuilder() {
        dto = new CvDto();
        dto.setParagraphs(new ArrayList<>());
    }

    @Override
    public CvBuilder handleHeader(String content) {
        dto.setHeader(content);
        return this;
    }

    @Override
    public CvBuilder handleParagraph(String content) {
        dto.getParagraphs().add(content);
        return this;
    }

    @Override
    public CvBuilder handleFooter(String content) {
        dto.setFooter(content);
        return this;
    }

    public CvDto getDto() {
        return dto;
    }
}
