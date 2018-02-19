package com.jokers.common.context;


import com.jokers.pojo.enums.ContextParamDictionary;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

/**
 * @description : 路径工厂类
 * @since 2011-6-2 下午04:39:46
 */
public class BasePathFactory {

    /**
     * @return String
     * @description 获取网站路径
     */
    public static String getBasePath(HttpServletRequest request) {
        return getWebRootPath(request);
    }

    /**
     * @return String
     * @description ProjectRootPath
     */
    public static String getProjectRootPath() {
        return System.getProperty(ContextParamDictionary.PROJECT_PATH.getParamValue());
    }

    /**
     * @return String
     * @author yuTong
     * @since 2015-8-17 下午02:27:46
     */
    public static String getBaseFilePath() {
        String path = BasePathFactory.class.getProtectionDomain()
                .getCodeSource().getLocation().getPath();
        if (path.indexOf("WEB-INF") > 0) {
            path = path.substring(0, path.indexOf("WEB-INF/classes"));
        } else {
            return null;
        }
        return path;
    }

    /**
     * @return String
     * @description 获得classpath(........../WebRoot/WEB-INF/classes/)
     */
    public static String getClassPath() {
        return BasePathFactory.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    }

    /**
     * @param request HttpServletRequest
     * @return String
     * @description 获取URL请求路径
     */
    public static String getWebRootPath(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath() + "/";
    }

    /**
     * basePath路径
     *
     * @param request HttpServletRequest
     * @return String
     */
    public static String getServerPath(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/");
    }

    /**
     * @param resourceName String
     * @return String
     * @description 获取资源文件路径
     */
    public static String getResourcePath(String resourceName) {
        return Objects.requireNonNull(BasePathFactory.class.getClassLoader().getResource(resourceName)).getPath();
    }
}
