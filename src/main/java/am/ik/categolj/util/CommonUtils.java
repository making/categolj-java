package am.ik.categolj.util;

import am.ik.categolj.common.Const;

public final class CommonUtils {
    public static int calcTotalPage(int totalCount) {
        int totalPage = (int) Math.ceil((totalCount + 0.0) / Const.VIEW_COUNT);
        return totalPage;
    }

    public static int calcOffset(int page) {
        int offset = (page - 1) * Const.VIEW_COUNT;
        return offset;
    }

    public static String preAppendIfNotStartsWithSlash(String s) {
        if (!s.startsWith("/")) {
            return "/" + s;
        } else {
            return s;
        }
    }

    public static void postAppendIfNotEndsWithSlash(StringBuilder sb) {
        if (!sb.toString().endsWith("/")) {
            sb.append("/");
        }
    }

    public static String postAppendIfNotEndsWithSlash(String s) {
        if (!s.endsWith("/")) {
            return s + "/";
        } else {
            return s;
        }
    }
}
