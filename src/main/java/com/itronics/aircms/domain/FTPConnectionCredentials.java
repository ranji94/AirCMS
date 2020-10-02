package com.itronics.aircms.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FTPConnectionCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ftpServer;
    private int port;
    private String ftpUser;
    private String password;

    public FTPConnectionCredentials(String ftpServer, int port, String ftpUser, String password) {
        this.ftpServer = ftpServer;
        this.port = port;
        this.ftpUser = ftpUser;
        this.password = password;
    }

    public FTPConnectionCredentials() {}

    public String getFtpServer() {
        return ftpServer;
    }

    public void setFtpServer(String ftpServer) {
        this.ftpServer = ftpServer;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getFtpUser() {
        return ftpUser;
    }

    public void setFtpUser(String ftpUser) {
        this.ftpUser = ftpUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
