package pl.wojtyna.tasks.cv;

import java.util.List;

public class Selection {

    private CvDocument cvDocument;
    private List<CvBuilder> cvBuilders;

    public void build() {
        cvBuilders.forEach(cvBuilder -> cvBuilder.build(cvDocument));
    }
}
