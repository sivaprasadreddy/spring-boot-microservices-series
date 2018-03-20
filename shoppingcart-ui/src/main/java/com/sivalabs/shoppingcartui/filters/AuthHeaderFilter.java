package com.sivalabs.shoppingcartui.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

import java.util.UUID;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

public class AuthHeaderFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //RequestContext ctx = RequestContext.getCurrentContext();

        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        if (request.getAttribute("AUTH_HEADER") == null) {
            //generate or get AUTH_TOKEN, ex from Spring Session repository
            String sessionId = UUID.randomUUID().toString();
            //request.setAttribute("AUTH_HEADER", sessionId);
            ctx.addZuulRequestHeader("AUTH_HEADER", sessionId);
        }
        return null;
    }
}
