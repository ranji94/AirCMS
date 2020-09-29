package com.itronics.aircms.api.controller;

import com.itronics.aircms.domain.News;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;

public interface DownloadFTPFileRestAdapter {
    @RequestMapping(method = RequestMethod.GET, value = "/api/ftp/download")
    public List<News> downloadFTPFile() throws IOException;
}
