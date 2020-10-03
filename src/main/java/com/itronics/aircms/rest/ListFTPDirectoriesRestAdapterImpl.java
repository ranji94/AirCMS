package com.itronics.aircms.rest;

import com.itronics.aircms.api.controller.ListFTPDirectoriesRestAdapter;
import com.itronics.aircms.common.FtpClient;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Logger;

@RestController
@Api(value = "listFTPDirectories", tags = {"listFTPDirectories"})
public class ListFTPDirectoriesRestAdapterImpl implements ListFTPDirectoriesRestAdapter {
    public static final Logger logger = Logger.getLogger(ListFTPDirectoriesRestAdapterImpl.class.getName());

    @Override
    public ResponseEntity<Collection<String>> listDirectories() {
        FtpClient ftpClient = FtpClient.getInstance();
        try {
            if(ftpClient.isConnected()) {
                return new ResponseEntity<>(ftpClient.listFilesCollection("/"), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(new ArrayList<>(Collections.singletonList("Please establish FTP connection, before listing directories.")), HttpStatus.FORBIDDEN);
            }
        } catch(IOException e) {
            logger.info("[ListDirectories] Error occured");

            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
