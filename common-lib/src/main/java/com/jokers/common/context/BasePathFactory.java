package com.jokers.common.context;


import com.jokers.pojo.enums.ContextParamDictionary;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * : 路径工厂类
 *
 * @author yuton
 * @version $Id: $Id
 * @since 2011-6-2 下午04:39:46
 */
public class BasePathFactory {

    /**
     * <p>获取网站路径.</p>
     *
     * @param request HttpServletRequest
     * @return String
     */
    public static String getBasePath(HttpServletRequest request) {
        return getWebRootPath(request);
    }

    /**
     * <p>getProjectRootPath.</p>
     *
     * @return String
     */
    public static String getProjectRootPath() {
        return System.getProperty(ContextParamDictionary.PROJECT_PATH.getParamValue());
    }

    /**
     * <p>getBaseFilePath.</p>
     *
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
     * <p>获得classpath(........../WebRoot/WEB-INF/classes/).</p>
     *
     * @return String
     */
    public static String getClassPath() {
        return BasePathFactory.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    }

    /**
     * <p>获取URL请求路径.</p>
     *
     * @param request HttpServletRequest
     * @return String
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
     * <p>获取资源文件路径.</p>
     *
     * @param resourceName String
     * @return String
     */
    public static String getResourcePath(String resourceName) {
        return Objects.requireNonNull(BasePathFactory.class.getClassLoader().getResource(resourceName)).getPath();
    }
}
