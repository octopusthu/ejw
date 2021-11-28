package com.octopusthu.ejw.sample.multireadrequest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;

@Slf4j
public class MultiReadTestingFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("MultiReadTestingFilter.doFilter is called");

        log.debug("request.getMethod(): " + request.getMethod());

        log.debug("request.getHeaderNames(): " + request.getHeaderNames());
        log.debug("request.getCookies(): " + Arrays.toString(request.getCookies()));

        try {
            log.debug("request.getParts(): " + request.getParts());
        } catch (Exception ignored) {
        }

        log.debug("request.getCharacterEncoding(): " + request.getCharacterEncoding());
        log.debug("request.getContentLength(): " + request.getContentLength());

        log.debug("request.getParameterMap(): " + new HashMap<>(request.getParameterMap()));

        Writer inputStreamWriter = new StringWriter();
        IOUtils.copy(request.getInputStream(), inputStreamWriter, Charset.defaultCharset());
        log.debug("request.getInputStream(): " + inputStreamWriter);

        Writer readerWriter = new StringWriter();
        IOUtils.copy(request.getReader(), readerWriter);
        log.debug("request.getReader(): " + readerWriter);

        chain.doFilter(request, response);
    }
}
