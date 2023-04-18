package org.primefaces.showcase.util;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
 
/**
 * Enables messages to be rendered on different pages from which they were set.
 * <p></p>
 * After each phase where messages may be added, this moves the messages
 * from the page-scoped FacesContext to the session-scoped session map.
 *<p></p>
 * Before messages are rendered, this moves the messages from the
 * session-scoped session map back to the page-scoped FacesContext.
 *<p></p>
 * Only global messages, not associated with a particular component, are
 * moved. Component messages cannot be rendered on pages other than the one on
 * which they were added.
 *<p></p>
 * To enable multi-page messages support, add a <code>lifecycle</code> block to your
 * faces-config.xml file. That block should contain a single
 * <code>phase-listener</code> block containing the fully-qualified classname
 * of this file.
 *
 * @author Jesse Wilson jesse[AT]odel.on.ca
 * @author  Lincoln Baxter III lincoln[AT]ocpsoft.com
 */
public class MultiPageMessagesSupport implements PhaseListener
{
 
    @Serial
    private static final long serialVersionUID = 1250469273857785274L;
    private static final String sessionToken = "MULTI_PAGE_MESSAGES_SUPPORT";
 
    public PhaseId getPhaseId()
    {
        return PhaseId.ANY_PHASE;
    }
 
    /*
     * Check to see if we are "naturally" in the RENDER_RESPONSE phase. If we
     * have arrived here and the response is already complete, then the page is
     * not going to show up: don't display messages yet.
     */
    public void beforePhase(final PhaseEvent event)
    {
        FacesContext facesContext = event.getFacesContext();
        this.saveMessages(facesContext);
 
        if (PhaseId.RENDER_RESPONSE.equals(event.getPhaseId()))
        {
            if (!facesContext.getResponseComplete())
            {
                this.restoreMessages(facesContext);
            }
        }
    }
 
    /*
     * Save messages into the session after every phase.
     */
    public void afterPhase(final PhaseEvent event)
    {
        if (!PhaseId.RENDER_RESPONSE.equals(event.getPhaseId()))
        {
            FacesContext facesContext = event.getFacesContext();
            this.saveMessages(facesContext);
        }
    }
 
    @SuppressWarnings("unchecked")
    private int saveMessages(final FacesContext facesContext)
    {
        List<FacesMessage> messages = new ArrayList<>();
        for (Iterator<FacesMessage> iter = facesContext.getMessages(null); iter.hasNext();)
        {
            messages.add(iter.next());
            iter.remove();
        }
 
        if (messages.size() == 0)
        {
            return 0;
        }
 
        Map<String, Object> sessionMap = facesContext.getExternalContext().getSessionMap();
        List<FacesMessage> existingMessages = (List<FacesMessage>) sessionMap.get(sessionToken);
        if (existingMessages != null)
        {
            for(FacesMessage fm: messages){
                if(!existingMessages.contains(fm)){
                  existingMessages.add(fm);
                }
              }
        }
        else
        {
            sessionMap.put(sessionToken, messages);
        }
        return messages.size();
    }
 
    @SuppressWarnings("unchecked")
    private int restoreMessages(final FacesContext facesContext)
    {
        Map<String, Object> sessionMap = facesContext.getExternalContext().getSessionMap();
        List<FacesMessage> messages = (List<FacesMessage>) sessionMap.remove(sessionToken);
 
        if (messages == null)
        {
            return 0;
        }
 
        int restoredCount = messages.size();
        for (FacesMessage element : messages)
        {
            facesContext.addMessage(null, element);
        }
        return restoredCount;
    }
}