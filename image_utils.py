import os
from PIL import Image
import cv2
import numpy as np

def rgba_to_rgb(img):
    if img.shape[2] == 4:
        # 提取 alpha 通道
        alpha = img[:, :, 3]
        # 创建一个与 RGB 图像形状相同的纯白色图像
        white = np.ones_like(img[:, :, :3], dtype=np.uint8) * 255
        # 将 alpha 通道应用于纯白色图像
        white = cv2.bitwise_and(white, white, mask=alpha)
        # 将 RGB 图像与 alpha 通道应用的图像合并
        img = cv2.cvtColor(img[:, :, :3], cv2.COLOR_RGB2BGR)
        img = cv2.cvtColor(cv2.addWeighted(white, 1.0, img, 1.0, 0.0), cv2.COLOR_BGR2RGB)
    return img

def process_image_file(file_path):
    # 打开图像文件并将其转换为RGB模式
    img = Image.open(file_path)
    if img.mode == 'RGBA':
        img = img.convert('RGB')
    # 将转换后的图像保存为JPEG文件
    file_path2 = os.path.splitext(file_path)[0] + ".jpg"
    img.save(file_path2, 'JPEG')
    img2 = cv2.imdecode(np.fromfile(file_path2, dtype=np.uint8), -1)
    if img2.shape[2] == 4:
        img2 = rgba_to_rgb(img2)
    os.remove(file_path)
    return img2