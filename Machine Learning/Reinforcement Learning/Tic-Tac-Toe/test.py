from pettingzoo.classic import tictactoe_v3

env = tictactoe_v3.env()

env.reset()
env.render()

print(env.last())