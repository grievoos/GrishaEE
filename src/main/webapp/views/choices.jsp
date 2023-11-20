<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Результаты</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Choices</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <!-- jQuery -->
    <script defer src="js/jquery.min.js"></script>
    <!-- Bootstrap JS + Popper JS -->
    <script defer src="js/bootstrap.min.js"></script>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.mi
n.js"></script>
</head>
<body>
<div class="container-fluid back-container">
    <jsp:include page="/views/header.jsp" />
    <div class="container-fluid back-container">
        <div class="row justify-content-start ">
            <div class="col-8 border bg-light px-4">
                <h3>Результаты:</h3>
                <table class="table">
                    <thead>
                    <th scope="col">Код</th>
                    <th scope="col">Вопрос голосования</th>
                    <th scope="col">Голосующий</th>
                    <th scope="col">Голос</th>
                    <th scope="col">Редактировать</th>
                    <th scope="col">Удалить</th>
                    </thead>
                    <tbody>
                    <c:forEach var="choice" items="${choices}">
                        <tr>
                            <td>${choice.getId()}</td>
                            <td>${choice.getQuestion().getContent()}</td>
                            <td>${choice.getUser().getSecondName()}</td>
                            <td>${choice.getUserChoice()}</td>
                            <td width="20"><a href="<c:url value="/editChoice?id=${choice.getId()}" />" role="button"
                                              class="btn btn-outline-primary">
                                <img alt="Редактировать"
                                     src="img/edit.png"></a></td>
                            <td width="20"><a href="<c:url value="/deleteChoice?id=${choice.getId()}"/>" role="button"
                                              class="btn btn-outline-primary">
                                <img alt="Удалить"
                                     src="img/delete.png" onclick="return confirm('Удалить голос с ID: ' +${choice.getId()} + '?')"></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-4 border px-4 a-color">
                <form method="POST" action="">
                    <h3>Результаты:</h3>
                    <br>
                    <div class="mb-3 row">
                        <label for="inputQuestion"
                               class="col-sm-3 col-form-label">Вопрос голосования</label>
                        <div class="col-sm-7">
                            <select name="questions" class="form-control">
                                <option>Выберите вопрос голосования</option>
                                <c:forEach var="question" items="${questions}">
                                    <option value="${question}">
                                        <c:out value="${question.getContent()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputUser"
                               class="col-sm-3 col-form-label">Голосующий</label>
                        <div class="col-sm-7">
                            <select name="users" class="form-control">
                                <option>Выберите голосующего</option>
                                <c:forEach var="user" items="${users}">
                                    <option value="${user}">
                                        <c:out value="${user.getSecondName()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputUserStatus"
                               class="col-sm-3 col-form-label">Голос(За/Против/Не голосовал)</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control"
                                   id="inputUserStatus" name="userStatus" />
                        </div>
                    </div>
                    <p> <br>
                        <button type="submit"
                                class="btn btn-primary">Добавить</button>
                    </p>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="/views/footer.jsp" />
</div>
</body>
</html>