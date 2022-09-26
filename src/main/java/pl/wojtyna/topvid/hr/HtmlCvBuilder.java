package pl.wojtyna.topvid.hr;

import pl.wojtyna.topvid.patterns.BuilderPattern;

@BuilderPattern
public class HtmlCvBuilder implements CvBuilder {

    private String content;

    public HtmlCvBuilder() {
        content = "";
    }

    @Override
    public CvBuilder handleHeader(String content) {
        this.content += "<h1>" + content + "</h1>";
        return this;
    }

    @Override
    public CvBuilder handleParagraph(String content) {
        this.content += "<p>" + content + "</p>";
        return this;
    }

    @Override
    public CvBuilder handleFooter(String content) {
        this.content += "<div id='footer'><b>" + content + "</b></div>";
        return this;
    }

    public String getHtml() {
        return "<html><body>" + content + "</body></html>";
    }
}
