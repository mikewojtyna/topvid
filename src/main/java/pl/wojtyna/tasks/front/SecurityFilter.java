package pl.wojtyna.tasks.front;

import jakarta.servlet.http.HttpServletRequest;

public class SecurityFilter implements Filter {

    private Filter delegate;

    @Override
    public ProcessingResult process(HttpServletRequest request) {
        if (isNotSafe(request)) {
            return ProcessingResult.unauthorized();
        }
        if (valueIsCached()) {
            return resultFromCache();
        }
        return delegate.process(request);
    }

    private ProcessingResult resultFromCache() {
        return null;
    }

    private boolean valueIsCached() {
    }

    private boolean isNotSafe(HttpServletRequest request) {
        return false;
    }
}
