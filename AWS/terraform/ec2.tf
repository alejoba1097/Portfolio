resource "aws_instance" "EC2_Instance_From_Terraform" {
  ami = "ami-0bb4c991fa89d4b9b"
  instance_type = "t2.micro"
  tags = {
    Name = "EC2 Test Created from Terraform with VS Code"
  }
  key_name = "EC2FirstDemo"
  user_data = <<-EOF
                #!/bin/bash
                yum update -y
                yum install -y httpd
                systemctl start httpd.service
                systemctl enable httpd.service
                echo "Hello world! I'm $(hostname -f) from Terraform" > /var/www/html/index.html
                EOF
}