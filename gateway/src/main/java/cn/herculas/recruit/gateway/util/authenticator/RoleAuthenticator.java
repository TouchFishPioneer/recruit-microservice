package cn.herculas.recruit.gateway.util.authenticator;

import cn.herculas.recruit.gateway.service.CookieService;
import cn.herculas.recruit.gateway.service.SessionService;
import com.netflix.zuul.context.RequestContext;
import org.springframework.http.HttpStatus;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RoleAuthenticator {
    public static Object roleAuthenticator(CookieService cookieService, SessionService sessionService, List<Integer> roleList) {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();

        Cookie cookie = cookieService.findCookie(httpServletRequest);
        Integer userRole = Integer.valueOf(sessionService.getRoleFromSession(cookie));

        if (!roleList.contains(userRole)) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
        }
        return null;
    }
}
