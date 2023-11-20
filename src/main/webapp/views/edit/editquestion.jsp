<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Вопросы голосований</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Questions</title>
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
                    <th scope="col">Голосование</th>
                    <th scope="col">Содержание</th>
                    <th scope="col">Дата голосования</th>
                    </thead>
                    <tbody>
                    <c:forEach var="question" items="${questions}">
                        <tr>
                            <td>${question.getId()}</td>
                            <td>${question.getVote().getTitle()}</td>
                            <td>${question.getContent()}</td>
                            <td>${question.getDateVote()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-4 border px-4 a-color">
                <form method="POST" action="">
                    <h3>Редактировать вопрос голосования:</h3>
                    <div class="mb-3 row">
                        <label for="idquestion"
                               class="col-sm-3 col-form-label">Код вопроса голосования</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control"
                                  readonly id="idquestion" value="${questionEdit.getId()}" />
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputVote"
                               class="col-sm-3 col-form-label">Голосование</label>
                        <div class="col-sm-7">
                            <select name="voteField" class="form-control">
                                <option value="${questionEdit.vote}">${questionEdit.vote.getTitle()}</option>
                                <c:forEach var="vote" items="${votes}">
                                    <option value="${vote}">
                                        <c:out value="${vote.getTitle()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputContent"
                               class="col-sm-3 col-form-label">Содержание вопроса</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control"
                                   id="inputContent" name="content" value="${questionEdit.getContent()}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputDateVote"
                               class="col-sm-3 col-form-label">Дата голосования</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control"
                                   id="inputDateVote" name="dateVote" value="${questionEdit.getDateVote()}"/>
                        </div>
                    </div>
                    <p> <br>
                        <button type="submit"
                                class="btn btn-primary">Редактировать</button>
                        <a href='<c:url value="/questions" />' role="button" class="btn btn-secondary">Отменить</a>
                    </p>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="/views/footer.jsp" />
</div>
</body>
</html>