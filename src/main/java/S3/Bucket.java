package S3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class Bucket {

    private final String bucketName;
    private final String keyName;
    final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTHEAST_2).build();

    public Bucket(String bucket_name, String key_name) {
        this.bucketName = bucket_name;
        this.keyName = key_name;
    }

    public void save(String data_content) {
        System.out.format("Uploading %s to S3 bucket %s...\n", data_content, bucketName);
        try {
            s3.putObject(bucketName, keyName, data_content);
        } catch (
                AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
        System.out.println("S3 saving done!");
    }

    public String get(String bucketName, String key) {
        return s3.getObjectAsString(bucketName, key);
    }
}