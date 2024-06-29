package org.primefaces.showcase.view.app;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.util.LangUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Parse the primefaces.taglib.xml and stores a map of all available tags.
 */
@Named
@ApplicationScoped
public class TagLibrary {

    /**
     * Map of lowercase tag names to the real tag name.
     */
    private final Map<String, String> tags = new HashMap<>();

    @PostConstruct
    public void init() throws ParserConfigurationException {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilderFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, ""); // Compliant
        docBuilderFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, ""); // Compliant
        docBuilderFactory.setIgnoringElementContentWhitespace(true);
        docBuilderFactory.setValidating(false);
        docBuilderFactory.setNamespaceAware(true);

        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = TagLibrary.class.getClassLoader();
        }

        try (InputStream is = classLoader.getResourceAsStream("META-INF/primefaces.taglib.xml")) {
            if (is == null) {
                throw new IOException("Resource not found: META-INF/primefaces.taglib.xml");
            }
            Document doc = docBuilder.parse(is);
            NodeList nodes = doc.getElementsByTagName("tag");

            for (int i = 0; i < nodes.getLength(); i++) {
                NodeList children = nodes.item(i).getChildNodes();

                for (int j = 0; j < children.getLength(); j++) {
                    Node child = children.item(j);
                    if ("tag-name".equals(child.getNodeName())) {
                        String tagName = child.getTextContent();
                        tags.put(tagName.toLowerCase(Locale.ROOT), tagName);
                    }
                }
            }
        } catch (IOException | SAXException e) {
            throw new IllegalStateException("Error while reading XML: " + e.getMessage(), e);
        }
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public boolean isTagVdlAvailable(String documentationLink) {
        if (LangUtils.isEmpty(documentationLink)) {
            return false;
        }
        return getTags().containsKey(parseLink(documentationLink));
    }

    public String getTagVdlComponent(String documentationLink) {
        return getTags().get(parseLink(documentationLink));
    }

    private String parseLink(String documentationLink) {
        return StringUtils.substringAfterLast(documentationLink.toLowerCase(Locale.ROOT), "/");
    }
}