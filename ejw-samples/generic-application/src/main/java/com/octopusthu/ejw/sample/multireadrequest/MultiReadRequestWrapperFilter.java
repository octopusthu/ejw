package com.octopusthu.ejw.sample.multireadrequest;

import java.io.IOException;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

/**
 * Make a <code>HttpServletRequest</code> multi-readable by wrapping it with a {@link MultiReadHttpServletRequest}.
 * <p>
 * Wrapping is done only when the request's media type is compatible with one of the values
 * in the {@link #mediaTypeWhiteList}, which by default contains only "application/json".
 *
 * @author figozhang
 */
@Slf4j
public class MultiReadRequestWrapperFilter extends HttpFilter {
    private static final List<MediaType> DEFAULT_MEDIA_TYPE_WHITELIST = List.of(
        MediaType.APPLICATION_JSON
    );

    private List<MediaType> mediaTypeWhiteList = DEFAULT_MEDIA_TYPE_WHITELIST;

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest wrappedRequest = request;
        if (shouldWrap(request)) {
            if (log.isDebugEnabled()) {
                log.debug("Wrapping request with MultiReadHttpServletRequest, origin request is: "
                    + request.getRequestURL());
            }
            wrappedRequest = new MultiReadHttpServletRequest(request);
        }
        super.doFilter(wrappedRequest, response, chain);
    }

    protected boolean shouldWrap(HttpServletRequest request) {
        final MediaType mediaType;
        try {
            String contentType = request.getContentType();
            mediaType = MediaType.parseMediaType(contentType);
        } catch (Exception ignored) {
            return false;
        }
        return mediaTypeWhiteList.stream().anyMatch(type -> type.isCompatibleWith(mediaType));
    }

    @SuppressWarnings("unused")
    public void setMediaTypeWhiteList(List<MediaType> mediaTypeWhiteList) {
        this.mediaTypeWhiteList = mediaTypeWhiteList;
    }

}
