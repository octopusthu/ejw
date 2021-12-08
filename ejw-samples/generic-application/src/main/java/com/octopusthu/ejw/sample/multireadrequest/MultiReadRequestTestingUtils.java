package com.octopusthu.ejw.sample.multireadrequest;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;

public class MultiReadRequestTestingUtils {

    public static void printRequestBasicInfo(HttpServletRequest request, Logger log) {
        log.debug("MultiReadTestingFilter.doFilter is called");

        log.debug("request.getMethod(): " + request.getMethod());

        log.debug("request.getHeaderNames(): " + printStringEnumeration(request.getHeaderNames()));
        log.debug("request.getCookies(): " + Arrays.toString(request.getCookies()));

        try {
            log.debug("request.getParts(): " + request.getParts());
        } catch (Exception ignored) {
        }

        log.debug("request.getCharacterEncoding(): " + request.getCharacterEncoding());
        log.debug("request.getContentType(): " + request.getContentType());
        log.debug("request.getContentLength(): " + request.getContentLength());
    }

    public static void printRequestBody(HttpServletRequest request, Logger log) throws IOException {
        Writer inputStreamWriter = new StringWriter();
        IOUtils.copy(request.getInputStream(), inputStreamWriter, Charset.defaultCharset());
        log.debug("request.getInputStream(): " + inputStreamWriter);

        Writer readerWriter = new StringWriter();
        IOUtils.copy(request.getReader(), readerWriter);
        log.debug("request.getReader(): " + readerWriter);
    }

    public static void printParameters(HttpServletRequest request, Logger log) {
        log.debug("request.getParameterMap(): " + printParameterMap(request.getParameterMap()));
    }

    public static String printParameterMap(Map<String, String[]> parameterMap) {
        StringBuffer map = new StringBuffer("\n");
        parameterMap.forEach((key, value) -> map.append(key).append(":").append(Arrays.toString(value)).append("\n"));
        return map.toString();
    }

    public static String printParameterNames(Enumeration<String> parameterNames) {
        return printStringEnumeration(parameterNames);
    }

    public static String printStringEnumeration(Enumeration<String> enumeration) {
        List<String> list = new ArrayList<>();
        enumeration.asIterator().forEachRemaining(list::add);
        return list.toString();
    }
}
