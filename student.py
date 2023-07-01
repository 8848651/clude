import json
import cv2
import numpy as np
from facedome import FaceRecognition




import requests

url = 'http://47.115.202.23:8090/RLSB/add'

# 定义JSON数据
data = {
    "workerid": "id",
    "workername": "郑奎峰",
    "data": {
        "state": 10,
        "option": "20"
    }
}
# 发送POST请求
response = requests.post(url, json=data)
# 打印响应结果
print(response.text)










"""
    #face_recognitio = FaceRecognition()
    #img = cv2.imdecode(np.fromfile(file_path, dtype=np.uint8), -1)
    #results = face_recognitio.recognition(img)
    #os.remove(file_path1)

@app.route('/delete', methods=['post'])
def delete():
    length = 10
    random_string = ''.join(random.choices(string.ascii_letters + string.digits, k=length))
    argsJson = request.data.decode('utf-8')
    argsJson = json.loads(argsJson)
    dataURL = argsJson.get('image')
    data = dataURL.split(",")[1]
    binary_data = base64.b64decode(data)
    image = Image.open(BytesIO(binary_data))
    file_path = os.path.join("random", random_string + ".png")
    image.save(file_path)

    face_recognitio = FaceRecognition()
    img = cv2.imdecode(np.fromfile(file_path, dtype=np.uint8), -1)
    results = face_recognitio.recognition(img)
    os.remove(file_path)

    file_path2 = os.path.join("face_db", results[0] + ".png")
    os.remove(file_path2)
    return jsonify({'result': results[0]})
"""