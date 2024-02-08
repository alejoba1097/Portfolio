from src.model import CaptchaRecognizer
from src.dataset import CaptchaImagesDataset
import torch
import torch.nn as nn
import torchvision
import torchvision.transforms as transforms

# Load the dataset
print("Loading dataset")
captcha_dataset = CaptchaImagesDataset(img_dir = './dataset/captcha_images_v2',
                                       annotations_file = './dataset/labels.csv',
                                       transform = transforms.Normalize(
                                           mean=[0, 0, 0, 0],
                                           std=[255, 255, 255, 255]))

train_set, test_set = torch.utils.data.random_split(captcha_dataset, [0.8, 0.2], generator=torch.Generator().manual_seed(42))
test_loader = torch.utils.data.DataLoader(test_set, num_workers = 2)

# Infere and check precision
correct = 0
total = 0

model = CaptchaRecognizer(input_size = (50,200), num_classes = 36)

model.load_state_dict(torch.load("text_recognizer.pth"))
model.eval()

char_map = {char: index for index, char in enumerate("-abcdefghijklmnopqrstuvwxyz0123456789")}
reverse_map = {value : key for key, value in char_map.items()}

with torch.no_grad():
    for data in test_loader:
        images, labels = data
        outputs = model(images)

        _, predicted = torch.max(outputs.data, 2)

        output_str = "".join(reverse_map[char.item()] for char in predicted[0])

        print(labels, ", ", output_str)

        #total += labels.size(0)
        #correct += (predicted == labels).sum().item()
        #break