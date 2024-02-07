import torch
import torch.nn as nn

class CaptchaRecognizer(nn.Module):
    def __init__(self, input_size, num_classes):
        super().__init__()

        # Define convolutional and pooling layers
        self.conv1 = nn.Conv2d(4, 32, kernel_size=5, padding=2)
        self.conv2 = nn.Conv2d(32, 64, kernel_size=5, padding=2)
        self.conv3 = nn.Conv2d(64, 128, kernel_size=5, padding=2)
        self.pool = nn.MaxPool2d(kernel_size=2, stride=2)

        # Intermediate linear layer
        self.linear1 = nn.Linear(150, 64)
        self.drop1 = nn.Dropout()

        # LSTM layer
        self.lstm = nn.LSTM(64, 64, batch_first = True)

        # Output layer
        self.fc = nn.Linear(64, num_classes + 1)

    def forward(self, x):
        # Pass through convolutional and pooling layers
        x = self.pool(nn.functional.relu(self.conv1(x)))
        x = self.pool(nn.functional.relu(self.conv2(x)))
        x = self.pool(nn.functional.relu(self.conv3(x)))

        # Pass through linear layer
        x = x.view(x.size(0), x.size(1), -1)
        x = nn.functional.relu(self.linear1(x))

        x = self.drop1(x)

        # Pass through LSTM
        x, (h, c) = self.lstm(x)

        # Output layer
        x = self.fc(x)

        x = nn.functional.log_softmax(x, dim = 2)

        return x