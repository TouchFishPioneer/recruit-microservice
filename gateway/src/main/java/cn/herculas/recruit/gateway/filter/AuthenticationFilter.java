package cn.herculas.recruit.gateway.filter;

import cn.herculas.recruit.gateway.enumeration.PermissionEnum;
import cn.herculas.recruit.gateway.service.CookieService;
import cn.herculas.recruit.gateway.service.SessionService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class AuthenticationFilter extends ZuulFilter {

    private final CookieService cookieService;
    private final SessionService sessionService;

    public AuthenticationFilter(CookieService cookieService, SessionService sessionService) {
        this.cookieService = cookieService;
        this.sessionService = sessionService;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();
        return PermissionEnum.rolePermitted(httpServletRequest.getMethod(), httpServletRequest.getRequestURI()) != null;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();
        Cookie cookie = cookieService.findCookie(httpServletRequest);
        if (cookie == null) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            return null;
        }
        List<Integer> rolePermitted = PermissionEnum.rolePermitted(httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
        Integer role = Integer.valueOf(sessionService.getRoleFromSession(cookie));

        if (rolePermitted == null) {
            return null;
        }

        if (!rolePermitted.contains(role)) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
            return null;
        }
        return null;
    }
}
