resource "aws_kinesis_stream" "test_kds" {
    name = "test_kds"
    retention_period = 24 # Default value

    stream_mode_details {
      stream_mode = "ON_DEMAND"
    }
}