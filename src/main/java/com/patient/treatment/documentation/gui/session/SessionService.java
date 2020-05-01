package com.patient.treatment.documentation.gui.session;

import com.patient.treatment.documentation.gui.model.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


@Service
public class SessionService {

    private RequestUtils requestUtils;

    @Autowired
    public SessionService(RequestUtils requestUtils) {
        this.requestUtils = requestUtils;
    }

    public void invalidate() {
        getActualSession().invalidate();
    }

    public User getAuthenticatedUser() {
        return (User) getActualSession().getAttribute(SessionConstantAttributes.AUTHENTICATED_USER);
    }

    public void setAuthenticatedUser(User user) {
        getActualSession().setAttribute(SessionConstantAttributes.AUTHENTICATED_USER, user);
    }

    private HttpSession getActualSession() {
        return requestUtils.getHttpServletRequest().getSession();
    }

}