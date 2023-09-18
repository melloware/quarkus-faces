package org.primefaces.showcase.service;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.IOException;
import java.time.Duration;
import lombok.SneakyThrows;
import org.eclipse.microprofile.config.inject.ConfigProperty;
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

    @ConfigProperty(name = "sso.idle.timeout", defaultValue = "PT30M")
    Duration idleTimeout;

    @ConfigProperty(name = "sso.idle.timeout.warning", defaultValue = "PT1M")
    Duration idleTimeoutWarning;
    
    @ConfigProperty(name = "quarkus.oidc.logout.path")
    String logoutPath;

    /**
     * Checks if we are logged in
     *
     * @return True or False
     */
    public boolean isLoggedIn() {
        log.infof("User is logged in = [%s] with name [%s] and roles %s", !identity.isAnonymous(), identity.getPrincipal().getName(), identity.getRoles());
        return identity != null && !identity.isAnonymous();
    }

    /**
     * Get the currently logged in username
     * 
     * @return Username or ANONYMOUS if not logged in
     */
    public String getUsername() {
        String username = identity.getPrincipal().getName();
        if (username == null || username.trim().isEmpty()) {
            username = "ANONYMOUS";
        }

        return username;
    }
    
    
    /**
     * Calculate the warning time we should show the session warning dialog
     * 
     * @return Time in millis of the difference between `idleTimeout` and `idleTimeoutWarning`
     */
    public long calculateIdleTimeWarningTime() {
        long abs = idleTimeout.minus(idleTimeoutWarning).toMillis();
        log.infof("Calculated idle warning time is %d", abs);
        return abs;
    }

    /**
     * Log the user out of the system by calling the virtual
     * Keycloak OIDC logout method configured in the properties
     */
    @SneakyThrows(value = IOException.class)
    public void logout() {
        log.infof("Logging out user [%s]", getUsername() );

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();

        // Perform the redirect to logout
        externalContext.redirect(logoutPath);
    }
    
    /**
     * Called to refresh the tokens by just making a backend call
     * and using the auto-refresh settings from OIDC in the application.properties
     *      */
    public void ping() {
        log.info("Ping!");
   }

    public Duration getIdleTimeoutWarning() {
        return idleTimeoutWarning;
    }
      
}
