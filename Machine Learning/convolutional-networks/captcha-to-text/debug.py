import torch
import torch.nn as nn
from torch.nn import functional as F

vocab = {char: index for index, char in enumerate("abc-")}

bs = 5

target_str = torch.tensor(
    [[0., 1., 2.]]*bs
) #abc

input_str = torch.tensor([
    [[1., 0., 0., 0.],    [0., 1., 0., 0.],    [0., 0., 1., 0.]],
    [[1., 0., 0., 0.],    [1., 0., 0., 0.],    [1., 0., 0., 0.]],
    [[0., 1., 0., 0.],    [0., 1., 0., 0.],    [0., 1., 0., 0.]],
    [[0., 0., 1., 0.],    [0., 0., 1., 0.],    [0., 0., 1., 0.]],
    [[0., 0., 0., 1.],    [0., 0., 0., 1.],    [0., 0., 0., 1.]]
]) #abc

input_lengths = torch.full(
            size = (bs,), fill_value=input_str.size(1), dtype=torch.int64)
        
target_lengths = torch.full(
            size = (bs,), fill_value = target_str.size(1), dtype = torch.int64)

log_probs = F.log_softmax(input_str, dim = 2).permute(1, 0, 2)

torch.log(F.ctc_loss(log_probs, target_str, input_lengths, target_lengths, blank=3, reduction='none'))


target_str = torch.tensor(
    [[0., 1., 2.],
    [0., 0., 0.],
    [1., 1., 1.],
    [2., 2., 2.],
    [3., 3., 3.]],
) #abc

input_str = torch.tensor([
    [[1., 0., 0., 0.],    [0., 1., 0., 0.],    [0., 0., 1., 0.]],
    [[1., 0., 0., 0.],    [1., 0., 0., 0.],    [1., 0., 0., 0.]],
    [[0., 1., 0., 0.],    [0., 1., 0., 0.],    [0., 1., 0., 0.]],
    [[0., 0., 1., 0.],    [0., 0., 1., 0.],    [0., 0., 1., 0.]],
    [[0., 0., 0., 1.],    [0., 0., 0., 1.],    [0., 0., 0., 1.]]
]) #abc

class MalparidaRedNeuronalEnPytorch(nn.Module):
    def __init__(self, inputs, outputs):
        super().__init__()
        self.linear = nn.Linear(inputs, outputs)
    
    def forward(self, x):
        return self.linear(x)
    
model = MalparidaRedNeuronalEnPytorch(4,4)

optimizer = torch.optim.Adam(model.parameters(), lr=0.1)
criterion = nn.CTCLoss(blank = 3, zero_infinity=True, reduction = 'mean')

for epoch in range(10):  # Adjust the number of epochs
    # Forward pass
    outputs = model(input_str)
    input_lengths = torch.full(
            size = (bs,), fill_value=input_str.size(1), dtype=torch.int64)
        
    target_lengths = torch.full(
                size = (bs,), fill_value = outputs.size(1), dtype = torch.int64)

    log_probs = nn.functional.log_softmax(input_str, dim = 2).permute(1, 0, 2)

    loss = criterion(log_probs, target_str, input_lengths, target_lengths)
    
    print(model.state_dict())

    # Backward pass and optimization
    optimizer.zero_grad()
    loss.requires_grad = True
    loss.backward()
    optimizer.step()

    print(loss)