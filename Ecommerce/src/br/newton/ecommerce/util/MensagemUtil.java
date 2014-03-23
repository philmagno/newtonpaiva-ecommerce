package br.newton.ecommerce.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class MensagemUtil {

	private static final String PACOTE_MENSAGENS = "br.gsfarma.mensagem.mensagens";

	public static String getMensagem(String propriedade) {
		ResourceBundle bundle = ResourceBundle.getBundle(MensagemUtil.PACOTE_MENSAGENS);
		return bundle.getString(propriedade);
	}

	public static String getMensagem(String propriedade, Object... parametros) {
		String mensagem = getMensagem(propriedade);
		MessageFormat formatter = new MessageFormat(mensagem);
		return formatter.format(parametros);
	}

}
