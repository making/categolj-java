package am.ik.categolj.app.entry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import am.ik.categolj.domain.common.consts.LogId;
import am.ik.categolj.domain.common.util.CategoryEditor;
import am.ik.categolj.domain.model.Entry;
import am.ik.categolj.domain.service.entry.EntryService;
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

    @ModelAttribute
    public EntryForm setUpForm(Model model) {
        Date today = new Date();
        EntryForm form = new EntryForm();
        form.setCreatedAt(today);
        form.setUpdatedAt(today);
        return form;
    }

    public static Entry fromForm(EntryForm form) {
        Entry entry = new Entry();
        BeanUtils.copyProperties(form, entry);
        return entry;
    }

    public static EntryForm toForm(Entry entry) {
        EntryForm form = new EntryForm();
        BeanUtils.copyProperties(entry, form);
        return form;
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

    @RequestMapping("/view/id/{id}/**")
    public String show(@PathVariable Long id, Model model) {
        Entry entry = entryService.getEntryById(id);
        model.addAttribute(entry);
        return "entry/view";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid EntryForm form, BindingResult bindingResult,
            Model model, HttpServletRequest req,
            RedirectAttributes redirectAttributes) {
        logger.debug(LogId.DCTGL002, form);
        if (bindingResult.hasErrors()) {

            return FORM_VIEW;
        }
        Entry entry = fromForm(form);
        entryService.insertEntry(entry);
        return "redirect:/";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createForm(EntryForm form, Model model) {
        return FORM_VIEW;
    }

    @RequestMapping(value = "/edit/**", method = RequestMethod.POST)
    public String update(@Valid EntryForm form, BindingResult bindingResult,
            Model model, HttpServletRequest req,
            RedirectAttributes redirectAttributes) {
        logger.debug(LogId.DCTGL003, form);
        if (bindingResult.hasErrors()) {
            return FORM_VIEW;
        }
        if (form.isUpdateDate()) {
            form.setUpdatedAt(new Date());
        }
        Entry entry = fromForm(form);
        entryService.updateEntry(entry);
        return "redirect:/entry/edit/id/" + entry.getId();
    }

    @RequestMapping(value = "/edit/id/{id}/**", method = RequestMethod.GET)
    public String updateForm(@PathVariable Long id, Model model) {
        Entry entry = entryService.getEntryById(id);
        EntryForm form = toForm(entry);
        model.addAttribute(form);
        return FORM_VIEW;
    }

    @RequestMapping(value = "/delete/**", method = RequestMethod.POST)
    public String delete(@Valid EntryForm form, BindingResult bindingResult,
            Model model, HttpServletRequest req,
            RedirectAttributes redirectAttributes) {
        logger.debug(LogId.DCTGL004, form);
        Entry entry = fromForm(form);
        entryService.deleteEntry(entry);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/id/{id}/**", method = RequestMethod.GET)
    public String deleteForm(@PathVariable Long id, Model model) {
        Entry entry = entryService.getEntryById(id);
        model.addAttribute(entry);
        return "entry/delete";
    }
}
