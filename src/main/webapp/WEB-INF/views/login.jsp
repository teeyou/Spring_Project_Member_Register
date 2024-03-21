<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<form action="/member/login" method="post">
    <input type="text" name="email" placeholder="이메일">
    <input type="text" name="password" placeholder="비밀번호">
    <p></p>
    <input type="text" name="memberEmail" placeholder="이메일">
    <input type="text" name="memberPassword" placeholder="비밀번호">
    <p></p>
    <input type="submit" value="로그인">
</form>
</body>
</html>