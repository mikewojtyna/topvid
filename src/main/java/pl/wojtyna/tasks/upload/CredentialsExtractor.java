package pl.wojtyna.tasks.upload;

import jakarta.servlet.http.HttpServletRequest;

public interface CredentialsExtractor {

    Credentials extractCredentials(HttpServletRequest request);
}
