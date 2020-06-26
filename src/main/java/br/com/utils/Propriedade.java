package br.com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Propriedade {

	public enum EnumPropriedade {
		Auth, Endpoint;
	}

	private static final String AUTH = "auth.properties";
	private static final String ENDPOINT = "endpoint.properties";

	private Properties props;
	private String arquivo;

	public Propriedade(EnumPropriedade tipo) {

		String path = "C:\\Users\\jmartin\\Documents\\Workspace - Eclipse\\Workspace - Spring Boot\\Lojinha\\backend\\properties\\";

		switch (tipo) {
		case Auth:
			arquivo = AUTH;
			try {
				FileInputStream fis = new FileInputStream(path + arquivo);
				props = new Properties();
				props.load(fis);
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case Endpoint:
			arquivo = ENDPOINT;
			try {
				FileInputStream fis = new FileInputStream(path + arquivo);
				props = new Properties();
				props.load(fis);
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		default:
			System.out.println("Tipo de properties invalida");
			break;
		}

	}

	public String getArquivo() {
		return arquivo;
	}

	public String getValor(String propriedade) {
		return props.getProperty(propriedade);

	}

}
