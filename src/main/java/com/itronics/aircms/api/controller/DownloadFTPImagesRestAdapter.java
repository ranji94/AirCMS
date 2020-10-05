package com.itronics.aircms.api.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

public interface DownloadFTPImagesRestAdapter {
    @RequestMapping(method = RequestMethod.GET, value = "/api/ftp/download/image", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> getFTPImage(@RequestParam("fileName") String fileName, @RequestParam("newsId") String newsId) throws IOException;
}
