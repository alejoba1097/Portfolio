terraform plan
terraform apply #--auto-approve
terraform destroy

# Connect to the instance remotely using SSH Key Pair
ssh - i ~/.ssh/EC2FirstDemo.pem ec2-user@"EC2 Instance Public IPv4"
