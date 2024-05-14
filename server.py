from fastapi import FastAPI, HTTPException
#python -m pip install fastapi
from urllib.parse import unquote
import helper_scripts

import firebase_admin
from firebase_admin import credentials
from firebase_admin import firestore

# Initialize Firebase Admin
cred = credentials.Certificate('G:/Bonnie White/Documents/School/Fall 2023/491A - Senior Project/my-project-1684959286164-95638a341fed.json')

firebase_admin.initialize_app(cred)
print(cred)

# Get Firestore database
db = firestore.client()

# Setup: heroku run python server.py --app politipal-backend

app = FastAPI()

@app.get("/isup")
async def isServerUp():
    return {"Server is up!"}

@app.get("/summarize_article/")
async def summarize_article(url:str):
   
 
    try:
        decoded_url = unquote(url)
        article_content = await helper_scripts.fetch_article_content(decoded_url)
        summary = await helper_scripts.summarize_text(article_content)
        print("Retuning results")
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
    



@app.get("/get-bills/")
async def get_bill():
    try:
        # Attempt to get the document from Firestore
        doc_ref = db.collection('allBills')
        doc = doc_ref.get()
        if doc.exists:
            return doc.to_dict()
        else:
            return {"error": "Bill not found"}
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))

@app.get("/get-reps/")
async def get_bill():
    try:
        # Attempt to get the document from Firestore
        doc_ref = db.collection('allLegislators')
        doc = doc_ref.get()
        if doc.exists:
            return doc.to_dict()
        else:
            return {"error": "Bill not found"}
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))



'''
async def main():
    await summarize_article("https://lbpost.com/news/politics/progressive-democrats-say-services-not-penalties-will-cut-crime")

if __name__ == "__main__":
    asyncio.run(main())
'''