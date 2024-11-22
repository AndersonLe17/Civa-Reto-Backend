package com.totospz.eshop.config.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.util.WebUtils;

public class CookieUtil {

    public static void create(HttpServletResponse response, String name, String value,
                              Boolean secure, Boolean httpOnly, Integer maxAge, String path) {
        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(secure);
        cookie.setHttpOnly(httpOnly);
        cookie.setMaxAge(maxAge);
        cookie.setDomain(response.getHeader("Host"));
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    public static void clear(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie cookie = WebUtils.getCookie(request, name);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public static void clear(HttpServletResponse response, String name, Boolean secure, Boolean httpOnly, String path) {
        Cookie cookie = new Cookie(name, null);
        cookie.setSecure(secure);
        cookie.setHttpOnly(httpOnly);
        cookie.setMaxAge(0);
        cookie.setDomain(response.getHeader("Host"));
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    public static String getValueFromCookie(HttpServletRequest request, String name) {
        Cookie cookie = WebUtils.getCookie(request, name);
        return cookie != null ? cookie.getValue() : null;
    }

}
