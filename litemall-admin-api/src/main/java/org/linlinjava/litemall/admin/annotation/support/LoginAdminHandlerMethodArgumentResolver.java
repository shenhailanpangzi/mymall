package org.linlinjava.litemall.admin.annotation.support;

import org.linlinjava.litemall.admin.annotation.LoginAdmin;
import org.linlinjava.litemall.admin.service.AdminTokenManager;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

//自定参数解析器
public class LoginAdminHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    public static final String LOGIN_TOKEN_KEY = "X-Litemall-Admin-Token";

//    判断是否支持要转换的参数类型
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Integer.class) && parameter.hasParameterAnnotation(LoginAdmin.class);
    }
//    当支持后进行相应的转换
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {

//        return new Integer(1);
        String token = request.getHeader(LOGIN_TOKEN_KEY);
        if (null == token || token.isEmpty()) {
            return null;
        }

        return AdminTokenManager.getUserId(token);
    }
}
