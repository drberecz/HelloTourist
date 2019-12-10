package hu.ps.ht.util;

import hu.ps.ht.dto.GuideDTO;
import hu.ps.ht.dto.TravellerDTO;
import hu.ps.ht.enumerated.Role;
import hu.ps.ht.service.GuideService;
import hu.ps.ht.service.TravellerService;
import java.security.Principal;
import javax.annotation.security.DeclareRoles;
import javax.ejb.EJBException;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Singleton
@DeclareRoles({Role.GUIDE, Role.TRAVELER, Role.ADMIN})
public class CredentialsManager {

    @Inject
    GuideService guideService;

    @Inject
    TravellerService travellerService;

    public HttpSession validateRoles(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        HttpSession session = request.getSession();
        String username = (principal != null) ? principal.getName() : Role.NOT_AUTHORIZED;
        boolean isImageLinkRetrieved = (session.getAttribute(Role.HTTP_SESSION_ATTR_IMAGELINK) != null);

        if (session.getAttribute(Role.HTTP_SESSION_ATTR_ANONYMOUS) != null) {
            session.removeAttribute(Role.HTTP_SESSION_ATTR_ANONYMOUS);
        }

        if (request.isUserInRole(Role.GUIDE)) {

            session.setAttribute(Role.HTTP_SESSION_ATTR_USERROLE, Role.GUIDE);
            if (!isImageLinkRetrieved) {
                setImageUrlFromDatabaseGuide(session, username);
            }

        } else if (request.isUserInRole(Role.TRAVELER)) {

            session.setAttribute(Role.HTTP_SESSION_ATTR_USERROLE, Role.TRAVELER);
            if (!isImageLinkRetrieved) {
                setImageUrlFromDatabaseTraveler(session, username);
            }

        } else if (request.isUserInRole(Role.ADMIN)) {

            session.setAttribute(Role.HTTP_SESSION_ATTR_USERROLE, Role.ADMIN);
            session.setAttribute(Role.HTTP_SESSION_ATTR_IMAGELINK, Role.DEFAULT_ADMIN_AVATAR_URL);
        } else {
            session.setAttribute(Role.HTTP_SESSION_ATTR_ANONYMOUS, Role.NOT_AUTHORIZED);
            session.setAttribute(Role.HTTP_SESSION_ATTR_IMAGELINK, null);
        }
        session.setAttribute("username", username);

        return session;
    }

    private void setImageUrlFromDatabaseGuide(HttpSession session, String username) {

        GuideDTO guideDTO = guideService.getGuideByUsername(username);

        if (guideDTO.getImageLink() == null) {
            session.setAttribute(Role.HTTP_SESSION_ATTR_IMAGELINK, Role.DEFAULT_AVATAR_URL);
        } else {
            session.setAttribute(Role.HTTP_SESSION_ATTR_IMAGELINK, guideDTO.getImageLink());
        }

    }

    private void setImageUrlFromDatabaseTraveler(HttpSession session, String username) {

        TravellerDTO travellerDTO = null;
        try {
            travellerDTO = travellerService.getTravellerByUsername(username);
        } catch (EJBException e) {
            System.out.println(e.getLocalizedMessage());
        }

        if (travellerDTO == null) {
            session.setAttribute(Role.HTTP_SESSION_ATTR_IMAGELINK, Role.DEFAULT_AVATAR_URL);
        } else {
            session.setAttribute(Role.HTTP_SESSION_ATTR_IMAGELINK, travellerDTO.retrieveImageLink());
        }
    }

}
