import cv2
import numpy as np

from facedome import FaceRecognition

if __name__ == '__main__':
    img = cv2.imdecode(np.fromfile('images/郑奎峰.jpg', dtype=np.uint8), -1)
    face_recognitio = FaceRecognition()
    result = face_recognitio.register(img, user_name='郑奎峰')


