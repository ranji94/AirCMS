package com.itronics.aircms.rest;

import com.itronics.aircms.utils.FtpClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class ListFTPDirectoriesController {
    public static final Logger logger = Logger.getLogger(ListFTPDirectoriesController.class.getName());

    @RequestMapping(method = RequestMethod.GET, value = "/api/ftp/listDirectories")
    public Collection<String> listDirectories() {
        FtpClient ftpClient = FtpClient.getInstance();
        try {
            return ftpClient.listFiles("/");
        } catch(IOException e) {
            logger.info("[ListDirectories] Error occured");

            return new ArrayList<>();
        }
    }
}
