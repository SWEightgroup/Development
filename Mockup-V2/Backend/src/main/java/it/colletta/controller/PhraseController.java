package it.colletta.controller;

import it.colletta.model.helper.FilterHelper;
import it.colletta.service.PhraseService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("/phrases")
public class PhraseController {

    @Autowired
    private PhraseService phraseService;

    @RequestMapping(method = RequestMethod.GET, value = "/downloadAll")
    public void downloadAllPhrases(HttpServletResponse response) {
        try {
            File file = phraseService.downloadAllPhrases();
            response.setContentType("application/json");
            response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
            OutputStream out = response.getOutputStream();
            FileInputStream in = new FileInputStream(file);
            IOUtils.copy(in, out);
            final boolean delete = file.delete();
        } catch (Exception ignored) {
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/download")
    public void downloadPhrasesWithFilter(HttpServletResponse response, @RequestBody FilterHelper filterHelper) {
        try {
            File file = phraseService.downloadPhrasesWithFilter(filterHelper);
            response.setContentType("application/json");
            response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
            OutputStream out = response.getOutputStream();
            FileInputStream in = new FileInputStream(file);
            IOUtils.copy(in, out);
            final boolean delete = file.delete();
        } catch (Exception ignored) {
        }
    }
}
