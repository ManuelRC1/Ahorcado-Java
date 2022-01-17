package es.manuel.ahorcado25;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Ahorcado", value = "/Ahorcado")
public class Ahorcado extends HttpServlet {
    static String HayTildes(String vocales) {
        return vocales.replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession(true);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();

        String PalabraFallada = (String) sesion.getAttribute("PalabraFallo");
        String id = (String) sesion.getAttribute("id");
        String[] numeros = {"cero", "uno", "dos", "tres", "cuatro", "cinco", "seis"};
        String[] diccionario = {"árbol", "avión", "camión", "gato", "perro", "helipuerto"};

        String PalFallo = "";
        String eleccion = null;
        boolean error = false;
        String palabraJugada;


        if (sesion.getAttribute("palabra") == null) {

            sesion.setAttribute("contador", "0");
            sesion.setAttribute("id", "cero");
            String letra = request.getParameter("letra");
            String letraToLowerCase = letra.toLowerCase();
            sesion.setAttribute("letra", letraToLowerCase);

            for (int i = 0; i < diccionario.length; i++) {
                int random = (int) (Math.random() * diccionario.length);
                eleccion = diccionario[random];
            }

            sesion.setAttribute("palabra", eleccion);
            String contador = (String) sesion.getAttribute("contador");
            String palabra = (String) sesion.getAttribute("palabra");
            String palabraTilde = HayTildes(palabra);

            palabraJugada = "";
            int contadorr = 0;

            if (palabraTilde.contains(letraToLowerCase)) {
                id = (String) sesion.getAttribute("id");
                for (int i = 0; i < palabraTilde.length(); i++) {
                    palabraJugada = palabraJugada + "_";
                }
                char[] palabraJugadaChar = palabraJugada.toCharArray();
                char[] palabraChar = palabraTilde.toCharArray();
                char letraChar = letraToLowerCase.charAt(0);


                for (int i = 0; i < palabraChar.length; i++) {
                    if (palabraChar[i] == letraChar) {
                        palabraJugadaChar[i] = letraChar;
                    }
                }
                String palabraCl = "";
                for (int i = 0; i < palabraJugadaChar.length; i++) {
                    palabraCl = palabraCl + palabraJugadaChar[i];
                }
                sesion.setAttribute("PalabraCl", palabraCl);

            } else {
                PalFallo = PalFallo + " " + letra;
                contadorr = Integer.parseInt(contador);
                contadorr++;
                id = numeros[contadorr];
                sesion.setAttribute("PalabraFallo", PalFallo);
            }
            sesion.setAttribute("id", id);
            String valor = String.valueOf(contadorr);
            sesion.setAttribute("contador", valor);


        } else {


            String palabra = (String) sesion.getAttribute("palabra");
            palabraJugada = (String) sesion.getAttribute("PalabraCl");
            String contador = (String) sesion.getAttribute("contador");
            String PalabraFallo = (String) sesion.getAttribute("PalabraFallo");
            String letra = request.getParameter("letra");
            String letraToLowerCase = letra.toLowerCase();
            sesion.setAttribute("letra", letraToLowerCase);
            String palabraTilde = HayTildes(palabra);

            if (palabraTilde.contains(letraToLowerCase)) {
                sesion.getAttribute("PalabraFallo");
                if (palabraJugada == null) {
                    palabraJugada = "";
                    for (int i = 0; i < palabraTilde.length(); i++) {
                        palabraJugada = palabraJugada + "_";
                    }
                }

                char[] palabraJugadaChar = palabraJugada.toCharArray();
                char[] palabraChar = palabraTilde.toCharArray();
                char letraChar = letraToLowerCase.charAt(0);


                for (int i = 0; i < palabraChar.length; i++) {
                    if (palabraChar[i] == letraChar) {
                        palabraJugadaChar[i] = letraChar;
                    }
                }

                String palabraCl = "";
                for (int i = 0; i < palabraJugadaChar.length; i++) {
                    palabraCl = palabraCl + palabraJugadaChar[i];
                }

                sesion.setAttribute("PalabraCl", palabraCl);


            } else {
                if (PalabraFallo == null) {
                    String PalVac = " ";
                    PalabraFallo = PalVac + letra;
                } else {
                    PalabraFallo = PalabraFallo + " " + letra;
                }



                int contador1 = Integer.parseInt(contador);
                contador1++;
                id = numeros[contador1];
                String valor = String.valueOf(contador1);
                sesion.getAttribute("PalabraFallo");
                sesion.getAttribute("PalabraCl");
                sesion.setAttribute("contador", valor);
                sesion.setAttribute("id", id);
                sesion.setAttribute("vacio", " ");

                sesion.setAttribute("PalabraFallo", PalabraFallo);
            }
        }
        sesion.setAttribute("vacio", " ");
        response.sendRedirect("ahorcado.jsp");
    }
}
