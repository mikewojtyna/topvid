package pl.wojtyna.tasks.cv;

public interface CvBuilder {

    void handleHeader(String header);

    void handleParagraph(String paragraph);

    void handleFooter(String footer);

    void build();
}
