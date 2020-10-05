package com.itronics.aircms.domain;

public class FTPDownloadStatus {
    private String fileName;
    private String newsId;
    private FTPClientStatus clientStatus;

    public FTPDownloadStatus() {}

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public FTPClientStatus getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(FTPClientStatus clientStatus) {
        this.clientStatus = clientStatus;
    }
}
