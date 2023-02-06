package pl.wojtyna.tasks.front;

import jakarta.servlet.http.HttpServletRequest;

public interface Filter {

    boolean applies(HttpServletRequest request);

    ProcessingResult process(HttpServletRequest request);
}
