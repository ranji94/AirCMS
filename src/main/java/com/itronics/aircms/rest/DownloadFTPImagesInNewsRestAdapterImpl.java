package com.itronics.aircms.rest;

import com.itronics.aircms.api.controller.DownloadFTPImagesInNewsRestAdapter;
import com.itronics.aircms.bizz.FTPOperationsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@Api(value = "downloadFTPImagesInNews", tags = {"downloadFTPImagesInNews"})
public class DownloadFTPImagesInNewsRestAdapterImpl implements DownloadFTPImagesInNewsRestAdapter {
    @Autowired
    FTPOperationsService ftpOperationsService;

    @Override
    public List<HttpEntity<byte[]>> getFTPImagesInNews(String newsId) throws IOException {
        return ftpOperationsService.downloadAllImagesInNewsDirectory(newsId);
    }
}
