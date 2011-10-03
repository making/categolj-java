<table class="zebra-striped">
    <tr><th>KEY</th><th>VALUE</th><th>ACTION</th></tr>
    <c:forEach var="r" items="${config}">
    <tr><td>${r.key}</td><td>${r.value}</td><td><button class="btn">EDIT</button></td></tr>
    </c:forEach>
</table>