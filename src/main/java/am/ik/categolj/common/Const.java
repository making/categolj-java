package am.ik.categolj.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Const {
    public static final String CATEGORY_PATH = "category";
    public static final String PAGE_PATH = "page";
    public static final String SEARCH_PATH = "search";
    public static final String TITLE_KEY = "categolj.title";
    public static final String CONTEXT_ROOT_KEY = "categolj.context.root";
    public static final String CATEGORY_DELIM = "::";
    public static final String LOGIN_USER_ATTR = "loginUser";
    public static final String RECENT_ENTRIES_ATTR = "recentEntries";
    public static final String PAGER_ATTR = "pagerLink";
    public static final String CATEGORY_LINK_ATTR = "categoryLink";
    public static final String CATEGORY_LINK_SET_ATTR = "categoryLinkSet";
    public static final int START_PAGE = 1;
    public static final int VIEW_COUNT = 3;
    public static final int RECENT_COUNT = 5;
    public static final String ROLE_USER = "ROLE_USER";

    static {
        Properties p = new Properties();
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream s = cl.getResourceAsStream("categolj.properties");
        try {
            p.load(s);
        } catch (IOException ignored) {
        }
    }
}
