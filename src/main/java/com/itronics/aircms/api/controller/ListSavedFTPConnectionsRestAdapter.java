package com.itronics.aircms.api.controller;

import com.itronics.aircms.domain.FTPConnectionCredentials;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface ListSavedFTPConnectionsRestAdapter {
    @RequestMapping(method = RequestMethod.GET, value = "/api/ftp/listSavedFTPConnections")
    public ResponseEntity<List<FTPConnectionCredentials>> listSavedCredentials();
}
