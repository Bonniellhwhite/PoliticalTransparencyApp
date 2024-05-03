from bs4 import BeautifulSoup
from fastapi import FastAPI, HTTPException
import requests
import httpx
from openai import OpenAI
import os

# Get the key from me and follow this article 
# #https://stackoverflow.com/questions/75313457/openai-api-openai-api-key-os-getenv-not-working

openai_api_key = os.getenv('OPENAI_API_KEY')
client = OpenAI()

async def fetch_article_content(url: str) -> str:
    async with httpx.AsyncClient() as client:
        response = await client.get(url)
        if response.status_code == 200:
            print("Code 200 working")
            # Currently Specific to given website, but will be more flexible as more are accepted 
            #Webscraping regex goes here for now 
            response = requests.get(url)
            soup = BeautifulSoup(response.content, 'html.parser')
            
            # Getting all content inside <div class="entry-content"></div>
            article_content = soup.find('div',class_='entry-content')
            if article_content:
                # Get all text from the <p> tags within the article
                paragraphs = article_content.find_all('p')

                # Concatenating the text of each paragraph to form the full article text
                article_text = '\n'.join(paragraph.text for paragraph in paragraphs)
                print("Returning Article Text:")
                return article_text
        else:
            raise HTTPException(status_code=404, detail="Article not found")

async def summarize_text(text: str) -> str:
    OpenAI.api_key = openai_api_key
    try:
        response = client.chat.completions.create(
            model="gpt-3.5-turbo",
            messages=[
                {"role": "system", "content": "You are a helpful assistant, you summarize given article texts into a single paragraph"},
                {"role": "user", "content": text}
            ]
        )
        summary = response.choices[0].message.content
        #print("Summary:", summary)
        return summary
    except Exception as e:
        print("An error occurred:", e)
        return str(e)  # Or handle the error as appropriate for your application

# For testing
#print(openai_api_key)

