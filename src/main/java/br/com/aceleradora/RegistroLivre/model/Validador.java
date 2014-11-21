package br.com.aceleradora.RegistroLivre.model;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class Validador {

	public static List<Socio> retiraSociosNulos(List<Socio> socios) {
		for (int i = 0; i < socios.size(); i++) {
			if (socios.get(i).getNome() == null
					&& socios.get(i).getCpf() == null) {

				socios.remove(i);
				i = 0;
			}
		}
		return socios;
	}

	public static boolean verificaCpfListaSocio(List<Socio> socios) {
		for (Socio socio : socios) {
			if (socio.getCpf() != null) {
				if (!verificaCpf(socio.getCpf())) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean verificaCpf(String cpf) {
		char digito1, digito2;
		int soma, resto, numero, peso;

		String cpfSemCaracteresEspeciais = cpf.replaceAll("[.-]", "");

		if (cpfSemCaracteresEspeciais.equals("00000000000")
				|| cpfSemCaracteresEspeciais.equals("11111111111")
				|| cpfSemCaracteresEspeciais.equals("22222222222")
				|| cpfSemCaracteresEspeciais.equals("33333333333")
				|| cpfSemCaracteresEspeciais.equals("44444444444")
				|| cpfSemCaracteresEspeciais.equals("55555555555")
				|| cpfSemCaracteresEspeciais.equals("66666666666")
				|| cpfSemCaracteresEspeciais.equals("77777777777")
				|| cpfSemCaracteresEspeciais.equals("88888888888")
				|| cpfSemCaracteresEspeciais.equals("99999999999")
				|| (cpfSemCaracteresEspeciais.length() != 11)) {
			return false;
		}

		soma = 0;
		peso = 10;

		for (int index = 0; index < 9; index++) {
			numero = (int) (cpfSemCaracteresEspeciais.charAt(index) - 48);
			soma = soma + (numero * peso);
			peso = peso - 1;
		}

		resto = 11 - (soma % 11);

		if ((resto == 10) || (resto == 11)) {
			digito1 = '0';
		} else {
			digito1 = (char) (resto + 48);
		}

		soma = 0;
		peso = 11;

		for (int index = 0; index < 10; index++) {
			numero = (int) (cpfSemCaracteresEspeciais.charAt(index) - 48);
			soma = soma + (numero * peso);
			peso = peso - 1;
		}

		resto = 11 - (soma % 11);

		if ((resto == 10) || (resto == 11)) {
			digito2 = '0';
		} else {
			digito2 = (char) (resto + 48);
		}

		if ((digito1 == cpfSemCaracteresEspeciais.charAt(9))
				&& (digito2 == cpfSemCaracteresEspeciais.charAt(10))) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean verificaCnpj(String cnpj) {

		if (cnpj == null) {
			return false;
		}

		cnpj = cnpj.replaceAll("[./-]", "");

		if (cnpj == "") {
			return false;
		}

		if (cnpj.length() != 14) {
			return false;
		}

		if (cnpj == "00000000000000" || cnpj == "11111111111111"
				|| cnpj == "22222222222222" || cnpj == "33333333333333"
				|| cnpj == "44444444444444" || cnpj == "55555555555555"
				|| cnpj == "66666666666666" || cnpj == "77777777777777"
				|| cnpj == "88888888888888" || cnpj == "99999999999999") {
			return false;
		}

		int tamanho = cnpj.length() - 2;
		String numeros = cnpj.substring(0, tamanho);
		String digitos = cnpj.substring(tamanho);

		int soma = 0;
		int pos = tamanho - 7;
		for (int i = tamanho; i >= 1; i--) {
			soma += Integer.parseInt(Character.toString(numeros.charAt(tamanho
					- i)))
					* pos--;

			if (pos < 2) {
				pos = 9;
			}
		}

		int resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;

		if (resultado != Integer
				.parseInt(Character.toString(digitos.charAt(0)))) {
			return false;
		}

		tamanho = tamanho + 1;
		numeros = cnpj.substring(0, tamanho);
		soma = 0;
		pos = tamanho - 7;

		for (int i = tamanho; i >= 1; i--) {
			soma += Integer.parseInt(Character.toString(numeros.charAt(tamanho
					- i)))
					* pos--;

			if (pos < 2) {
				pos = 9;
			}
		}

		resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
		if (resultado != Integer
				.parseInt(Character.toString(digitos.charAt(1)))) {
			return false;
		}

		return true;

	}

	public static boolean verificaNomeFantasia(String nomeFantasia) {
		if (nomeFantasia == null) {
			return false;
		}

		if (nomeFantasia.length() <= 1) {
			return false;
		}

		return true;
	}

	public static boolean verificaNumeroEndereco(String numero) {
		if (numero != null) {
			return numero.matches("[0-9]+");
		}

		return true;
	}

	public static boolean verificaExtensaoArquivo(String nomeArquivo) {
		if (nomeArquivo == null) {
			return false;
		}

		if (!nomeArquivo.contains(".pdf")) {
			return false;
		}

		return true;
	}
}
