package com.devcamp.tokofable.component;

import com.devcamp.tokofable.config.AuthContext;
import com.devcamp.tokofable.config.AuthModel;
import com.devcamp.tokofable.security.Security;
import com.google.gson.Gson;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class GeneralInterceptor extends HandlerInterceptorAdapter {

    private AuthModel authModel;

    @Value("${erp.salt}")
    private String SALT;

    // http://www.baeldung.com/spring-mvc-handlerinterceptor
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        authModel = null;
        if (request.getMethod().equals("OPTIONS"))
            return allow(response, null);
        String token = request.getHeader("token");
        if (token != null) {
            try {
                AuthModel model = Security.getModel(token, SALT);
                if (model != null) {
                    return allow(response, token);
                } else
                    return forbidden(response, "Invalid token");

            } catch (MalformedJwtException e) {
                return forbidden(response, "INVALID token");
            }
        } else return forbidden(response, "Token NULL");

    }

    private boolean setAuthModel(String token, HttpServletResponse response) {
        try {
            AuthModel authModel = Security.getModel(token, SALT);
            this.authModel = authModel;
            AuthContext.setCurrentRequest(authModel);
            return true;
        } catch (ExpiredJwtException e) {
            return forbidden(response, "EXPIRED token");
        } catch (MalformedJwtException e) {
            return forbidden(response, "INVALID token");
        }
    }

    private boolean forbidden(HttpServletResponse response, String message) {
        Map map = new HashMap();
        map.put("message", message);
        Gson gson = new Gson();
        try {
            response.getWriter().write(gson.toJson(map));
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setHeader("Access-Control-Allow-Headers", "*");
            response.setContentType("application/json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean allow(HttpServletResponse response, String token) {
        if (token != null) {
            response.setHeader("token", Security.recreateToken(token, SALT));
        }
        if (authModel == null && token != null) {
            setAuthModel(token, response);
        }
        return true;
    }

}

