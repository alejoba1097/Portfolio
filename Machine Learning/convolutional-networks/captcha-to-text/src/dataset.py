import os
import glob
import csv
import pandas as pd
import torch
from torchvision.io import read_image
from torch.utils.data import Dataset
from urllib.request import urlopen
from io import BytesIO
from zipfile import ZipFile

class CaptchaImagesDataset(Dataset):
    def __init__(self, annotations_file, img_dir, extract_dir = './dataset', transform = None, target_transform = None):
        self.img_dir = img_dir
        self.transform = transform
        self.target_transform = target_transform
        self.extract_dir = extract_dir
        self.char_map = {char: index for index, char in enumerate("-abcdefghijklmnopqrstuvwxyz0123456789")}

        if not os.path.isfile('./dataset'):
            self.download_and_unzip(url = 'https://github.com/AakashKumarNain/CaptchaCracker/raw/master/captcha_images_v2.zip')
        self.img_labels = pd.read_csv(annotations_file, header = None)

        self.img_labels[1] = self.img_labels[1].apply(lambda x: self.string_to_int_sequence(x))

    def __len__(self):
        return len(self.img_labels)
    
    def __getitem__(self, index):
        img_path = os.path.join(self.img_dir, self.img_labels.iloc[index, 0])
        image = read_image(img_path).float()
        label = self.img_labels.iloc[index, 1]
        if self.transform:
            image = self.transform(image)
        if self.target_transform:
            label = self.target_transform(label)
        
        return image, label
    
    def download_and_unzip(self, url):
        http_response = urlopen(url)
        zipfile = ZipFile(BytesIO(http_response.read()))
        zipfile.extractall(path=self.extract_dir)

        images = glob.glob(self.img_dir + '/*.png')

        annotations = [[image.split('/')[-1], image.split('/')[-1].split('.')[-2]] for image in images]

        with open(self.extract_dir + '/labels.csv', 'w') as f:
            write = csv.writer(f)
            write.writerows(annotations)

    def string_to_int_sequence(self, string):
        # Insert a blank character between each character in the string
        #string_with_blank = [char if char == '-' else char + '-' for char in string]
        
        # Flatten the list of characters
        #flattened_string = [char for sublist in string_with_blank for char in sublist]
        
        # Map each character to its integer value using the vocabulary
        #integer_sequence = torch.tensor([self.char_map[char] for char in flattened_string], dtype=torch.long)
        integer_sequence = torch.tensor([self.char_map[char] for char in string], dtype=torch.long)
        
        return integer_sequence
