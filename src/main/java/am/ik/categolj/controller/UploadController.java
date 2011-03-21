package am.ik.categolj.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import am.ik.categolj.service.UploaderService;
import am.ik.categolj.service.uploader.UploadResponse;

@Controller
@RequestMapping("/upload")
public class UploadController {
    private final static Logger logger = LoggerFactory
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
        logger.debug("page = {}, count = {}", new Object[] { page, count });
        return uploaderService.getUploadedFilesByPage(page, count);
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public UploadResponse delete(@PathVariable int id) {
        return uploaderService.deleteUplodedFileById(id);
    }
}
