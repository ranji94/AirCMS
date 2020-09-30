package com.itronics.aircms.rest;

import com.itronics.aircms.api.controller.FTPConnectionRestAdapter;
import com.itronics.aircms.common.FtpClient;
import com.itronics.aircms.domain.FTPConnectionCredentials;
import com.itronics.aircms.domain.FTPConnectionStatus;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Logger;

@RestController
@Api(value = "connectFTP", tags = {"connectFTP"})
public class FTPConnectionRestAdapterImpl implements FTPConnectionRestAdapter {
    public static final Logger logger = Logger.getLogger(FTPConnectionRestAdapterImpl.class.getName());

    @Override
    public ResponseEntity<FTPConnectionStatus> connect(FTPConnectionCredentials credentials) {
        FtpClient ftpClient = FtpClient.getInstance();

        logger.info(String.format("[ConnectFTP] FTP connection open requested for credentials: %s", credentials));

        ftpClient.setCredentials(credentials);

        try {
            return new ResponseEntity<>(ftpClient.open(), HttpStatus.OK);
        } catch(IOException e) {
            logger.info("[ConnectFTPController] FTP Connection Failed");

            FTPConnectionStatus status = new FTPConnectionStatus();
            status.setConnected(false);

            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
