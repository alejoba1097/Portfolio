import boto3
import json

f = open('/home/alejoba1097/Portfolio/AWS/kinesis_to_redshift/posts.json')
data = json.load(f)

kinesis_client = boto3.client('kinesis')
response = kinesis_client.put_record(
    StreamName = "test_kds",
    Data = json.dumps(data),
    PartitionKey = "1"
)

print(response)