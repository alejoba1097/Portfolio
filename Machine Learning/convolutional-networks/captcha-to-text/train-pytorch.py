from src.model import CaptchaRecognizer
from src.dataset import CaptchaImagesDataset
import torch
import torch.nn as nn
import torchvision
import torchvision.transforms as transforms

# Load the dataset
print("Loading dataset")

batch_size = 8
epochs = 10

captcha_dataset = CaptchaImagesDataset(img_dir = './dataset/captcha_images_v2',
                                       annotations_file = './dataset/labels.csv',
                                       transform = transforms.Normalize(
                                           mean=[0, 0, 0, 0],
                                           std=[255, 255, 255, 255]))

train_set, test_set = torch.utils.data.random_split(captcha_dataset, [0.8, 0.2], generator=torch.Generator().manual_seed(42))
train_loader = torch.utils.data.DataLoader(train_set, batch_size = batch_size, shuffle = True, num_workers = 4)

# Create the model
print("Creating the model")
model = CaptchaRecognizer(input_size = (50,200), num_classes = 36)

# Choose an optimizer and loss function
optimizer = torch.optim.Adam(model.parameters(), lr=1e-5)
criterion = nn.CTCLoss(reduction = 'mean', zero_infinity = True)

char_map = {char: index for index, char in enumerate("-abcdefghijklmnopqrstuvwxyz0123456789")}
reverse_map = {value : key for key, value in char_map.items()}

# Training loop
print("Starting training loop")
for epoch in range(epochs):  # Adjust the number of epochs
    for i, (images, labels) in enumerate(train_loader):
        # Forward pass
        outputs = model(images)
        outputs = outputs.permute(1,0,2) # Update shape to T, N, C

        input_lengths = torch.full(
            size = (batch_size,), fill_value=outputs.size(0), dtype=torch.int32)
        
        target_lengths = torch.full(
            size = (batch_size,), fill_value = labels.size(1), dtype = torch.int32)

        loss = criterion(outputs, labels, input_lengths, target_lengths)

        # Backward pass and optimization
        optimizer.zero_grad()
        loss.backward()
        optimizer.step()

        # Print training progress
        
        if i % 100 == 0:
            
            _, predicted = torch.max(outputs.data, 2)
            predicted = predicted.permute(1,0)

            output_str = "".join(reverse_map[char.item()] for char in predicted[0])
            label_str = "".join(reverse_map[char.item()] for char in labels[0])
            print(f"Epoch [{epoch+1}/{epochs}], Step [{i}/{len(train_loader)}], Loss: {loss.item():.4f}, String: {output_str}, Label: {label_str}")
            

# Save the trained model (optional)
torch.save(model.state_dict(), "text_recognizer.pth")