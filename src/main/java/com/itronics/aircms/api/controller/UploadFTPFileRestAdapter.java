package com.itronics.aircms.api.controller;

import com.itronics.aircms.domain.News;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;

public interface UploadFTPFileRestAdapter {
    @RequestMapping(method = RequestMethod.POST, value = "/api/ftp/upload")
    public ResponseEntity<List<News>> uploadFTPFile(@RequestBody List<News> news) throws IOException;
}
