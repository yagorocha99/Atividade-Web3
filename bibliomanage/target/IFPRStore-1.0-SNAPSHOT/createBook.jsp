<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Criar Livro</title>
</head>
<body>

<h2>Criar Novo Livro</h2>

<form action="createBook" method="post">
    <label for="name">Nome:</label>
    <input type="text" id="name" name="name" required><br><br>

    <label for="date">Data de Criação:</label>
    <input type="date" id="date" name="date" required><br><br>

    <label for="author">Autor:</label>
    <select id="author" name="authorId" required>
        <c:forEach var="author" items="${authors}">
            <option value="${author.id}">${author.name}</option>
        </c:forEach>
    </select><br><br>

    <label for="status">Status:</label>
    <select id="status" name="status" required>
        <option value="DISPONIVEL">Disponível</option>
        <option value="EMPRESTADO">Emprestado</option>
        <option value="INDISPONIVEL">Indisponível</option>
    </select><br><br>

    <input type="submit" value="Criar Livro">
</form>

<br>
<a href="index.jsp">Voltar para lista</a>

</body>
</html>
