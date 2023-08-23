package com.nearnstyle.apis.common.util;

import java.util.ResourceBundle;

 
public class ConstantUtil {

    private ConstantUtil() {

    }

    private static final ResourceBundle serverPropertiesBundle = ResourceBundle.getBundle("application");


    public static final Integer SERVER_PORT =
            Integer.valueOf(serverPropertiesBundle.getString("server.port"));
    public static final String ALLOW_ORIGIN = serverPropertiesBundle.getString("allow.origin");

}
