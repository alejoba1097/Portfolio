import torch
import torch.nn as nn
import model

vocabulary = [
   "hello",
   "world",
   "python",
   "transformer",
   "coding",
   "learning",
   "neural",
   "network",
   "machine",
   "language",
   "[PAD]",
   "[SOS]",
   "[EOS]",
   "[UNK]"
]

vocabulary_french = [
    "bonjour",  # hello
    "monde",  # world
    "python",  # python (same in French)
    "transformateur",  # transformer
    "codage",  # coding
    "apprentissage",  # learning
    "neuronal",  # neural
    "réseau",  # network
    "machine",  # machine (same in French)
    "langage",  # language
    "[PAD]",
   "[SOS]",
   "[EOS]",
   "[UNK]" # unknown token (keep in English)
]

vocab_size = len(vocabulary)
d_model = 100

def tokenize_en(input_str):
    return torch.tensor([vocabulary.index(word) if word in vocabulary else vocabulary.index("[UNK]") for word in input_str.split(' ')])

def tokenize_fr(input_str):
    return torch.tensor([vocabulary_french.index(word) if word in vocabulary_french else vocabulary_french.index("[UNK]") for word in input_str.split(' ')])

def token_to_word(token_id):
    return vocabulary[token_id]

class DumbEncoDecoder(nn.Module):
    def __init__(self,vocab_size, d_model):
        super().__init__()
        self.input_embedder = nn.Embedding(vocab_size, d_model)
        self.linear_layer = nn.Linear(d_model, vocab_size)
        self.softmax_layer = nn.Softmax()
    
    def forward(self, tokenized_input):
        output = self.input_embedder(tokenized_input)
        output = self.linear_layer(output)
        output = self.softmax_layer(output)

        return output
    
    def decode(self, tokenized_input):
        output = self.input_embedder(tokenized_input)
        return output

input_str = "hello amazing python world"
label_str = "bonjour beau python monde"

tokenized_input = tokenize_en(input_str)
tokenized_label = tokenize_fr(label_str)

input_encoder = DumbEncoDecoder(vocab_size, d_model)
output_encoder = DumbEncoDecoder(vocab_size, d_model)

# Training loop
epochs = 10000
optimizer = torch.optim.Adam(input_encoder.parameters(),lr=1e-4,eps=1e-9)
loss_fn = nn.CrossEntropyLoss()

for _ in range(epochs):
    input_encoder.train()
    model_output = input_encoder(tokenized_input)

    loss = loss_fn(model_output, output_encoder(tokenized_label))
    loss.backward()

    optimizer.step()
    optimizer.zero_grad(set_to_none=True)

" ".join([token_to_word(token_id) for token_id in torch.max(input_encoder(tokenized_input), dim=1)[1]])