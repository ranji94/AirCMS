package com.itronics.aircms.repository;

import com.itronics.aircms.domain.FTPConnectionCredentials;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FTPConnectionCredentialsRepository extends CrudRepository<FTPConnectionCredentials, Long> {
    List<FTPConnectionCredentials> findByFtpServer(String FtpServer);
}
