package com.devcamp.tokofable.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AuthContext {

    private static ThreadLocal<AuthModel> currentRequest = new ThreadLocal<>();

    public static void setCurrentRequest(AuthModel param) {
        currentRequest.set(param);
    }

    public static String getRequester() {
        AuthModel model = currentRequest.get();
        return model == null ? "" : model.getUserId();
    }

//    public static List<RoleEnum> getRoles() {
//        AuthModel model = currentRequest.get();
//        if (model == null)
//            return new ArrayList<>();
//        String[] roleNames = model.getRoles().split(",");
//        return Arrays.stream(roleNames)
//                .map(s -> RoleEnum.valueOf(s))
//                .collect(Collectors.toList());
//    }

//    public static boolean isRole(RoleEnum roleEnum) {
//        for (RoleEnum re : getRoles()) {
//            if (re.getValue() == roleEnum.getValue()) return true;
//        }
//        return false;
//    }

//    public static boolean isRole(List<RoleEnum> roleEnums) {
//        for (RoleEnum re : getRoles()) {
//            for (RoleEnum roleEnum : roleEnums) {
//                if (re.getValue() == roleEnum.getValue()) return true;
//            }
//        }
//        return false;
//    }

    public static void clear() {
        currentRequest.remove();
    }
}
