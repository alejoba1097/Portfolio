import pandas as pd
import numpy as np
import yfinance as yf

sp_index = 'SPY'
start_date = '2023-09-01'
end_date = '2023-09-30'

def get_all(sp_index, start_date, end_date):
    return yf.download(sp_index, start = start_date, end = end_date)

print(get_all(sp_index, start_date, end_date))