package com.itronics.aircms.model;

public class FTPConnectionCredentials {
    private String server;
    private int port;
    private String user;
    private String password;

    public FTPConnectionCredentials() { }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "FTPConnectionCredentials{" +
                "server='" + server + '\'' +
                ", port=" + port +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
