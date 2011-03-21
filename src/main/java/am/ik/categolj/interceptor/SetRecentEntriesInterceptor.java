package am.ik.categolj.interceptor;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import am.ik.categolj.common.Const;
import am.ik.categolj.entity.Entry;
import am.ik.categolj.service.EntryService;

public class SetRecentEntriesInterceptor extends HandlerInterceptorAdapter {
    @Inject
    protected EntryService entryService;

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            List<Entry> entries = entryService
                    .getEntriesOnlyIdTitle(Const.RECENT_COUNT);
            modelAndView.addObject(Const.RECENT_ENTRIES_ATTR, entries);
        }
    }
}
