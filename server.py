from fastapi import FastAPI
#python -m pip install fastapi

import asyncio

import helper_scripts

app = FastAPI()

@app.get("/isup")
async def isServerUp():
    return {"Server is up!"}

@app.get("/summarize-article/{url}")
async def summarize_article(url: str):
    try:
        article_content = await helper_scripts.fetch_article_content(url)
        summary = await helper_scripts.summarize_text(article_content)
        return {"summary": summary}
    except Exception as e:
        print("Error")
        return {"Error"}
    
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
    
@app.get("/write_to_DB")
async def write_to_DB(input):
    return {input}


async def main():
    await summarize_article("https://lbpost.com/news/politics/progressive-democrats-say-services-not-penalties-will-cut-crime")

if __name__ == "__main__":
    asyncio.run(main())
