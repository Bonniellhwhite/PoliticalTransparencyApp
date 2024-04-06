from fastapi import FastAPI
#pip install fastapi


app = FastAPI()

@app.get("/isup")
async def isServerUp():
    return {"Server is up!"}

@app.get("/items/{item_id}")
async def read_item(item_id: int, q: str = None):
    return {"item_id": item_id, "q": q}
