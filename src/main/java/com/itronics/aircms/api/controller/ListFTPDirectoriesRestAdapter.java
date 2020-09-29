package com.itronics.aircms.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

public interface ListFTPDirectoriesRestAdapter {
    @RequestMapping(method = RequestMethod.GET, value = "/api/ftp/listDirectories")
    public ResponseEntity<Collection<String>> listDirectories();
}
