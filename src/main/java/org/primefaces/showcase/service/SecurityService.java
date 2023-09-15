package org.primefaces.showcase.service;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.jboss.logging.Logger;


/**
 * Tracks user related stuff
 * 
 * @author tmulle
 */
@Named
@ApplicationScoped
public class SecurityService {

    @Inject
    Logger log;

    @Inject
    SecurityIdentity identity;

 
    /**
     * Checks if we are logged in
     * 
     * @return True or False
     */
    public boolean isLoggedIn() {
        log.infof("User is logged in = [%s] with name [%s] and roles %s", !identity.isAnonymous(),identity.getPrincipal().getName(), identity.getRoles() );
        return identity != null && !identity.isAnonymous();
    }
}
