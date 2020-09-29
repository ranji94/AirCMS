package com.itronics.aircms.domain;

public class FTPConnectionCredentials {
    private String server;
    private int port;
    private String user;
    private String password;

    public FTPConnectionCredentials(String server, Integer port, String user, String password) {
        this.server = server;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    public String getServer() {
        return server;
    }

    public int getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
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
