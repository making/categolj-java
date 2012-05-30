package am.ik.categolj.app.blog.web.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import am.ik.categolj.app.common.domain.Entry;
import am.ik.categolj.app.common.service.EntryService;

@Controller
public class FeedController {
    @Inject
    protected EntryService entryService;

    @RequestMapping({ "/feed", "/rss" })
    public String feed(Model model) {
        List<Entry> entries = entryService.getEntriesByPage(1, 10);
        model.addAttribute(entries);
        return "rssEntryFeedView";
    }
}
