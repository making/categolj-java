package am.ik.categolj.app.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import am.ik.categolj.app.domain.Entry;
import am.ik.categolj.app.service.EntryService;

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
