package com.rest.adapter.utils;

import javax.ws.rs.Path;

public final class Utils {

    public static <T> boolean validateServiceInterface(Class<T>... face){
        for (Class<T> each : face) {
            Path path = each.getAnnotation(Path.class);
            if (path != null) return true;
        }
        return false;
    }


}
