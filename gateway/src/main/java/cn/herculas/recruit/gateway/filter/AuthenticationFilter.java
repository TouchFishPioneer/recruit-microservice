package cn.herculas.recruit.gateway.filter;

import cn.herculas.recruit.gateway.enumeration.PermissionEnum;
import cn.herculas.recruit.gateway.enumeration.RoleEnum;
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
        List<Integer> permittedRoles = PermissionEnum.rolePermitted(httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
        return permittedRoles == null || !permittedRoles.contains(RoleEnum.NO_DEMAND.getCode());
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();
        Cookie cookie = cookieService.findCookie(httpServletRequest);

        // If no cookie in request

        if (cookie == null) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            return null;
        }

        List<Integer> permittedRoles = PermissionEnum.rolePermitted(httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
        String roleString = sessionService.getRoleFromSession(cookie);

        // If no consistent session in database

        if (roleString == null) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            return null;
        }

        // If try to access a path which has not been logged

        if (permittedRoles == null) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
            return null;
        }

        // If the permitted list of this path do not contains the role of this request

        Integer thisRole = Integer.valueOf(roleString);
        if (!permittedRoles.contains(thisRole)) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
            return null;
        }
        return null;
    }
}
