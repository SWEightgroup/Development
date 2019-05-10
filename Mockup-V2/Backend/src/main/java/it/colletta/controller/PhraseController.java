package it.colletta.controller;

import it.colletta.model.helper.FilterHelper;
import it.colletta.service.PhraseService;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/phrases")
public class PhraseController {

  private PhraseService phraseService;

  @Autowired
  public PhraseController(PhraseService phraseService) {
    this.phraseService = phraseService;
  }


  /**
   * Download all phrases.
   * 
   * @param response Response
   */
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/downloadAll")
  public void downloadAllPhrases(HttpServletResponse response) {
    try {
      File file = phraseService.downloadAllPhrases();
      setResponse(response, file);
    } catch (Exception error) {
      error.printStackTrace();
    }
  }

  /**
   * Response.
   * 
   * @param response Response
   * @param file File
   * @throws IOException Exception
   */
  private void setResponse(HttpServletResponse response, File file) throws IOException {
    response.setContentType("application/json");
    response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
    OutputStream out = response.getOutputStream();
    FileInputStream in = new FileInputStream(file);
    IOUtils.copy(in, out);
    file.delete();
  }


  /**
   * Download phrases With Filter.
   * 
   * @param response Response
   * @param filterHelper Filters
   */
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/download")
  public void downloadPhrasesWithFilter(HttpServletResponse response,
      @RequestBody FilterHelper filterHelper) {
    try {
      File file = phraseService.downloadPhrasesWithFilter(filterHelper);
      setResponse(response, file);
    } catch (Exception ignored) {
      ignored.printStackTrace();
    }
  }
}
