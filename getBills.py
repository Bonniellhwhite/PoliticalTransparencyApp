import requests
import pandas as pd
import csv
import json


# Bill API has 17817 results so I am going to drop it to 1 request, which is 250 rows
key = "3abJiDguIhbNKrqAyEBsYYHbWjSTm2ZMiKwVvmYL"
base_url = "https://api.congress.gov/v3/bill"
headers = {
    'Content-Type': 'application/json'
}   

# Bill Section 
print("Processing Congress:",118)
params = {
    'format': 'json',
    'limit': 5,
    'api_key': key
}

response = requests.get(f"{base_url}/{118}", headers=headers, params=params)
data = response.json()





def read_json(filename: str) -> dict: 
  
    try: 
        with open(filename, "r") as f: 
            data = json.loads(f.read()) 
    except: 
        raise Exception(f"Reading {filename} file encountered an error") 
  
    return data 
  
  
def normalize_json(data: dict) -> dict: 
  
    new_data = dict() 
    for key, value in data.items(): 
        if not isinstance(value, dict): 
            new_data[key] = value 
        else: 
            for k, v in value.items(): 
                new_data[key + "_" + k] = v 
      
    return new_data 
  

# Read the JSON file as python dictionary 
data = read_json(filename="article.json") 

# Normalize the nested python dict  
new_data = normalize_json(data=data) 

print("New dict:", new_data, "\n") 

# Create a pandas dataframe  
dataframe = pd.DataFrame(new_data, index=[0]) 

# Write to a CSV file 
dataframe.to_csv("article.csv") 

 