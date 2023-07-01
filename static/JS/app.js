
    new Vue({
        el: '#app',
        data: {
            saveImage: false, // 是否保存当前图像
            cameraStarted: false, // 是否已经启动摄像头
            inputText: ''
        },
        mounted() {
            // 初始化视频元素
            var video = this.$refs.video;
            video.setAttribute('autoplay', '');
            video.setAttribute('muted', '');
            video.setAttribute('playsinline', '');
        },
        methods: {
            startCamera() {
                // 获取视频流，并将其显示在视频窗口中
                navigator.mediaDevices.getUserMedia({ video: true })
                    .then(function(stream) {
                        var video = this.$refs.video;
                        video.srcObject = stream;
                        video.play();
                        this.cameraStarted = true;
                    }.bind(this))
                    .catch(function(err) {
                        console.log("获取视频流失败：" + err);
                    });
            },
            addSelect() {
                // 确保摄像头已经启动
                if (!this.cameraStarted) {
                    alert("请先启动摄像头");
                    return;
                }
                this.saveImage = true; // 设置保存当前图像
                this.uploadImage("http://127.0.0.1/select");
                //this.convertToImage();
                 this.stopCamera();
            },
            addDelete() {
                // 确保摄像头已经启动
                if (!this.cameraStarted) {
                    alert("请先启动摄像头");
                    return;
                }
                this.saveImage = true; // 设置保存当前图像
                this.uploadImage("http://127.0.0.1/delete");
                 this.stopCamera();
            },
            addAdd() {
                // 确保摄像头已经启动
                if (!this.cameraStarted) {
                    alert("请先启动摄像头");
                    return;
                }
                this.saveImage = true; // 设置保存当前图像
                this.uploadImage("http://127.0.0.1/add");
                 this.stopCamera();
            },
           stopCamera() {
                // 关闭摄像头
                var video = this.$refs.video;
                var stream = video.srcObject;
                if (stream) {
                    var tracks = stream.getTracks();
                    tracks.forEach(function(track) {
                    track.stop();
                });
                video.srcObject = null;
                this.cameraStarted = false;
                }
            },
            uploadImage(url) {
                if (this.saveImage) {
                    // 将保存当前图像的变量设置为 false，保证只保存当前图像
                    this.saveImage = false;
                    var canvas = this.$refs.canvas;
                    var video = this.$refs.video;
                    var context = canvas.getContext('2d');
                    // 将视频窗口中的图像绘制到canvas中
                    context.drawImage(video, 0, 0, canvas.width, canvas.height);
                    // 在 canvas 中绘制边框
                    context.strokeStyle = 'red';
                    context.lineWidth = 5;
                    context.strokeRect(0, 0, canvas.width, canvas.height);
                    // 将canvas中的图像转换为二进制数据
                    var dataURL = canvas.toDataURL();
                    // 将二进制数据发送到后端服务器
                    axios({
                        method: "post",
                        url: url,
                        data: {
                            image: dataURL,
                            text: this.inputText
                        }
                    }).then(function(response) {
                        alert(response.data.result);
                    }).catch(function(error) {
                        console.log("上传失败：" + error);
                    });
                }
            }
        }
    });
