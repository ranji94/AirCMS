package com.itronics.aircms.common;

import com.itronics.aircms.domain.FTPClientStatus;
import com.itronics.aircms.domain.FTPConnectionCredentials;
import com.itronics.aircms.domain.FTPConnectionStatus;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class FtpClient {
    private static final FtpClient instance = new FtpClient();
    public static final Logger logger = Logger.getLogger(FtpClient.class.getName());

    private FTPClient ftp;
    private FTPConnectionCredentials credentials;

    private FtpClient() { }

    public static FtpClient getInstance() {
        return instance;
    }

    public FTPConnectionStatus open() throws IOException {
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

        FTPConnectionStatus status = new FTPConnectionStatus();
        status.setConnected(ftp.isConnected());
        status.setConnectedUser(credentials.getFtpUser());
        status.setConnectedServer(credentials.getFtpServer());
        status.setClientStatus(FTPClientStatus.FTP_CLIENT_CONNECTED);

        return status;
    }

    public FTPConnectionStatus disconnect() throws IOException {
        FTPConnectionStatus status = new FTPConnectionStatus();
        status.setConnected(false);
        status.setClientStatus(FTPClientStatus.FTP_CLIENT_DISCONNECTED);

        ftp.disconnect();

        return status;
    }

    public FTPFile[] listFiles(String path) throws IOException {
        return ftp.listFiles(path);
    }

    public Collection<String> listFilesCollection(String path) throws IOException {
        FTPFile[] files = ftp.listFiles(path);
        return Arrays.stream(files)
                .map(FTPFile::getName)
                .collect(Collectors.toList());
    }

    public FTPClientStatus downloadFile(String fileRemote, String fileSource) throws IOException {
        final File downloadedFile = new File(fileSource);
        FTPClientStatus status;
        try {
            FileOutputStream fos = new FileOutputStream(downloadedFile);
            InputStream inputStream = ftp.retrieveFileStream(fileRemote);

            byte[] bytesIn = new byte[4096];
            int read = 0;

            while((read = inputStream.read(bytesIn)) != -1) {
                fos.write(bytesIn, 0, read);
            }

            inputStream.close();
            fos.close();

            if(!ftp.completePendingCommand()) {
                ftp.logout();
                ftp.disconnect();
                logger.info("[DownloadFTP] File transfer failed.");
                status = FTPClientStatus.FTP_DOWNLOAD_FAILED;
                System.exit(1);
            }

            status = FTPClientStatus.FTP_DOWNLOAD_SUCCESS;
        } catch (FileNotFoundException e) {
            status = FTPClientStatus.FTP_FILE_NOT_FOUND ;
        }

        return status;
    }

    public FTPClientStatus uploadFile(String localFileName, String remotePath) throws IOException {
        File localFile = new File(localFileName);
        InputStream inputStream = new FileInputStream(localFile);
        boolean done = ftp.storeFile(remotePath, inputStream);
        inputStream.close();

        if(done) {
            return FTPClientStatus.FTP_UPLOAD_SUCCESS;
        }

        return FTPClientStatus.FTP_UPLOAD_FAILED;
    }

    public String getRemoteAddress() {
        if (ftp == null) {
            return null;
        } else {
            if (ftp.getRemoteAddress() == null) {
               return null;
            }
        }

        return ftp.getRemoteAddress().getHostName();
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
