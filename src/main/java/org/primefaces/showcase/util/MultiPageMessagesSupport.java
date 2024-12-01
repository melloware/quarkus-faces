package org.primefaces.showcase.util;

import java.io.Serial;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;

/**
 * Enables messages to be rendered on different pages from which they were set.
 * <p>
 * After each phase where messages may be added, this moves the messages
 * from the page-scoped FacesContext to the session-scoped session map.
 * </p>
 * <p>
 * Before messages are rendered, this moves the messages from the
 * session-scoped session map back to the page-scoped FacesContext.
 * </p>
 * <p>
 * Only global messages, not associated with a particular component, are
 * moved. Component messages cannot be rendered on pages other than the one on
 * which they were added.
 * </p>
 * <p>
 * To enable multi-page messages support, add a <code>lifecycle</code> block to your
 * faces-config.xml file. That block should contain a single
 * <code>phase-listener</code> block containing the fully-qualified classname
 * of this file.
 * </p>
 * <p>
 * Example configuration in faces-config.xml:
 * <pre>
 * &lt;lifecycle&gt;
 *     &lt;phase-listener&gt;org.primefaces.showcase.util.MultiPageMessagesSupport&lt;/phase-listener&gt;
 * &lt;/lifecycle&gt;
 * </pre>
 * </p>
 *
 * @author Jesse Wilson jesse[AT]odel.on.ca
 * @author Lincoln Baxter III lincoln[AT]ocpsoft.com
 */
public class MultiPageMessagesSupport implements PhaseListener {

    @Serial
    private static final long serialVersionUID = 1250469273857785274L;
    private static final String TOKEN = "MULTI_PAGE_MESSAGES_SUPPORT";

    /**
     * Returns the phase id indicating this listener is interested in all phases.
     * @return PhaseId.ANY_PHASE
     */
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    /**
     * Before each phase, saves messages to the session. During RENDER_RESPONSE phase,
     * restores messages if the response is not complete.
     * 
     * @param event The phase event
     */
    public void beforePhase(final PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        this.saveMessages(facesContext, facesContext.getExternalContext().getSessionMap());

        if (PhaseId.RENDER_RESPONSE.equals(event.getPhaseId())) {
            /*
             * Check to see if we are "naturally" in the RENDER_RESPONSE phase. If
             * we have arrived here and the response is already complete, then the
             * page is not going to show up: don't display messages yet.
             */
            if (!facesContext.getResponseComplete()) {
                this.restoreMessages(facesContext, facesContext.getExternalContext().getSessionMap());
            }
        }
    }

    /**
     * After each phase except RENDER_RESPONSE, saves messages to the session.
     * 
     * @param event The phase event
     */
    public void afterPhase(final PhaseEvent event) {
        if (!PhaseId.RENDER_RESPONSE.equals(event.getPhaseId())) {
            FacesContext facesContext = event.getFacesContext();
            this.saveMessages(facesContext, facesContext.getExternalContext().getSessionMap());
        }
    }

    /**
     * Saves FacesMessages from the FacesContext to the provided destination map.
     * Messages are wrapped in FacesMessageWrapper objects and stored in a LinkedHashSet.
     * 
     * @param facesContext The FacesContext containing messages to save
     * @param destination The map where messages will be stored
     */
    @SuppressWarnings("unchecked")
    public void saveMessages(final FacesContext facesContext, final Map<String, Object> destination) {
        if (facesContext != null) {
            Set<FacesMessageWrapper> messages = new LinkedHashSet<>();
            for (Iterator<FacesMessage> iter = facesContext.getMessages(null); iter.hasNext(); ) {
                messages.add(new FacesMessageWrapper(iter.next()));
            }

            if (messages.size() > 0) {
                Set<FacesMessageWrapper> existingMessages = (LinkedHashSet<FacesMessageWrapper>) destination.get(TOKEN);
                if (existingMessages != null) {
                    existingMessages.addAll(messages);
                } else {
                    destination.put(TOKEN, messages);
                }
            }
        }
    }

    /**
     * Restores messages from the source map to the FacesContext.
     * Only messages that don't already exist in the FacesContext are restored.
     * 
     * @param facesContext The FacesContext to restore messages to
     * @param source The map containing saved messages
     */
    @SuppressWarnings("unchecked")
    public void restoreMessages(final FacesContext facesContext, final Map<String, Object> source) {
        if (facesContext != null) {
            // get save messages from the session
            Set<FacesMessageWrapper> messages = (LinkedHashSet<FacesMessageWrapper>) source.remove(TOKEN);

            // nothing to do
            if (messages == null) {
                return;
            }

            // build set of message currently in the FacesContext
            Set<FacesMessageWrapper> exitingMessages = new LinkedHashSet<>();
            for (Iterator<FacesMessage> iter = facesContext.getMessages(null); iter.hasNext(); ) {
                exitingMessages.add(new FacesMessageWrapper(iter.next()));
            }

            // restore all messages not already in the FacesContext
            for (FacesMessageWrapper message : messages) {
                if (!exitingMessages.contains(message)) {
                    facesContext.addMessage(null, message.wrapped());
                }
            }

        }
    }

    /**
     * A wrapper class for FacesMessage that implements equals/hashCode for proper Set behavior
     * and implements Serializable for session storage.
     */
    private record FacesMessageWrapper(FacesMessage wrapped) implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FacesMessageWrapper other = (FacesMessageWrapper) o;
            return Objects.equals(wrapped.getSeverity(), other.wrapped().getSeverity()) &&
                    Objects.equals(wrapped.getSummary(), other.wrapped().getSummary()) &&
                    Objects.equals(wrapped.getDetail(), other.wrapped().getDetail());
        }

        @Override
        public int hashCode() {
            return Objects.hash(wrapped.getSeverity(), wrapped.getSummary(), wrapped.getDetail());
        }

    }
}