package pl.wojtyna.tasks.upload;

import jakarta.servlet.http.HttpServletRequest;

public abstract class BaseApi {

    private final UserIdentityService userIdentityService = UserIdentityServiceFactory.getInstance();
    private final CredentialsExtractor credentialsExtractor = CredentialsExtractorFactory.getInstance();

    public User currentUser(HttpServletRequest request) {
        var credentials = credentialsExtractor.extractCredentials(request);
        User user = userIdentityService.identityOf(credentials).orElseThrow();
        return user;
    }
}
