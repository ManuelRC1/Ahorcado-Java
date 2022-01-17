<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.Objects" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Title</title>

    <style>
        #seis {
            width: 78.15px;
            height: 92px;
            border: 1px black solid;
            background: url('imagenes/ahorcados.jpg') 0 0;
        }

        #cinco {
            width: 78.15px;
            height: 92px;
            border: 1px black solid;
            background: url('imagenes/ahorcados.jpg') -78.15px 0;
        }

        #cuatro {
            width: 78.15px;
            height: 92px;
            border: 1px black solid;
            background: url('imagenes/ahorcados.jpg') -156.30px 0;
        }

        #tres {
            width: 78.15px;
            height: 92px;
            border: 1px black solid;
            background: url('imagenes/ahorcados.jpg') -234.45px 0;
        }

        #dos {
            width: 78.15px;
            height: 92px;
            border: 1px black solid;
            background: url('imagenes/ahorcados.jpg') -312.60px 0;
        }

        #uno {
            width: 78.15px;
            height: 92px;
            border: 1px black solid;
            background: url('imagenes/ahorcados.jpg') -390.75px 0;
        }

        #cero {

            width: 78.15px;
            height: 92px;
            border: 1px black solid;
            background: url('imagenes/ahorcados.jpg') -468.90px 0;
        }

        .imagen {
            margin: 20px auto;
            display: block;

        }

        .entrada {
            width: 100%;
            height: 100px;
            background: grey;
            text-align: center;
        }

        .Palabra {
            margin: 30px auto;
            text-align: center;

        }

        body {
            background: lightgrey;
        }

        h1 {
            margin: 0 auto;
        }


    </style>
</head>
<body>


<%
    HttpSession sesion = request.getSession(true);
    PrintWriter pw = response.getWriter();
%>


<form method="post" action="Ahorcado">
    <div class="container-fluid">
        <div class="row entrada">
            <h1>Juego del ahorcado</h1>
        </div>
        <div class="row">
            <div class="col-12">
                <c:choose>
                    <c:when test="${palabra == null}">
                        <img src="imagenes/FFFFFF.png" id="cero" class="imagen">
                    </c:when>
                    <c:otherwise>
                        <img src="imagenes/FFFFFF.png" id="${id}" class="imagen">
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="row Palabra">
            <div class="col-12">
                <c:choose>
                    <c:when test="${PalabraFallo == null}">
                        <h3>Palabras erroneas: ${vacio}</h3>

                    </c:when>
                    <c:otherwise>
                        <h3>Palabras erroneas${PalabraFallo}</h3>

                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <div class="row Palabra">
            <div class="col-12">
                <c:choose>
                    <c:when test="${PalabraCl == null}">
                        <h3>La palabra es:</h3>
                    </c:when>
                    <c:otherwise>
                        <h3>La palabra es: ${PalabraCl}</h3>

                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="row Palabra">
            <div class="col-12">
                <c:if test="${PalabraCl != null}">
                    <c:if test="${!PalabraCl.contains('_')}">   
                        <%
                            pw.println("<form action=\"ahorcado.jsp\">");
                            pw.println("<h3 >Ha ganado. ¿Quieres volver a jugar?<p/>");
                            sesion.invalidate();
                            pw.println("<input type=\"submit\" value=\"volver a jugar\"></button>");
                            pw.println("</form>");
                        %>
                    </c:if>
                </c:if>
                <c:if test="${contador == 6}">
                    <%
                        pw.println("<form action=\"Ahorcado.jsp\">");
                        pw.println("<h3>perdiste. La palabra era " );%> ${PalabraCl}
                    <%
                        sesion.invalidate();
                        pw.println("<input type=\"submit\" value=\"volver a jugar\"></button>");
                        pw.println("</form>");
                    %>
                </c:if>
            </div>
        </div>
        <div class="row Palabra">
            <div class="col-12">
                <input type="text" name="letra" required autofocus maxlength="1">
                <input class="parrafo" type="submit" value="Probar">
            </div>
        </div>
    </div>
</form>

</body>
</html>