package am.ik.categolj.app.blog.web.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import am.ik.categolj.app.admin.service.UploaderService;
import am.ik.categolj.app.admin.service.uploader.UploadResponse;
import am.ik.categolj.common.fw.consts.LogId;
import am.ik.yalf.logger.Logger;

@Controller
@RequestMapping("/upload")
public class UploadController {
    private final static Logger logger = Logger
            .getLogger(UploadController.class);

    @Inject
    protected UploaderService uploaderService;

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @ResponseBody
    public UploadResponse post(@RequestParam MultipartFile file) {
        return uploaderService.upload(file);
    }

    @RequestMapping("/view/{page}/{count}")
    @ResponseBody
    public UploadResponse view(@PathVariable int page, @PathVariable int count) {
        logger.debug(LogId.DCTGL005, page, count);
        return uploaderService.getUploadedFilesByPage(page, count);
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public UploadResponse delete(@PathVariable int id) {
        return uploaderService.deleteUplodedFileById(id);
    }
}
