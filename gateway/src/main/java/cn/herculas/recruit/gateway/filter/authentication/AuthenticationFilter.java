package cn.herculas.recruit.gateway.filter.authentication;

import cn.herculas.recruit.gateway.service.CookieService;
import cn.herculas.recruit.gateway.util.constant.UriConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class AuthenticationFilter extends ZuulFilter {

    private final CookieService cookieService;

    public AuthenticationFilter(CookieService cookieService) {
        this.cookieService = cookieService;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 2;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();

        System.out.println(httpServletRequest.getMethod());
        System.out.println(httpServletRequest.getQueryString());
        System.out.println(httpServletRequest.getRequestURI());

        return !httpServletRequest.getRequestURI().equals(UriConstants.TEACHER_LOGIN)
                && !httpServletRequest.getRequestURI().equals(UriConstants.STUDENT_LOGIN);
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();

        Cookie cookie = cookieService.findCookie(httpServletRequest);
        if (cookie == null) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}
