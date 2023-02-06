package pl.wojtyna.tasks.upload;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/some/other/controller")
public class SomeOtherRestApi {

    private IdentityFacade identityFacade;

    @GetMapping
    public void doSomething(HttpServletRequest request) {
        User user = identityFacade.currentUser(request);
        doSomethingWith(user);
    }

    private void doSomethingWith(User user) {

    }
}
