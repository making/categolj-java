package am.ik.categolj.controller;

import java.util.List;
import java.util.Properties;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import am.ik.categolj.entity.Entry;
import am.ik.categolj.service.EntryService;
import am.ik.categolj.util.PropertyUtils;
import am.ik.support.jqgrid.JqGridRequest;
import am.ik.support.jqgrid.JqGridResponse;
import am.ik.support.jqgrid.JqGridResponseBuilder;
import am.ik.yalf.logger.Logger;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
    private static final Logger LOGGER = Logger
            .getLogger(AdminController.class);

    @Inject
    protected EntryService entryService;

    @RequestMapping({ "/index" })
    public void index(Model model) {
        Properties config = PropertyUtils.loadProperties();
        model.addAttribute("config", config);
    }

    @RequestMapping("/entry/list")
    @ResponseBody
    public JqGridResponse<Entry> entryList(JqGridRequest req,
            @RequestParam("_search") boolean isSearch) {
        LOGGER.debug(false, "req={0} isSearch={1}", req, isSearch);
        int records = entryService.getTotalEntryCount();
        int total = (int) Math.ceil((double) records / req.getRows());
        List<Entry> entries = entryService.getEntriesForGrid(req.getPage(),
                req.getRows(), req.getSidx(), req.getSord());
        return new JqGridResponseBuilder<Entry>().page(req.getPage())
                .records(records).total(total).addAll(entries).build();
    }
}
