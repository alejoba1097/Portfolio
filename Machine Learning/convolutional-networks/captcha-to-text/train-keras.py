from src_keras.dataset import CaptchaImagesDataset
from src_keras.model import CaptchaRecognizer
import tensorflow as tf
from tensorflow import keras
from keras.optimizers import Adam

num_classes = 36
epochs = 10
batch_size = 8

train_dataset = CaptchaImagesDataset("dataset/captcha_images_v2", split="train", batch_size=batch_size)
test_dataset = CaptchaImagesDataset("dataset/captcha_images_v2", split="test", batch_size=batch_size)

model = CaptchaRecognizer((50, 200), num_classes)

optimizer = Adam(learning_rate=0.001)
loss_fn = tf.nn.ctc_loss

for epoch in range(epochs):
  for batch_images, batch_labels in train_dataset:  # Replace with your data loader
    with tf.GradientTape() as tape:
      # Forward pass
      predictions = model(batch_images)

      # CTC loss calculation (including label lengths)
      label_lengths = tf.fill([batch_size], 5)  # Set all lengths to 5 (example)
      logit_lengths = tf.fill([batch_size], 32)
      loss = loss_fn(batch_labels, predictions, label_lengths, logit_lengths)

    # Calculate gradients
    gradients = tape.gradient(loss, model.trainable_variables)

    # Update model weights
    optimizer.apply_gradients(zip(gradients, model.trainable_variables))

    # Print training progress (optional)
    print(f"Epoch {epoch+1}/{10}, Batch loss: {loss.numpy()}")


model.save('captcha_model.h5')