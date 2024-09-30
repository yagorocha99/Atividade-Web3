<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bibliomanage - Lista de Livros</title>
</head>
<body>

<h2>Lista de Livros</h2>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Autor</th>
        <th>Data de Criação</th>
        <th>Status</th>
        <th>Ações</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.author.name}</td>
            <td>${book.date}</td>
            <td>${book.status}</td>
            <td>
                <a href="updateBook?id=${book.id}">Editar</a>
                <form action="deleteBook" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="${book.id}" />
                    <input type="submit" value="Deletar" onclick="return confirm('Tem certeza que deseja deletar este livro?');"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br>
<a href="createBook">Adicionar novo livro</a>

</body>
</html>
