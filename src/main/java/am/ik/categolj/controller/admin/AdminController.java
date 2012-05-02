package am.ik.categolj.controller.admin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import am.ik.categolj.entity.Link;
import am.ik.categolj.service.EntryService;
import am.ik.categolj.service.LinkService;
import am.ik.categolj.util.PropertyUtils;
import am.ik.yalf.logger.Logger;

@Controller
@RequestMapping("/admin/**")
public class AdminController {
    private static final Logger LOGGER = Logger
            .getLogger(AdminController.class);

    @Inject
    protected EntryService entryService;
    @Inject
    protected LinkService linkService;

    @RequestMapping({ "/index" })
    public void index(Model model) {
        Properties config = PropertyUtils.loadProperties();
        model.addAttribute("config", config);
    }

    @RequestMapping({ "/link/save" })
    public @ResponseBody
    Map<String, ?> linkSave(@Valid Link link, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<String, Object>();
        LOGGER.debug(false, "bindingResult={0}", bindingResult);
        LOGGER.debug(false, "save={0}", link);
        if (bindingResult.hasErrors()) {
            response.put("result", "NG");
            response.put("error", bindingResult.getFieldErrors());
        } else {
            linkService.save(link);
            response.put("result", "OK");
            response.put("id", link.getId());
        }
        return response;
    }

    @RequestMapping({ "/link/delete" })
    public @ResponseBody
    Map<String, ?> linkDelete(@Valid Link link) {
        LOGGER.debug(false, "delete={0}", link);
        if (link.getId() != null) {
            linkService.delete(link.getId());
        }
        return Collections.singletonMap("result", "OK");
    }
}
