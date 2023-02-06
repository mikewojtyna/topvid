package pl.wojtyna.tasks.front;

import jakarta.servlet.http.HttpServletRequest;

import java.util.logging.Logger;

public class BenchmarkingFilter implements Filter {

    private final Filter delegate;

    public BenchmarkingFilter(Filter delegate) {
        this.delegate = delegate;
    }

    @Override
    public void process(HttpServletRequest request) {
        var start = System.currentTimeMillis();
        delegate.process(request);
        var end = System.currentTimeMillis();
        Logger.getAnonymousLogger().info("Processsing took %s".formatted(end - start));
    }
}
