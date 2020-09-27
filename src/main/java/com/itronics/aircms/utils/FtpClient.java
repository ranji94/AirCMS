package com.itronics.aircms.utils;

import com.itronics.aircms.model.FTPConnectionCredentials;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class FtpClient {
    private static final FtpClient instance = new FtpClient();

    private FTPClient ftp;
    private FTPConnectionCredentials credentials;

    private FtpClient() { }

    public static FtpClient getInstance() {
        return instance;
    }

    public void open() throws IOException {
        ftp = new FTPClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

        ftp.connect(credentials.getServer(), credentials.getPort());
        int reply = ftp.getReplyCode();

        if(!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            String failedMessage = "Cannot connect to FTP server. Check provided data and try again.";
            throw new IOException(failedMessage);
        }

        ftp.login(credentials.getUser(), credentials.getPassword());
    }

    public Collection<String> listFiles(String path) throws IOException {
        FTPFile[] files = ftp.listFiles(path);
        return Arrays.stream(files)
                .map(FTPFile::getName)
                .collect(Collectors.toList());
    }

    void close() throws IOException {
        ftp.disconnect();
    }

    public FTPConnectionCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(FTPConnectionCredentials credentials) {
        this.credentials = credentials;
    }
}
