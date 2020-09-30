package com.itronics.aircms.api.controller;

import com.itronics.aircms.domain.FTPConnectionCredentials;
import com.itronics.aircms.domain.FTPConnectionStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface FTPConnectionRestAdapter {
    @RequestMapping(method = RequestMethod.POST, value = "/api/ftp/connect")
    public ResponseEntity<FTPConnectionStatus> connect(@RequestBody FTPConnectionCredentials credentials);
}
