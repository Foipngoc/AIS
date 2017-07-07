<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2017/2/15
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>AIS接收</title>
  </head>
  <body>
  AIS接收
    <!--<script type="text/JavaScript">
      if ('WebSocket' in window) {
        websocket = new WebSocket("ws://" + document.location.host + "/ws");
      } else {
        console.info('Not support websocket');
        alert("浏览器不支持");
      }

      websocket.onerror = function() {
        console.info("连接错误")
        setMessageInnerHTML("Connect Error!");
      };
      websocket.onopen = function() {
        console.info("连接成功");
        setMessageInnerHTML("Data Updating...");
      }
      websocket.onmessage = function(event)
      {
        var param;
        console.info("消息coming!");
        //event.data即为后台传到前台的值
        //todo想要实现的内容
      }
      //连接关闭的回调方法
      websocket.onclose = function() {
        console.info("连接关闭");
      }
      window.onbeforeunload = function() {
        console.info("监听_连接关闭");
        websocket.close();
      }
      //将消息显示在网页上
      function setMessageInnerHTML(message) {
        console.info("消息同步到网页");
        //document.getElementById('message').innerHTML = "<span id=\"blink\">" + message + "</span>";
      }
      //关闭连接
      function closeWebSocket() {
        console.info("关闭连接");
        websocket.close();
      }
    </script>-->
  </body>
</html>
