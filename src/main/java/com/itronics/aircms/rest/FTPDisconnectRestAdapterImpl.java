package com.itronics.aircms.rest;

import com.itronics.aircms.api.controller.FTPConnectionRestAdapter;
import com.itronics.aircms.api.controller.FTPDisconnectRestAdapter;
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
@Api(value = "disconnectFTP", tags = {"disconnectFTP"})
public class FTPDisconnectRestAdapterImpl implements FTPDisconnectRestAdapter {
    public static final Logger logger = Logger.getLogger(FTPDisconnectRestAdapterImpl.class.getName());

    @Override
    public ResponseEntity<FTPConnectionStatus> disconnect() {
        FtpClient ftpClient = FtpClient.getInstance();

        logger.info(String.format("[DisconnectFTP] FTP disconnected"));

        try {
            return new ResponseEntity<>(ftpClient.disconnect(), HttpStatus.OK);
        } catch(IOException e) {
            logger.info("[DisconnectFTP] FTP disconnect failed");

            FTPConnectionStatus status = new FTPConnectionStatus();
            status.setConnected(true);

            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
