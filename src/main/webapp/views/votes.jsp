<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Голосования</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Vote</title>
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
                <h3>Список голосований:</h3>
                <table class="table">
                    <thead>
                    <th scope="col">Код</th>
                    <th scope="col">Тема</th>
                    <th scope="col">Начало</th>
                    <th scope="col">Конец</th>
                    <th scope="col">Статус</th>
                    <th scope="col">Редактировать</th>
                    <th scope="col">Удалить</th>
                    </thead>
                    <tbody>
                    <c:forEach var="vote" items="${votes}">
                        <tr>
                            <td>${vote.getId()}</td>
                            <td>${vote.getTitle()}</td>
                            <td>${vote.getDateStart()}</td>
                            <td>${vote.getDateFinish()}</td>
                            <td>${vote.getStatus()}</td>
                            <td width="20"><a href="<c:url value="/editVote?id=${vote.getId()}" />" role="button"
                                              class="btn btn-outline-primary">
                                <img alt="Редактировать"
                                     src="img/edit.png"></a></td>
                            <td width="20"><a href="<c:url value="/deleteVote?id=${vote.getId()}"/>" role="button"
                                              class="btn btn-outline-primary">
                                <img alt="Удалить"
                                     src="img/delete.png" onclick="return confirm('Удалить голосование с ID: ' +${vote.getId()} + '?')"></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-4 border px-4 a-color">
                <form method="POST" action="">
                    <h3>Новое голосование</h3>
                    <div class="mb-3">
                        <br> <label for="inputTitle"
                                    class="col-sm-3 col-form-label">Тема голосования</label>
                        <div class="col-sm-6">
                            <input type="text" name="title"
                                   class="form-control" id="inputTitle" />
                        </div>
                        <label for="inputDateStart"
                               class="col-sm-3 col-form-label">Дата начала голосования</label>
                        <div class="col-sm-6">
                            <input type="text" name="dateStart"
                                   class="form-control" id="inputDateStart" />
                        </div>
                        <label for="inputDateFinish"
                               class="col-sm-3 col-form-label">Дата конца голосования</label>
                        <div class="col-sm-6">
                            <input type="text" name="dateFinish"
                                   class="form-control" id="inputDateFinish" />
                        </div>
                        <label for="inputStatus"
                               class="col-sm-3 col-form-label">Статус голосования(Запланирован, в процессе, завершен)</label>
                        <div class="col-sm-6">
                            <input type="text" name="status"
                                   class="form-control" id="inputStatus" />
                        </div>
                    </div>
                    <p>
                        <br> <br> <br>
                        <button type="submit"
                                class="btn btn-primary">Добавить</button>
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