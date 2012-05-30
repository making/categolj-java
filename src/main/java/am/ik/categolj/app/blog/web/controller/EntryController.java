package am.ik.categolj.app.blog.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import am.ik.categolj.app.common.domain.Entry;
import am.ik.categolj.app.common.service.EntryService;
import am.ik.categolj.common.fw.consts.LogId;
import am.ik.categolj.common.fw.util.BindingResultUtils;
import am.ik.categolj.common.fw.util.CategoryEditor;
import am.ik.support.jqgrid.JqGridRequest;
import am.ik.support.jqgrid.JqGridResponse;
import am.ik.support.jqgrid.JqGridResponseBuilder;
import am.ik.yalf.logger.Logger;

@Controller
@RequestMapping("/entry/*")
public class EntryController {
    private static final Logger logger = Logger
            .getLogger(EntryController.class);

    private static final String FORM_VIEW = "entry/form";

    @Inject
    protected EntryService entryService;

    @InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, true);
        CategoryEditor categoryEditor = new CategoryEditor();
        binder.registerCustomEditor(Date.class, "createdAt", dateEditor);
        binder.registerCustomEditor(Date.class, "updatedAt", dateEditor);
        binder.registerCustomEditor(List.class, "category", categoryEditor);
    }

    @RequestMapping("/view/id/{id}/**")
    public String view(@PathVariable Long id, Model model) {
        Entry entry = entryService.getEntryById(id);
        model.addAttribute(entry);
        return "entry/view";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Entry entry) {
        Date today = new Date();
        entry.setCreatedAt(today);
        entry.setUpdatedAt(today);
        return FORM_VIEW;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createOnSubmit(@Valid Entry entry,
            BindingResult bindingResult, Model model) {
        logger.debug(LogId.DCTGL002, entry);

        if (!BindingResultUtils.addModelIfErrorsExist(bindingResult, model)) {
            return FORM_VIEW;
        }

        entryService.insertEntry(entry);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit/id/{id}/**", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        Entry entry = entryService.getEntryById(id);
        model.addAttribute(entry);
        return FORM_VIEW;
    }

    @RequestMapping(value = "/edit/**", method = RequestMethod.POST)
    public String editOnSubmit(@Valid Entry entry, BindingResult bindingResult,
            Model model) {
        logger.debug(LogId.DCTGL003, entry);
        if (!BindingResultUtils.addModelIfErrorsExist(bindingResult, model)) {
            return FORM_VIEW;
        }
        if (entry.isUpdateDate()) {
            entry.setUpdatedAt(new Date());
        }
        entryService.updateEntry(entry);
        return "redirect:/entry/edit/id/" + entry.getId();
    }

    @RequestMapping(value = "/delete/id/{id}/**", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, Model model) {
        Entry entry = entryService.getEntryById(id);
        model.addAttribute(entry);
        return "entry/delete";
    }

    @RequestMapping(value = "/delete/**", method = RequestMethod.POST)
    public String deleteOnSubmit(@Valid Entry entry,
            BindingResult bindingResult, Model model) {
        logger.debug(LogId.DCTGL004, entry);
        entryService.deleteEntry(entry);
        return "redirect:/";
    }

    @RequestMapping("/json")
    @ResponseBody
    public JqGridResponse<Entry> entryList(JqGridRequest req,
            @RequestParam("_search") boolean isSearch) {
        logger.debug(false, "req={0} isSearch={1}", req, isSearch);
        int records = entryService.getTotalEntryCount();
        int total = (int) Math.ceil((double) records / req.getRows());
        List<Entry> entries = entryService.getEntriesForGrid(req.getPage(),
                req.getRows(), req.getSidx(), req.getSord());
        return new JqGridResponseBuilder<Entry>().page(req.getPage())
                .records(records).total(total).addAll(entries).build();
    }
}
