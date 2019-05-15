package cn.herculas.recruit.gateway.filter.role;

import cn.herculas.recruit.gateway.enumeration.uri.CommonUriEnum;
import cn.herculas.recruit.gateway.enumeration.role.RoleEnum;
import cn.herculas.recruit.gateway.service.CookieService;
import cn.herculas.recruit.gateway.service.SessionService;
import cn.herculas.recruit.gateway.util.authenticator.RoleAuthenticator;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Component
public class AllRoleFilter extends ZuulFilter {

    private final CookieService cookieService;
    private final SessionService sessionService;

    public AllRoleFilter(CookieService cookieService, SessionService sessionService) {
        this.cookieService = cookieService;
        this.sessionService = sessionService;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 3;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();
        return CommonUriEnum.contains(httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
    }

    @Override
    public Object run() {
        List<Integer> roleList = Arrays.asList(
                RoleEnum.STUDENT.getCode(),
                RoleEnum.TEACHER_ADMINISTRATOR.getCode(),
                RoleEnum.TEACHER_ADMISSION_TEAM_LEADER.getCode(),
                RoleEnum.TEACHER_ADMISSION_TEAM_MEMBER.getCode());
        return RoleAuthenticator.roleAuthenticator(this.cookieService, this.sessionService, roleList);
    }
}
