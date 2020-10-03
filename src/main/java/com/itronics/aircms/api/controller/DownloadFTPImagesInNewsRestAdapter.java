package com.itronics.aircms.api.controller;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

public interface DownloadFTPImagesInNewsRestAdapter {
    @RequestMapping(method = RequestMethod.GET, value = "/api/ftp/download/imagesDir")
    @ResponseBody
    public List<HttpEntity<byte[]>> getFTPImagesInNews(@RequestParam("newsId") String newsId) throws IOException;
}
