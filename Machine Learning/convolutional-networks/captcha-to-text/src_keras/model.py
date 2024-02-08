import tensorflow as tf
from tensorflow.keras import layers, models

class CaptchaRecognizer(tf.keras.Model):
    def __init__(self, input_shape, num_classes):
        super(CaptchaRecognizer, self).__init__()

        # Define convolutional and pooling layers
        self.conv1 = layers.Conv2D(32, kernel_size=5, padding='same', activation = 'relu')
        self.conv2 = layers.Conv2D(64, kernel_size=5, padding='same', activation = 'relu')
        self.conv3 = layers.Conv2D(128, kernel_size=5, padding='same', activation = 'relu')
        self.pool = layers.MaxPooling2D()

        # Intermediate linear layer
        self.linear1 = layers.Dense(64, activation = 'relu')
        self.drop1 = layers.Dropout(0.5)

        self.reshape = layers.Reshape((32, -1))

        # LSTM layer
        self.lstm = layers.LSTM(64, return_sequences = True)

        # Output layer
        self.fc = layers.Dense(num_classes + 1, activation = 'log_softmax')

    def call(self, x):
        # Pass through convolutional and pooling layers
        x = self.pool((self.conv1(x)))
        x = self.pool((self.conv2(x)))
        x = self.pool((self.conv3(x)))

        # Pass through linear layer
        x = self.linear1(x)
        x = self.drop1(x)

        # Pass through LSTM
        x = self.reshape(x)
        x = self.lstm(x)

        # Output layer
        x = self.fc(x)

        return x
    
if __name__ == '__main__':
    num_classes = 36
    model = CaptchaRecognizer(5,num_classes)

    input_tensor = tf.random.uniform((1,32,32,4))

    print(model(input_tensor))