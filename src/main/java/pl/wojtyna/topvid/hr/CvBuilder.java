package pl.wojtyna.topvid.hr;

import lombok.NonNull;
import pl.wojtyna.topvid.patterns.BuilderPattern;

@BuilderPattern
public interface CvBuilder {

    CvBuilder handleHeader(@NonNull String content);

    CvBuilder handleParagraph(@NonNull String content);

    CvBuilder handleFooter(@NonNull String content);
}
