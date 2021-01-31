package com.octopusthu.ejw.sample.cookiewriter;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/cookies")
public class CookieWriterController {

    @GetMapping("/read")
    public Cookie[] read(HttpServletRequest request) {
        return request.getCookies();
    }

    @PostMapping("/write")
    public void write(@RequestParam String name,
                      @RequestParam String value,
                      @RequestParam String domain,
                      @RequestParam String path,
                      @RequestParam(defaultValue = "300") int maxAge,
                      @RequestParam(defaultValue = "true") boolean secure,
                      @RequestParam(defaultValue = "false") boolean httpOnly,
                      HttpServletResponse response) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setSecure(secure);
        cookie.setHttpOnly(httpOnly);
        response.addCookie(cookie);
    }

}
