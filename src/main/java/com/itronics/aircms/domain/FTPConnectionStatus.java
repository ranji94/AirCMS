package com.itronics.aircms.domain;

public class FTPConnectionStatus {
    private boolean isConnected;
    private String connectedUser;
    private String connectedServer;

    public FTPConnectionStatus() { }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public String getConnectedUser() {
        return connectedUser;
    }

    public void setConnectedUser(String connectedUser) {
        this.connectedUser = connectedUser;
    }

    public String getConnectedServer() {
        return connectedServer;
    }

    public void setConnectedServer(String connectedServer) {
        this.connectedServer = connectedServer;
    }
}
