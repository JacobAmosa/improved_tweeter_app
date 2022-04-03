package edu.byu.cs.server.dao;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.ByteArrayInputStream;
import java.util.Base64;

import edu.byu.cs.shared.model.net.request.RegisterRequest;

public class S3ImageDAO implements S3ImageInterface {
    public final String bucketName = "jacobbucket340";
    private AmazonS3 amazonS3;
    private String imageURL = null;

    @Override
    public String uploadImage(RegisterRequest request) {
        this.amazonS3 = AmazonS3ClientBuilder.standard().withRegion("us-west-2").build();
        String fileName = request.getUsername() + ".png";
        byte[] bytes = Base64.getDecoder().decode(request.getImage());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        PutObjectRequest objectRequest = new PutObjectRequest(bucketName, fileName,byteArrayInputStream, new ObjectMetadata())
                .withCannedAcl(CannedAccessControlList.PublicReadWrite);
        amazonS3.putObject(objectRequest);
        amazonS3.setObjectAcl(bucketName, request.getUsername(), CannedAccessControlList.PublicRead);
        imageURL = amazonS3.getUrl(bucketName, fileName).toString();
        return imageURL;
    }
}
