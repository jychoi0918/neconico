package com.neconico.neconico.file.s3provider;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

import static com.amazonaws.services.s3.model.DeleteObjectsRequest.*;

@Slf4j
@Component
public class S3Deleter {
    private AmazonS3 s3Client;

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    public void delete(String dirName, String fileName) {

        String bucketFolder =  bucket + "/" + dirName;

        try {
            s3Client.deleteObject(new DeleteObjectRequest(bucketFolder, fileName));
            log.info("파일이 버킷에서 삭제되었습니다.");

        }catch (AmazonServiceException e) {
            e.printStackTrace();

        }catch (SdkClientException e) {
            e.printStackTrace();
        }
    }

    public void deletes(String dirName, List<String> fileNames) {
        String bucketFolder =  bucket + "/" + dirName;

        try {
            DeleteObjectsResult deleteObjectsResult = s3Client.deleteObjects(createRequest(bucketFolder, fileNames));

            int successfulDeletes = deleteObjectsResult.getDeletedObjects().size();

            log.info("{}개 파일이 버킷에서 삭제되었습니다.", successfulDeletes);

        } catch(AmazonServiceException e) {
            e.printStackTrace();

        }catch (SdkClientException e) {
            e.printStackTrace();
        }

    }

    private DeleteObjectsRequest createRequest(String bucketFolder, List<String> fileNames) {

        List<KeyVersion> keys = fileNames.stream()
                .map(KeyVersion::new)
                .collect(Collectors.toList());

        return new DeleteObjectsRequest(bucketFolder)
                .withKeys(keys);
    }
}
