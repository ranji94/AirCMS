package com.itronics.aircms.rest;

import com.itronics.aircms.api.controller.UploadFTPFileRestAdapter;
import com.itronics.aircms.bizz.FTPOperationsService;
import com.itronics.aircms.common.FtpClient;
import com.itronics.aircms.domain.News;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "uploadFTP", tags = {"uploadFTP"})
public class UploadFTPFileRestAdapterImpl implements UploadFTPFileRestAdapter {
    @Autowired
    FTPOperationsService ftpOperationsService;

    @Override
    public ResponseEntity<List<News>> uploadFTPFile(List<News> newsList) throws IOException {
        FtpClient ftpClient = FtpClient.getInstance();
        HttpHeaders headers = new HttpHeaders();

        if (ftpClient == null) {
            headers.add("FTP-Client-Connected", "0");
            return new ResponseEntity<>(new ArrayList<>(), headers, HttpStatus.BAD_REQUEST);
        }
        else {
            if(ftpClient.getRemoteAddress() == null) {
                headers.add("FTP-Client-Connected", "0");
                return new ResponseEntity<>(new ArrayList<>(), headers, HttpStatus.BAD_REQUEST);
            }

            headers.add("FTP-Client-Connected", "1");

            return new ResponseEntity<>(ftpOperationsService.uploadNewsFeed(newsList), headers, HttpStatus.OK);
        }
    }
}
