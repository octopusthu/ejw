package com.octopusthu.ejw.sample.multireadrequest;

import static com.octopusthu.ejw.sample.multireadrequest.MultiReadRequestTestingUtils.printParameters;
import static com.octopusthu.ejw.sample.multireadrequest.MultiReadRequestTestingUtils.printRequestBasicInfo;
import static com.octopusthu.ejw.sample.multireadrequest.MultiReadRequestTestingUtils.printRequestBody;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MultiReadRequestTestingFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        printRequestBasicInfo(request, log);
        printRequestBody(request, log);

        log.debug("printing form parameters AFTER request body is read");
        printParameters(request, log);

        chain.doFilter(request, response);
    }
}
