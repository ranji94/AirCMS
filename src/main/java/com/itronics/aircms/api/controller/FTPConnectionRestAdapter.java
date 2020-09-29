package com.itronics.aircms.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface FTPConnectionRestAdapter {
    @RequestMapping(method = RequestMethod.POST, value = "/api/ftp/connect")
    public ResponseEntity<String> connect(@RequestParam("user") String user,
                                          @RequestParam("password") String password,
                                          @RequestParam("port") int port,
                                          @RequestParam("server") String server);
}
