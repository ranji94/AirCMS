package com.itronics.aircms.rest;

import com.itronics.aircms.api.controller.FTPConnectionRestAdapter;
import com.itronics.aircms.domain.FTPConnectionCredentials;
import com.itronics.aircms.common.FtpClient;
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
    public ResponseEntity<String> connect(String user, String password, int port, String server) {
        FtpClient ftpClient = FtpClient.getInstance();

        FTPConnectionCredentials credentials = new FTPConnectionCredentials(server,
                port,
                user,
                password);

        logger.info(String.format("[ConnectFTP] FTP connection open requested for credentials: %s", credentials));

        ftpClient.setCredentials(credentials);

        try {
            return new ResponseEntity<>(ftpClient.open(), HttpStatus.OK);
        } catch(IOException e) {
            logger.info("[ConnectFTPController] FTP Connection Failed");
            return new ResponseEntity<>("FTP Connection Failed. Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
