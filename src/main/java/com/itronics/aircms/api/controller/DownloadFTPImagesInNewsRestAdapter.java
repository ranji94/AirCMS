package com.itronics.aircms.api.controller;

import com.itronics.aircms.domain.FTPDownloadStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

public interface DownloadFTPImagesInNewsRestAdapter {
    @RequestMapping(method = RequestMethod.GET, value = "/api/ftp/download/imgDirInfo")
    @ResponseBody
    public ResponseEntity<List<FTPDownloadStatus>> getFTPImagesInNews(@RequestParam("newsId") String newsId) throws IOException;
}
