package com.itronics.aircms.api.controller;

import com.itronics.aircms.domain.FTPConnectionCredentials;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface SaveConnectionCredentialsRestAdapter {
    @RequestMapping(method = RequestMethod.POST, value = "/api/ftp/saveCredentials")
    public ResponseEntity<String> saveCredentials(@RequestBody FTPConnectionCredentials ftpConnectionCredentials);
}
