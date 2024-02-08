import os
import tensorflow as tf
import numpy as np
from tensorflow import keras
from keras.preprocessing.image import load_img, img_to_array
from sklearn.model_selection import train_test_split

class CaptchaImagesDataset(tf.keras.utils.Sequence):
    def __init__(self, img_dir, validation_split = 0.2, split = 'train', batch_size = 32):
        self.img_dir = img_dir
        self.split = split
        self.batch_size = batch_size

        self.image_filenames = os.listdir(img_dir)
        self.labels = [filename.split('.')[0] for filename in self.image_filenames]

        self.train_paths, self.test_paths, self.train_labels, self.test_labels = train_test_split(
            self.image_filenames, self.labels, test_size=validation_split, random_state=42, shuffle=True)
    
    def __len__(self):
        if self.split == "train":
            return int(np.ceil(len(self.train_paths) / self.batch_size))
        else:
            return int(np.ceil(len(self.test_paths) / self.batch_size))
        
    def __getitem__(self, index):
        batch_paths = self.image_filenames[index * self.batch_size : (index + 1) * self.batch_size]
        batch_labels = self.labels[index * self.batch_size : (index + 1) * self.batch_size]

        images = [self.load_and_preprocess_image(filename, label) for filename, label in zip(batch_paths, batch_labels)]
        batch_images, batch_labels = zip(*images)

        return tf.convert_to_tensor(batch_images), tf.convert_to_tensor(batch_labels)
                  
    def load_and_preprocess_image(self, filename, label):
        img_path = os.path.join(self.img_dir, filename)
        img = load_img(img_path)
        img_array = img_to_array(img) / 255.0
        return img_array, label
