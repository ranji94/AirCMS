package com.itronics.aircms.rest;

import com.itronics.aircms.api.controller.SaveConnectionCredentialsRestAdapter;
import com.itronics.aircms.domain.FTPConnectionCredentials;
import com.itronics.aircms.repository.FTPConnectionCredentialsRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "saveFTPConnectionCredentials", tags = {"saveFTPConnectionCredentials"})
public class SaveConnectionCredentialsRestAdapterImpl implements SaveConnectionCredentialsRestAdapter {
    @Autowired
    FTPConnectionCredentialsRepository ftpConnectionCredentialsRepository;

    @Override
    public ResponseEntity<String> saveCredentials(FTPConnectionCredentials ftpConnectionCredentials){
        ftpConnectionCredentialsRepository.save(ftpConnectionCredentials);
        return new ResponseEntity<>("FTP Connection Credentials Saved", HttpStatus.OK);
    }
}
