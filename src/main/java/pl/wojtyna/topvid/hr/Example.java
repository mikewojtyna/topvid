package pl.wojtyna.topvid.hr;

import pl.wojtyna.topvid.patterns.BuilderPattern;

import java.io.InputStream;

@BuilderPattern
public class Example {

    public static void main(String[] args) {
        DocumentDtoCvBuilder dtoBuilder = new DocumentDtoCvBuilder();
        HtmlCvBuilder htmlBuilder = new HtmlCvBuilder();

        DocumentFileParser parser = new DocumentFileParser(dtoBuilder,
                                                           htmlBuilder);
        InputStream resourceAsStream = Example.class
            .getResourceAsStream("/files/george-martin-cv.txt");
        parser.parse(resourceAsStream);

        System.out.println("DTO result: " + dtoBuilder.getDto());
        System.out.println("HTML result: " + htmlBuilder.getHtml());
    }
}
