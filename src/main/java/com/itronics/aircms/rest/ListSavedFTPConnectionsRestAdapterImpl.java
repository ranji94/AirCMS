package com.itronics.aircms.rest;

import com.itronics.aircms.api.controller.ListSavedFTPConnectionsRestAdapter;
import com.itronics.aircms.bizz.FTPOperationsService;
import com.itronics.aircms.domain.FTPConnectionCredentials;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@Api(value = "listSavedFTPCredentials", tags = {"listSavedFTPCredentials"})
public class ListSavedFTPConnectionsRestAdapterImpl implements ListSavedFTPConnectionsRestAdapter {
    public static final Logger logger = Logger.getLogger(ListSavedFTPConnectionsRestAdapterImpl.class.getName());
    @Autowired
    FTPOperationsService ftpOperationsService;

    @Override
    public ResponseEntity<List<FTPConnectionCredentials>> listSavedCredentials() {
        logger.info("[ListSavedFTPConnections] Request sent");
        return new ResponseEntity<>(ftpOperationsService.getAllSavedConnections(), HttpStatus.OK);
    }
}
