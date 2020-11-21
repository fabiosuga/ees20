package br.com.suga.util;

public class Util {

    private static final int MAX_ZEROS = 8;

    public static String getNumPedidoFormatado(Integer numero) {
        String retorno = "";
        if (numero == null) {
            retorno = "00000000";
            return retorno;
        }

        retorno = numero.toString();

        while (retorno.length() < MAX_ZEROS) {
            retorno = "0" + retorno;
        }

        return retorno;
    }

    public static String formatarCpf(String numero) {
        if (numero == null || numero.isEmpty()) {
            return "000.000.000-00";
        }
        if (numero.contains(".") && numero.contains("-")) {
            return numero;
        }

        String zeros = "00000000000";
        int tamanho = numero.length();
        String auxiliar = numero;

        if (tamanho < 11) {
            auxiliar = zeros.substring(0, 11 - tamanho) + numero;
        }

        StringBuffer cpf = new StringBuffer();
        cpf.append(auxiliar.substring(0, 3));
        cpf.append(".");
        cpf.append(auxiliar.substring(3, 6));
        cpf.append(".");
        cpf.append(auxiliar.substring(6, 9));
        cpf.append("-");
        cpf.append(auxiliar.substring(9));

        return cpf.toString();
    }
}
