package pl.wojtyna.tasks.upload;

import jakarta.servlet.http.HttpServletRequest;

public interface IdentityFacade {

    User currentUser(HttpServletRequest request);
}
