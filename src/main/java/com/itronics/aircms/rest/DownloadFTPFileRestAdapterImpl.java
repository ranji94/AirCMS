package com.itronics.aircms.rest;

import com.itronics.aircms.api.controller.DownloadFTPFileRestAdapter;
import com.itronics.aircms.bizz.FTPOperationsService;
import com.itronics.aircms.domain.News;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@Api(value = "downloadFTP", tags = {"downloadFTP"})
public class DownloadFTPFileRestAdapterImpl implements DownloadFTPFileRestAdapter {
    public static final Logger logger = Logger.getLogger(DownloadFTPFileRestAdapterImpl.class.getName());

    @Autowired
    FTPOperationsService ftpOperationsService;

    public DownloadFTPFileRestAdapterImpl(FTPOperationsService ftpOperationsService) {
        this.ftpOperationsService = ftpOperationsService;
    }

    @Override
    public List<News> downloadFTPFile() throws IOException {
        return ftpOperationsService.downloadNewsFeed();
    }
}
