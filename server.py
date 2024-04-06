from fastapi import FastAPI
#python -m pip install fastapi
from urllib.parse import unquote

import helper_scripts

app = FastAPI()

@app.get("/isup")
async def isServerUp():
    return {"Server is up!"}

@app.get("/summarize_article/{url}")
async def summarize_article(url):
    '''
    try:
        decoded_url = unquote(url)
        article_content = await helper_scripts.fetch_article_content(decoded_url)
        summary = await helper_scripts.summarize_text(article_content)
        print("Retuning results")
        return {"summary": summary}
    except Exception as e:
        print("Error")
        return {"Error"}
        '''
    return {url}
    
@app.get("/simplify-bill/{url}")
async def simplify_bill(url: str):
    try:
        if "https://" in url:
            #Scrape URL Article 
            print("")
            return ""
        else:
            # Simplify text only
            print("")
            return ""
    except Exception as e:
        return {"Error"}
    
@app.get("/write_to_DB/{test}")
async def write_to_DB(test):
    return {test}

'''
async def main():
    await summarize_article("https://lbpost.com/news/politics/progressive-democrats-say-services-not-penalties-will-cut-crime")

if __name__ == "__main__":
    asyncio.run(main())
'''