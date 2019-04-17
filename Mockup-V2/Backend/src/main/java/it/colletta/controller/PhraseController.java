package it.colletta.controller;

import it.colletta.service.PhraseService;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/phrases")
public class PhraseController {

  @Autowired private PhraseService phraseService;

  @RequestMapping(method = RequestMethod.GET, value = "/downloadAll")
  public void downloadAllPhrases(HttpServletResponse response)  {
    try
    {
      File file = phraseService.downloadAllPhrases();
      response.setContentType("application/json");
      response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
      OutputStream out = response.getOutputStream();
      FileInputStream in = new FileInputStream(file);
      IOUtils.copy(in,out);
      file.delete();
    }
    catch (Exception e)
    {
    }
  }
}
