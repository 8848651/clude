import random
import string
from flask import Flask, request, jsonify
import json
import base64
from PIL import Image
from io import BytesIO
from facedome import FaceRecognition
import os
import cv2
import numpy as np
from image_utils import process_image_file
app = Flask(__name__)


@app.route('/select', methods=['post'])
def select():
    length = 10
    random_string = ''.join(random.choices(string.ascii_letters + string.digits, k=length))
    argsJson = request.data.decode('utf-8')
    argsJson = json.loads(argsJson)
    dataURL = argsJson.get('image')
    data = dataURL.split(",")[1]
    binary_data = base64.b64decode(data)
    image = Image.open(BytesIO(binary_data))
    file_path1 = os.path.join("random", random_string + ".png")
    file_path2 = os.path.join("random", random_string + ".jpg")
    image.save(file_path1)
    process_image_file(file_path1)
    img2 = cv2.imdecode(np.fromfile(file_path2, dtype=np.uint8), -1)
    results = face_recognitio.recognition(img2)
    return jsonify({'result': results[0]})

@app.route('/add', methods=['post'])
def add():
    argsJson = request.data.decode('utf-8')
    argsJson = json.loads(argsJson)
    dataURL = argsJson.get('image')
    data = dataURL.split(",")[1]
    binary_data = base64.b64decode(data)
    image = Image.open(BytesIO(binary_data))
    text = argsJson.get('text')
    file_path1 = os.path.join("images", text + ".png")
    file_path2 = os.path.join("images", text + ".jpg")
    image.save(file_path1)
    process_image_file(file_path1)
    img = cv2.imdecode(np.fromfile(file_path2 , dtype=np.uint8), -1)
    face_recognitio.register(img, user_name=text)
    return jsonify({'result': 'success'})


if __name__ == '__main__':
    face_recognitio = FaceRecognition()
    app.run(host='127.0.0.1', port=5000)
