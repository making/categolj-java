package am.ik.categolj.controller;

import java.util.Properties;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import am.ik.categolj.service.EntryService;
import am.ik.categolj.util.PropertyUtils;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
    @Inject
    protected EntryService entryService;

    @RequestMapping({ "/index" })
    public void index(Model model) {
        Properties config = PropertyUtils.loadProperties();
        model.addAttribute("config", config);
    }
}
