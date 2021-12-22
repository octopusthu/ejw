package com.octopusthu.ejw.sample.multireadrequest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * A Multi-Read <code>HttpServletRequest</code> adapted from
 * <a href="https://stackoverflow.com/questions/10210645/http-servlet-request-lose-params-from-post-body-after-read-it-once">
 * this Stack Overflow post</a>.
 *
 * @author figozhang
 */
@Slf4j
public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {
    private final ByteArrayOutputStream cachedBytes;

    public MultiReadHttpServletRequest(HttpServletRequest request) throws IOException {
        super(request);

        log.debug("MultiReadHttpServletRequest constructor is called");

        // Cache on construct
        cachedBytes = new ByteArrayOutputStream();
        IOUtils.copy(super.getInputStream(), cachedBytes);
    }

    @Override
    public ServletInputStream getInputStream() {
        log.debug("MultiReadHttpServletRequest.getInputStream() is called");
        return new CachedServletInputStream(this.cachedBytes);
    }

    @Override
    public BufferedReader getReader() {
        log.debug("MultiReadHttpServletRequest.getReader() is called");

        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    /**
     * An InputStream which reads the cached request body
     */
    @Slf4j
    private static class CachedServletInputStream extends ServletInputStream {
        private final ByteArrayInputStream input;

        public CachedServletInputStream(ByteArrayOutputStream cachedBytes) {
            this.input = new ByteArrayInputStream(cachedBytes.toByteArray());
        }

        @Override
        public boolean isFinished() {
            log.debug("CachedServletInputStream.isFinished() is called");

            return false;
        }

        @Override
        public boolean isReady() {
            log.debug("CachedServletInputStream.isReady() is called");

            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {
            log.debug("CachedServletInputStream.setReadListener is called");
        }

        @Override
        public int read() {
            return input.read();
        }
    }
}
