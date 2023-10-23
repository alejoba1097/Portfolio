resource "aws_redshift_cluster" "kinesis_to_redshift_cluster" {
    cluster_identifier = "kinesis-to-redshift-cluster"
    database_name      = "dev"
    master_username    = "alejoba1097"
    master_password    = "A801b5e55f2*"
    node_type          = "dc2.large"
    cluster_type       = "single-node"
    default_iam_role_arn = "arn:aws:iam::490388389030:role/RedshiftReadFromKinesis"
}