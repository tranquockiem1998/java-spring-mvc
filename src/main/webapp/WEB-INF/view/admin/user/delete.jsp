<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Delete Users ${id}</title>
                <!-- Latest compiled and minified CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <!-- Latest compiled JavaScript -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <!-- <link rel="stylesheet" href="/css/demo.css"> -->
            </head>

            <body>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-md-6 col-12 mx-auto">
                            <h1>Delete user ${id}</h1>
                            <hr>
                            <div class="alert alert-danger mb-3" role="alert">
                                Are you sure you want to delete this user?
                            </div>
                            <form:form method="post" action="/admin/user/delete" modelAttribute="newUser">
                                <div style="display: none;">
                                    <label class="form-label">Id: </label>
                                    <form:input type="text" class="form-control" path="id" value="${id}" />
                                </div>
                                <button type="submit" class="btn btn-danger mt-1">Confirm</button>
                            </form:form>
                        </div>


                    </div>
                </div>
            </body>

            </html>