package com.itronics.aircms.bizz;

import com.google.gson.Gson;
import com.itronics.aircms.common.FtpClient;
import com.itronics.aircms.domain.FTPConnectionCredentials;
import com.itronics.aircms.domain.News;
import com.itronics.aircms.repository.FTPConnectionCredentialsRepository;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Service
public class FTPOperationsService {

    @Autowired
    FTPConnectionCredentialsRepository ftpConnectionCredentialsRepository;

    public static final Logger logger = Logger.getLogger(FTPOperationsService.class.getName());
    private static final String NEWS_FILENAME = "news.json";
    private static final String REMOTE_NEWS_PATH = "aircms/" + NEWS_FILENAME;
    private static final String REMOTE_IMAGES_NEWS_PATH = "aircms/images/news/";
    private final Gson gson = new Gson();
    private static final String ROOT_DIR = System.getProperty("user.dir");

    public List<News> downloadNewsFeed() throws IOException {
        final FtpClient ftpClient = FtpClient.getInstance();
        final String localPath = ROOT_DIR + "/users/" + ftpClient.getRemoteAddress() + "/";

        if(ftpClient.isConnected()) {
            Files.createDirectories(Paths.get(localPath));
//            String downloadStatus = ftpClient.downloadFile(REMOTE_NEWS_PATH, localPath + NEWS_FILENAME);

            logger.info(String.format("[DownloadNewsFeed] Download news status: %s",
//                    downloadStatus
                    "DOWNLOAD NEWS FROM FTP DISABLED FOR TEST PURPOSE"
            ));
            return new ArrayList<>(Arrays.asList(gson.fromJson(new FileReader(localPath + NEWS_FILENAME), News[].class)));
        } else {
         return new ArrayList<>();
        }
    }

    public HttpEntity<byte[]> downloadImage(String fileName, String newsId) throws IOException {
        final FtpClient ftpClient = FtpClient.getInstance();
        final String localPath = ROOT_DIR + "/users/" + ftpClient.getRemoteAddress() + "/" + "images/news/" + newsId + "/";
        final HttpHeaders headers = new HttpHeaders();

        if(ftpClient.isConnected()) {
            Files.createDirectories(Paths.get(localPath));
            //COMMENTED FOR TEST PURPOSES
//            String downloadStatus = ftpClient.downloadFile(REMOTE_IMAGES_NEWS_PATH + newsId + "/" + fileName, localPath + fileName);
//            logger.info(String.format("[DownloadImages] Image download status: %s", downloadStatus));

            BufferedImage bImage = ImageIO.read(new File(localPath + fileName));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpeg", bos);
            byte [] data = bos.toByteArray();

            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(data.length);

            return new HttpEntity<byte[]>(data, headers);
        }
        return new HttpEntity<>(null, headers);
    }

    public List<HttpEntity<byte[]>> downloadAllImagesInNewsDirectory(String newsId) throws IOException {
        final FtpClient ftpClient = FtpClient.getInstance();
        FTPFile[] subFiles = Arrays
                .stream(ftpClient.listFiles(REMOTE_IMAGES_NEWS_PATH + newsId + "/"))
                .filter(i -> !(i.getName().equals(".") || i.getName().equals("..")))
                .toArray(FTPFile[]::new);
        List<HttpEntity<byte[]>> allImagesInDir = new ArrayList<>();

        if(subFiles.length > 0) {
            for(FTPFile aFile : subFiles) {
                allImagesInDir.add(downloadImage(aFile.getName(), newsId));
            }
        }

        return allImagesInDir;
    }

    public List<News> uploadNewsFeed(List<News> newsList) throws IOException {
        FtpClient ftpClient = FtpClient.getInstance();
        String newsFeedString = gson.toJson(newsList);

        final String localPath = ROOT_DIR + "/users/" + ftpClient.getRemoteAddress() + "/" + NEWS_FILENAME;

        BufferedWriter writer = new BufferedWriter(new FileWriter(localPath + NEWS_FILENAME));
        writer.write(newsFeedString);
        writer.close();

        ftpClient.uploadFile(localPath + NEWS_FILENAME, REMOTE_NEWS_PATH);

        return newsList;
    }

    public List<FTPConnectionCredentials> getAllSavedConnections() {
        return ftpConnectionCredentialsRepository.findAll();
    }
}
