package pl.wojtyna.tasks.cv;

import java.util.List;

public record CvDocument(String header, List<String> paragraphs, String footer) {
}
