package com.itronics.aircms.rest;

import com.itronics.aircms.api.controller.DownloadFTPImagesInNewsRestAdapter;
import com.itronics.aircms.bizz.FTPOperationsService;
import com.itronics.aircms.domain.FTPDownloadStatus;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@Api(value = "downloadFTPImagesInNews", tags = {"downloadFTPImagesInNews"})
public class DownloadFTPImagesInNewsRestAdapterImpl implements DownloadFTPImagesInNewsRestAdapter {
    @Autowired
    FTPOperationsService ftpOperationsService;

    @Override
    public ResponseEntity<List<FTPDownloadStatus>> getFTPImagesInNews(String newsId) throws IOException {
        List<FTPDownloadStatus> filesStatuses = ftpOperationsService.downloadAllImagesInNewsDirectory(newsId);
        if(filesStatuses.isEmpty()) {
            return new ResponseEntity<>(filesStatuses, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(filesStatuses, HttpStatus.OK);
    }
}
