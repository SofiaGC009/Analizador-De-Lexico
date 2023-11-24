package teoria;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AnalizadorLexico {

    private static final Map<String, Integer> TOKENS = new HashMap<>();
    private static final ArrayList<String> CARACTERES_IGNORADOS = new ArrayList<>(Arrays.asList(
             ".", " ", "TAB", "EOLN", "EOF"
    ));

    static {
        //Cambiar la direccion del archivo tokens.txt, poner la URL completa para evitar errores
        cargarTablaDesdeArchivo("C:\\Users\\sofyg\\MateriasUni\\Desarrollo\\proyecto\\src\\main\\java\\teoria\\tokens.txt");
    }

    public static void main(String[] args) {
        //Cambiar la direccion del archivo entrada.txt, poner la URL completa para evitar errores
        String archivoFuente = "C:\\Users\\sofyg\\MateriasUni\\Desarrollo\\proyecto\\src\\main\\java\\teoria\\entrada.txt";
        //Este archivo se genera en la misma carpeta donde se encuentra el proyecto.
        String archivoSalida = "tabla_tokens.txt";

        try {
            ArrayList<Token> tablaTokens = analizarCodigo(archivoFuente);
            guardarTablaEnArchivo(tablaTokens, archivoSalida);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cargarTablaDesdeArchivo(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\s+");
                if (partes.length == 2) {
                    String lexema = partes[0];
                    int numeroToken = Integer.parseInt(partes[1]);
                    TOKENS.put(lexema, numeroToken);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Token> analizarCodigo(String archivoFuente) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(archivoFuente));
        ArrayList<Token> tablaTokens = new ArrayList<>();
        String linea;
        int numeroLinea = 1;

        while ((linea = br.readLine()) != null) {
            String[] palabras = linea.split("\\s+");
            for (String palabra : palabras) {
                if (!palabra.isEmpty() && !CARACTERES_IGNORADOS.contains(palabra)) {
                    Token token = identificarToken(palabra);
                    token.setNumeroLinea(numeroLinea);
                    tablaTokens.add(token);
                }
            }

            numeroLinea++;
        }

        br.close();
        return tablaTokens;
    }

    public static Token identificarToken(String lexema) {
        Token token = new Token();
        token.setLexema(lexema);

        if (TOKENS.containsKey(lexema)) {
            token.setNumeroToken(TOKENS.get(lexema));
            token.setPosicionTabla(-1);
        } else if (esIdentificador(lexema)) {
            token.setNumeroToken(-100);  // Usar un número negativo para identificadores
            token.setPosicionTabla(-2);
        } else if (esConstanteEntera(lexema)) {
            token.setNumeroToken(-101);  // Usar un número negativo para constantes enteras
            token.setPosicionTabla(-1);
        } else if (esConstanteReal(lexema)) {
            token.setNumeroToken(-102);  // Usar un número negativo para constantes reales
            token.setPosicionTabla(-1);
        } else if (esConstanteString(lexema)) {
            token.setNumeroToken(-103);  // Usar un número negativo para constantes de cadena
            token.setPosicionTabla(-1);
        } else if (esComentario(lexema)) {
            token.setNumeroToken(-104);  // Usar un número negativo para comentarios
            token.setPosicionTabla(-1);
        } else {
            token.setNumeroToken(-105);  // Usar un número negativo para otros casos
            token.setPosicionTabla(-1);
        }

        return token;
    }

    public static boolean esIdentificador(String lexema) {
        // Identificadores: Inician con letra, y pueden continuar con letras o dígitos.
        return lexema.matches("[a-zA-Z][a-zA-Z0-9]*");
    }

    public static boolean esConstanteEntera(String lexema) {
        // Constantes Enteras: Están en el intervalo de -32768 a 32767.
        try {
            int valor = Integer.parseInt(lexema);
            return valor >= -32768 && valor <= 32767;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean esConstanteReal(String lexema) {
        // Constantes Reales: Pueden o no llevar signo. Pueden iniciar con punto decimal,
        // pero no pueden terminar con punto decimal. No permiten notación científica.
        try {
            Double.parseDouble(lexema);
            return lexema.matches("[-+]?\\d*\\.?\\d+");
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean esConstanteString(String lexema) {
        // Constante String: Van limitadas por “ al inicio y al final.
        // Un string debe terminar en la misma línea, no hay manera de continuarlo en otra línea.
            return lexema.startsWith("\"") && lexema.endsWith("\"");
        }
    
    

    public static boolean esComentario(String lexema) {
        // Comentarios: El lenguaje permite comentarios que inician con // y deben terminar en la misma línea.
        return lexema.startsWith("//");
    }

    public static void guardarTablaEnArchivo(ArrayList<Token> tablaTokens, String archivoSalida) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida))) {
            bw.write("Lexema\t\tToken\t\tNúmero de Línea\tPosicion de Tabla\n");
            bw.write("---------------------------------------------------------------\n");

            for (Token token : tablaTokens) {
                bw.write(String.format("%-20s%-20s%-20s%-20s\n",
                        token.getLexema(), token.getNumeroToken(), token.getNumeroLinea(), token.getPosicionTabla()));
            }
        }
    }

    public static class Token {
        private String lexema;
        private int numeroToken;
        private int numeroLinea;
        private int posicionTabla;

        public Token() {}

        public String getLexema() {
            return lexema;
        }

        public void setLexema(String lexema) {
            this.lexema = lexema;
        }

        public int getNumeroToken() {
            return numeroToken;
        }

        public void setNumeroToken(int numeroToken) {
            this.numeroToken = numeroToken;
        }

        public int getNumeroLinea() {
            return numeroLinea;
        }

        public void setNumeroLinea(int numeroLinea) {
            this.numeroLinea = numeroLinea;
        }

        public int getPosicionTabla() {
            return posicionTabla;
        }

        public void setPosicionTabla(int posicionTabla) {
            this.posicionTabla = posicionTabla;
        }
    }
}

