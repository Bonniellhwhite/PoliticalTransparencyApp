from bs4 import BeautifulSoup
from fastapi import FastAPI, HTTPException
import requests
import httpx
import openai
import os

# Get the key from me and follow this article 
# #https://stackoverflow.com/questions/75313457/openai-api-openai-api-key-os-getenv-not-working
openai_api_key = os.getenv('OPENAI_API_KEY')

async def fetch_article_content(url: str) -> str:
    async with httpx.AsyncClient() as client:
        response = await client.get(url)
        if response.status_code == 200:
            # Currently Specific to given website, but will be more flexible as more are accepted 
            #Webscraping regex goes here for now 
            url = ""
            response = requests.get(url)
            soup = BeautifulSoup(response.content, 'html.parser')

            # Getting all content inside <div class="entry-content"></div>
            article_content = soup.find('div',class_='entry-content')
            if article_content:
                # Get all text from the <p> tags within the article
                paragraphs = article_content.find_all('p')

                # Concatenating the text of each paragraph to form the full article text
                article_text = '\n'.join(paragraph.text for paragraph in paragraphs)
                return article_text
        else:
            raise HTTPException(status_code=404, detail="Article not found")

async def summarize_text(text: str) -> str:
    response = openai.Completion.create(
      engine="davinci", 
      prompt="Summarize this article into a single paragraph: {}".format(text), 
      max_tokens=150,
      temperature=0.7
    )
    return response.choices[0].text.strip()

# For testing
#print(openai_api_key)