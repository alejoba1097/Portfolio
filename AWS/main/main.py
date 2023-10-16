import boto3

# Credentials and configuration provided with $ aws configure
s3 = boto3.client('s3')
bucket = "alejoba1097-stock-data"

s3.delete_object(Bucket = bucket, Key='test.txt')