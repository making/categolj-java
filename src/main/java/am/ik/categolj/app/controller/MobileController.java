package am.ik.categolj.app.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import am.ik.categolj.app.domain.Entry;
import am.ik.categolj.app.service.EntryService;

@Controller
@RequestMapping("/m/")
public class MobileController {
    @Inject
    protected EntryService entryService;

    @RequestMapping("*")
    public String index(Model model) {
        List<Entry> entries = entryService.getEntriesForGrid(1, 10,
                "updated-at", "desc");
        model.addAttribute(entries);
        return "mobile/index";
    }

    @RequestMapping("entry/view/id/{id}/**")
    public String view(@PathVariable Long id, Model model) {
        Entry entry = entryService.getEntryById(id);
        model.addAttribute(entry);
        return "mobile/entry";
    }
}
