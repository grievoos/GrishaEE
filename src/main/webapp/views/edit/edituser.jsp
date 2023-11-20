<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Голосующие</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Users</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <!-- jQuery -->
    <script defer src="js/jquery-3.6.4.js"></script>
    <!-- Bootstrap JS + Popper JS -->
    <script defer src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid back-container">
    <jsp:include page="/views/header.jsp" />
    <div class="container-fluid back-container">
        <div class="row justify-content-start ">
            <div class="col-8 border bg-light px-4">
                <h3>Список голосующих:</h3>
                <table class="table">
                    <thead>
                    <th scope="col">Код</th>
                    <th scope="col">Фамилия</th>
                    <th scope="col">Имя</th>
                    <th scope="col">Отчество</th>
                    <th scope="col">Почта</th>
                    <th scope="col">Номер телефона</th>
                    <th scope="col">Статус</th>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.getId()}</td>
                            <td>${user.getSecondName()}</td>
                            <td>${user.getFirstName()}</td>
                            <td>${user.getLastName()}</td>
                            <td>${user.getEmail()}</td>
                            <td>${user.getPhone()}</td>
                            <td>${user.getUserStatus()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-4 border px-4 a-color" >
                <form method="POST" action="">
                    <h3 class="a-color">Редактировать голосующего</h3>
                    <div class="mb-3">
                        <label for="iduser"
                               class="col-sm-3 col-form-label">Код голосующего</label>
                        <div class="col-sm-6">
                            <input type="text"
                                   class="form-control" readonly id="iduser" value="${userEdit.getId()}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputSecondName"
                                    class="col-sm-3 col-form-label">Фамилия</label>
                        <div class="col-sm-6">
                            <input type="text" name="secondName"
                                   class="form-control" id="inputSecondName" value="${userEdit.getSecondName()}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputFirstName"
                               class="col-sm-3 col-form-label">Имя</label>
                        <div class="col-sm-6">
                            <input type="text" name="firstName"
                                   class="form-control" id="inputFirstName" value="${userEdit.getFirstName()}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputLastName"
                               class="col-sm-3 col-form-label">Отчество</label>
                        <div class="col-sm-6">
                            <input type="text" name="lastName"
                                   class="form-control" id="inputLastName" value="${userEdit.getLastName()}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputEmail"
                               class="col-sm-3 col-form-label">Электронная почта</label>
                        <div class="col-sm-6">
                            <input type="text" name="email"
                                   class="form-control" id="inputEmail" value="${userEdit.getEmail()}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputPhone"
                               class="col-sm-3 col-form-label">Номер телефона</label>
                        <div class="col-sm-6">
                            <input type="text" name="phone"
                                   class="form-control" id="inputPhone" value="${userEdit.getPhone()}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputStatusUser"
                               class="col-sm-3 col-form-label">Статус голосующего(голосовал(а), не голосовал(а))</label>
                        <div class="col-sm-6">
                            <input type="text" name="statusUser"
                                   class="form-control" id="inputStatusUser" value="${userEdit.getUserStatus()}"/>
                        </div>
                    </div>
                    <p>
                        <br> <br> <br>
                        <button type="submit"
                                class="btn btn-primary">Редактировать</button>
                        <a href='<c:url value="/users" />' role="button" class="btn btn-secondary">Отменить</a>
                        <br>
                    </p>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="/views/footer.jsp" />
</div>
</body>
</html>