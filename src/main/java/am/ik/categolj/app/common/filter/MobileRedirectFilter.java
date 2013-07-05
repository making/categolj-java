package am.ik.categolj.app.common.filter;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.sf.amateras.functions.utils.StringUtils;

import org.springframework.util.AntPathMatcher;

import am.ik.yalf.logger.Logger;

public class MobileRedirectFilter implements Filter {
    static Logger LOGGER = Logger.getLogger(MobileRedirectFilter.class);

    private static final String REDIRECT_PATH_PREFIX_KEY = "redirectPathPrefix";
    private static final String EXCLUDE_PATH_PATTERNS_KEY = "excludePathPatterns";
    private static final String VIEW_PC_KEY_KEY = "viewPcKey";
    private static final String VIEW_MOBILE_KEY_KEY = "viewMobileKey";
    private static final String TARGET_AGENT_REGEX_KEY = "targetAgentRegex";

    protected String redirectPathPrefix = "/m/";
    protected String[] excludePathPatterns = {};
    protected final AntPathMatcher excludePathMatcher = new AntPathMatcher();
    protected String viewPcKey = "pc";
    protected String viewMobileKey = "mobile";
    protected String targetAgentRegex = "iPhone";
    protected Pattern targetAgentPattern;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String redirectTargetPrefix = filterConfig
                .getInitParameter(REDIRECT_PATH_PREFIX_KEY);
        if (redirectTargetPrefix != null) {
            this.redirectPathPrefix = redirectTargetPrefix;
        }
        String excludePathPatterns = filterConfig
                .getInitParameter(EXCLUDE_PATH_PATTERNS_KEY);
        if (excludePathPatterns != null) {
            this.excludePathPatterns = excludePathPatterns.split(",");
        }
        String viewPcKey = filterConfig.getInitParameter(VIEW_PC_KEY_KEY);
        if (viewPcKey != null) {
            this.viewPcKey = viewPcKey;
        }
        String viewMobileKey = filterConfig
                .getInitParameter(VIEW_MOBILE_KEY_KEY);
        if (viewMobileKey != null) {
            this.viewMobileKey = viewMobileKey;
        }
        String targetAgentRegex = filterConfig
                .getInitParameter(TARGET_AGENT_REGEX_KEY);
        if (targetAgentRegex != null) {
            this.targetAgentRegex = targetAgentRegex;
        }
        this.targetAgentPattern = Pattern.compile(this.targetAgentRegex);
    }

    protected boolean isTargetAgent(String userAgent) {
        Matcher m = targetAgentPattern.matcher(userAgent);
        return m.find();
    }

    protected boolean isTargetPath(String path) {
        if (path.startsWith(redirectPathPrefix)) {
            return false;
        }
        for (String excludePathPattern : excludePathPatterns) {
            if (excludePathMatcher.match(excludePathPattern, path)) {
                return false;
            }
        }
        return true;
    }

    protected String getRedirectPath(HttpServletRequest request) {
        String redirect = "";
        String path = request.getRequestURI();
        if (path.length() == 0) {
            redirect = redirectPathPrefix;
        } else {
            redirect = redirectPathPrefix.substring(0,
                    redirectPathPrefix.length() - 1)
                    + path;
        }
        StringBuilder sb = new StringBuilder(redirect);
        @SuppressWarnings("unchecked")
        Map<String, Object> params = request.getParameterMap();
        if (!params.isEmpty()) {
            sb.append("&");
            sb.append(StringUtils.map2queryString(params));
        }
        return redirect;
    }

    public static boolean getBooleanValue(HttpServletRequest req, String key) {
        HttpSession session = req.getSession();
        boolean value = false;
        if (session.getAttribute(key) instanceof Boolean) {
            value = (Boolean) session.getAttribute(key);
        }
        if (req.getParameter(key) != null) {
            value = Boolean.parseBoolean(req.getParameter(key));
        }
        if (value) {
            session.setAttribute(key, value);
        } else {
            session.removeAttribute(key);
        }
        return value;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String userAgent = req.getHeader("User-Agent");
        String path = req.getRequestURI();
        boolean viewMobile = getBooleanValue(req, viewMobileKey);
        if ((viewMobile || isTargetAgent(userAgent)) && isTargetPath(path)) {
            boolean viewPc = getBooleanValue(req, viewPcKey);
            HttpSession session = req.getSession();
            if (viewPc) {
                session.removeAttribute(viewMobileKey);
            }
            if (viewMobile) {
                session.removeAttribute(viewPcKey);
            }
            if (!viewPc) {
                String redirect = getRedirectPath(req);
                LOGGER.debug(false, "[ICTGLXX2] User-Agent={0}", userAgent);
                LOGGER.debug(false, "[ICTGLXX3] redirect={0}", redirect);
                res.sendRedirect(redirect);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
