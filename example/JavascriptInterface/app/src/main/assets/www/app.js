// JS主动调用Java
function showToastByInterface() {
    JSBridge.showToast("js主动调用java -> showToastByInterface");
}

// 触发事件
function fireDocumentEvent(name, data) {
    var event = document.createEvent('HTMLEvents');
    event.initEvent(name, true, true);
    event.arguments = data;
    document.dispatchEvent(event);
}

// 监听原生层的消息
document.addEventListener("titleUpdate", function(msg) {
    alert("title: "+ msg.arguments);
});