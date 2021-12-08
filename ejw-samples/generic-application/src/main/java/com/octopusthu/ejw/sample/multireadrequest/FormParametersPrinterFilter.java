package com.octopusthu.ejw.sample.multireadrequest;

import static com.octopusthu.ejw.sample.multireadrequest.MultiReadRequestTestingUtils.printParameters;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Slf4j
public class FormParametersPrinterFilter extends HttpFilter {
    private static final String READ_FORM_PARAMETER_HEADER_NAME = "Read-Form-Parameter";
    private static final RequestMatcher REQUEST_MATCHER =
        new RequestHeaderRequestMatcher(READ_FORM_PARAMETER_HEADER_NAME);

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        if (REQUEST_MATCHER.matches(request)) {
            log.debug("printing form parameters BEFORE request body is read");
            printParameters(request, log);
        }

        chain.doFilter(request, response);
    }
}
