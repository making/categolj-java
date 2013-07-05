package am.ik.categolj.domain.common.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import am.ik.categolj.app.common.consts.Const;

public final class CommonUtils {
    public static int calcTotalPage(int totalCount, int viewCount) {
        int totalPage = (int) Math.ceil((totalCount + 0.0) / viewCount);
        return totalPage;
    }

    public static int calcTotalPage(int totalCount) {
        return calcTotalPage(totalCount, Const.VIEW_COUNT);
    }

    public static int calcOffset(int page, int viewCount) {
        int offset = (page - 1) * viewCount;
        return offset;
    }

    public static int calcOffset(int page) {
        return calcOffset(page, Const.VIEW_COUNT);
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
