package com.itronics.aircms.rest;

import com.itronics.aircms.model.FTPConnectionCredentials;
import com.itronics.aircms.utils.FtpClient;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.logging.Logger;

@RestController
public class ConnectFTPController {
    public static final Logger logger = Logger.getLogger(ConnectFTPController.class.getName());

    @RequestMapping(method = RequestMethod.GET, value = "/api/ftp/connect")
    public String connect(@RequestParam("user") String user,
                          @RequestParam("password") String password,
                          @RequestParam("port") int port,
                          @RequestParam("server") String server) {
        FtpClient ftpClient = FtpClient.getInstance();

        FTPConnectionCredentials credentials = new FTPConnectionCredentials();
        credentials.setUser(user);
        credentials.setPassword(password);
        credentials.setPort(port);
        credentials.setServer(server);

        logger.info(String.format("[ConnectFTP] FTP connection open requested for credentials: %s", credentials));

        ftpClient.setCredentials(credentials);

        try {
            ftpClient.open();
        } catch(IOException e) {
            logger.info("[ConnectFTPController] FTP Connection Failed");
        }

        return "Connection requested";
    }
}
