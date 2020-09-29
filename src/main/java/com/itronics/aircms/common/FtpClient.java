package com.itronics.aircms.common;

import com.itronics.aircms.domain.FTPConnectionCredentials;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
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

    public String open() throws IOException {
        ftp = new FTPClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

        ftp.connect(credentials.getFtpServer(), credentials.getPort());
        int reply = ftp.getReplyCode();

        if(!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            String failedMessage = "Cannot connect to FTP server. Check provided data and try again.";
            throw new IOException(failedMessage);
        }

        ftp.login(credentials.getFtpUser(), credentials.getPassword());
        return ftp.getStatus();
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

    public String downloadFile(String fileRemote, String fileSource) throws IOException {
        File downloadedFile = new File(fileSource);
        String status = "FAILED";
        try {
            OutputStream out = new BufferedOutputStream(new FileOutputStream(downloadedFile));
            boolean success = ftp.retrieveFile(fileRemote, out);
            out.close();
            status = success ? "File downloaded successfully." : "Cannot download file" ;
        } catch (FileNotFoundException e) {
            status = "File not found. Stacktrace: " + e;
        }

        return status;
    }

    public String uploadFile(String localFileName, String remotePath) throws IOException {
        File localFile = new File(localFileName);
        InputStream inputStream = new FileInputStream(localFile);
        boolean done = ftp.storeFile(remotePath, inputStream);
        inputStream.close();

        if(done) {
            return "File uploaded successfully";
        }

        return "File upload failed";
    }

    public boolean isConnected() {
        return ftp == null ? false : ftp.isConnected();
    }

    public FTPConnectionCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(FTPConnectionCredentials credentials) {
        this.credentials = credentials;
    }
}
