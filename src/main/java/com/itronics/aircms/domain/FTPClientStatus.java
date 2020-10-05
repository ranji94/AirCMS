package com.itronics.aircms.domain;

public enum FTPClientStatus {
    FTP_CLIENT_CONNECTED("FTP_CLIENT_CONNECTED"),
    FTP_CLIENT_DISCONNECTED("FTP_CLIENT_DISCONNECTED"),
    FTP_DOWNLOAD_FAILED("FTP_DOWNLOAD_FAILED"),
    FTP_FILE_NOT_FOUND("FTP_FILE_NOT_FOUND"),
    FTP_DIRECTORY_NOT_FOUND("FTP_DIRECTORY_NOT_FOUND"),
    FTP_DOWNLOAD_SUCCESS("FTP_DOWNLOAD_SUCCESS"),
    FTP_UPLOAD_FAILED("FTP_UPLOAD_FAILED"),
    FTP_FILE_FETCH_FAILED("FTP_FILE_FETCH_FAILED"),
    FTP_UPLOAD_SUCCESS("FTP_UPLOAD_SUCCESS");

    private final String clientStatus;

    private FTPClientStatus(String value) {
        clientStatus = value;
    }

    public String getClientStatus() {
        return clientStatus;
    }
}
