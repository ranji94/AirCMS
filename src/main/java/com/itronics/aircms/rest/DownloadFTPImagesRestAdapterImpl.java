package com.itronics.aircms.rest;

import com.itronics.aircms.api.controller.DownloadFTPImagesRestAdapter;
import com.itronics.aircms.bizz.FTPOperationsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Logger;

@RestController
@Api(value = "downloadFTPImage", tags = {"downloadFTPImage"})
public class DownloadFTPImagesRestAdapterImpl implements DownloadFTPImagesRestAdapter {
    public static final Logger logger = Logger.getLogger(DownloadFTPImagesRestAdapterImpl.class.getName());

    @Autowired
    FTPOperationsService ftpOperationsService;

    @Override
    public HttpEntity<byte[]> getFTPImage(String fileName, String newsId) throws IOException {
        logger.info(String.format("REQUESTED FOR IMAGE: %s, newsID: %s", fileName, newsId));
        return ftpOperationsService.downloadImage(fileName, newsId);
    }
}
