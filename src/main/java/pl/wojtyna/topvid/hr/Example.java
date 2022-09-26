package pl.wojtyna.topvid.hr;

import pl.wojtyna.topvid.patterns.BuilderPattern;

import java.io.IOException;
import java.io.InputStream;

@BuilderPattern
public class Example {

    public static void main(String[] args) throws IOException {
        DocumentDtoCvBuilder dtoBuilder = new DocumentDtoCvBuilder();
        HtmlCvBuilder htmlBuilder = new HtmlCvBuilder();

        CvParser parser = new CvParser(dtoBuilder,
                                       htmlBuilder);
        InputStream resourceAsStream = Example.class
            .getResourceAsStream("/files/george-martin-cv.txt");
        parser.parse(resourceAsStream);

        System.out.println("DTO result");
        System.out.println(dtoBuilder.getDto());
        System.out.println("HTML result");
        System.out.println(htmlBuilder.getHtml());
    }
}
