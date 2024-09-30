<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Livro</title>
</head>
<body>

<h2>Editar Livro</h2>

<form action="updateBook" method="post">
    <input type="hidden" name="id" value="${book.id}">

    <label for="name">Nome:</label>
    <input type="text" id="name" name="name" value="${book.name}" required><br><br>

    <label for="date">Data de Criação:</label>
    <input type="date" id="date" name="date" value="${book.date}" required><br><br>

    <label for="author">Autor:</label>
    <select id="author" name="authorId" required>
        <c:forEach var="author" items="${authors}">
            <option value="${author.id}" ${author.id == book.author.id ? 'selected' : ''}>${author.name}</option>
        </c:forEach>
    </select><br><br>

    <label for="status">Status:</label>
    <select id="status" name="status" required>
        <option value="DISPONIVEL" ${book.status == 'DISPONIVEL' ? 'selected' : ''}>Disponível</option>
        <option value="EMPRESTADO" ${book.status == 'EMPRESTADO' ? 'selected' : ''}>Emprestado</option>
        <option value="INDISPONIVEL" ${book.status == 'INDISPONIVEL' ? 'selected' : ''}>Indisponível</option>
    </select><br><br>

    <input type="submit" value="Atualizar Livro">
</form>

<br>
<form action="deleteBook" method="post">
    <input type="hidden" name="id" value="${book.id}" />
    <input type="submit" value="Deletar Livro" onclick="return confirm('Tem certeza que deseja deletar este livro?');"/>
</form>

<br>
<a href="index.jsp">Voltar para lista</a>

</body>
</html>
