package am.ik.categolj.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static Set<String> createKeywordSet(List<String> keywords) {
        Set<String> kws = new HashSet<String>();
        for (String kw : keywords) {
            if (isValidKeyword(kw)) {
                kws.add(kw.toUpperCase());
            }
        }
        return kws;
    }

    public static boolean isValidKeyword(String keyword) {
        if (keyword == null || keyword.length() <= 1) {
            return false;
        }
        try {
            // 数字だけのものは飛ばす
            Double.parseDouble(keyword);
            return false;
        } catch (Exception ignored) {
        }
        return true;
    }
}