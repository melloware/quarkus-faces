package org.primefaces.showcase.util;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import lombok.extern.jbosslog.JBossLog;

@WebFilter("/*")
@JBossLog
public class BotFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String ua = req.getHeader("User-Agent");

        if (ua == null || ua.contains("Bytespider") ||ua.contains("curl") ||ua.contains("python") ||ua.contains("wget")) {
            log.infof("Blocked request from: %s" + ua);
            ((HttpServletResponse) response).sendError(403);
            return;
        }

        chain.doFilter(request, response);
    }
}