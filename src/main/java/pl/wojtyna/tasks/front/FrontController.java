package pl.wojtyna.tasks.front;

import jakarta.servlet.http.HttpServletRequest;

public class FrontController {

    private ConfigurationManager configurationManager;

    public void handle(HttpServletRequest request) {
        configurationManager.findMatchingConfiguration().ifPresent(matchingConfiguration -> {
            matchingConfiguration.preProcess(request);
            matchingConfiguration.delegateToMatchingController();
        });
    }
}
