import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import teoria.AnalizadorLexico;
import teoria.AnalizadorLexico.Token;


public class AnalizadorLexicoTest {

    @Test
    public void testIdentificarToken() {
        // Coloca aquí tu lógica de prueba utilizando aserciones de JUnit
        assertTrue(true);
    }

    @Test
    public void testEsIdentificador() {
        assertTrue("La palabra 'variable' debería ser un identificador", AnalizadorLexico.esIdentificador("variable"));
        assertFalse("La palabra '123variable' no debería ser un identificador", AnalizadorLexico.esIdentificador("123variable"));
        assertFalse("La palabra '123' no debería ser un identificador", AnalizadorLexico.esIdentificador("123"));
    }

    @Test
    public void testEsConstanteEntera() {
        assertTrue("La cadena '123' debería ser una constante entera", AnalizadorLexico.esConstanteEntera("123"));
        assertFalse("La cadena '-32768' debería ser una constante entera", AnalizadorLexico.esConstanteEntera("-32768"));
        assertFalse("La cadena 'abc' no debería ser una constante entera", AnalizadorLexico.esConstanteEntera("abc"));
        assertFalse("La cadena '32768' no debería ser una constante entera", AnalizadorLexico.esConstanteEntera("32768"));
    }

    @Test
    public void testEsConstanteReal() {
        assertTrue("La cadena '123.45' debería ser una constante real", AnalizadorLexico.esConstanteReal("123.45"));
        assertTrue("La cadena '-0.123' debería ser una constante real", AnalizadorLexico.esConstanteReal("-0.123"));
        assertFalse("La cadena 'abc' no debería ser una constante real", AnalizadorLexico.esConstanteReal("abc"));
        assertFalse("La cadena '12.34.56' no debería ser una constante real", AnalizadorLexico.esConstanteReal("12.34.56"));
    }

    @Test
    public void testEsConstanteString() {
        assertTrue("Cadena entre comillas dobles debería ser una constante de cadena", AnalizadorLexico.esConstanteString("\"abc\""));
        assertTrue("Cadena numérica entre comillas dobles debería ser una constante de cadena", AnalizadorLexico.esConstanteString("\"123\""));
        assertTrue("Cadena alfanumérica entre comillas dobles debería ser una constante de cadena", AnalizadorLexico.esConstanteString("\"Sofia123\""));
        assertTrue("Cadena con espacio entre comillas dobles debería ser una constante de cadena", AnalizadorLexico.esConstanteString("\"Hola Mundo\""));
    }

    @Test
    public void testAnalizarCodigo() {
        try {
            String archivoFuente = "C:\\Users\\sofyg\\MateriasUni\\Desarrollo\\proyecto\\src\\test\\java\\entradaTest.txt"; // Ruta al archivo de prueba
            ArrayList<Token> tablaTokens = AnalizadorLexico.analizarCodigo(archivoFuente);

            // Verificar el tamaño de la tabla de tokens (ajustar según el contenido del archivo de prueba)
            assertEquals(3, tablaTokens.size());

            // Verificar algunos tokens específicos
            assertEquals("Identificador", tablaTokens.get(0).getLexema());
            assertEquals(-100, tablaTokens.get(0).getNumeroToken());
            assertEquals(1, tablaTokens.get(0).getNumeroLinea());

            assertEquals("123", tablaTokens.get(1).getLexema());
            assertEquals(-101, tablaTokens.get(1).getNumeroToken());
            assertEquals(2, tablaTokens.get(1).getNumeroLinea());

            assertEquals("\"Cadena\"", tablaTokens.get(2).getLexema());
            assertEquals(-103, tablaTokens.get(2).getNumeroToken());
            assertEquals(3, tablaTokens.get(2).getNumeroLinea());

        } catch (IOException e) {
            fail("Excepción inesperada: " + e.getMessage());
        }
    }
}


