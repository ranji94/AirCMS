package com.itronics.aircms.rest;

import com.itronics.aircms.api.controller.UploadFTPFileRestAdapter;
import com.itronics.aircms.bizz.FTPOperationsService;
import com.itronics.aircms.domain.News;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@Api(value = "uploadFTP", tags = {"uploadFTP"})
public class UploadFTPFileRestAdapterImpl implements UploadFTPFileRestAdapter {
    @Autowired
    FTPOperationsService ftpOperationsService;

    @Override
    public List<News> uploadFTPFile(List<News> newsList) throws IOException {
        return ftpOperationsService.uploadNewsFeed(newsList);
    }
}
