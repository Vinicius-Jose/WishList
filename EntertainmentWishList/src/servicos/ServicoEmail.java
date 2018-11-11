package servicos;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import entity.Codigo;

public class ServicoEmail {

	public void servicoEmail(Codigo codigo) {
		SimpleEmail sp = new SimpleEmail();
		sp.setHostName("smtp.googlemail.com");
		sp.setSmtpPort(1000);
		sp.setAuthenticator(new DefaultAuthenticator("wishListRVH@gmail.com", "perdi2018"));
		sp.setSSLOnConnect(true);
		try {
			sp.addTo(codigo.getUsuario()); // Destinario
			sp.setFrom("wishListRVH@gmail.com");// remetente
			sp.setSubject("Sua Nova Senha");// Assunto
			sp.setMsg("Ol� " + codigo.getUsuario() 
					+ " voc� solicitou a um c�digo de segura�a para a troca de sua senha: seu c�digo �: "
					+ codigo.getCodigo()
					+ " realize o acesso ao site com este codigo na se��o recuperar senha e altere para a uma nova senha "
					+ "Esta mensagem � gerada automaticamente, assim como o c�digo. Sua senha � armazenada de forma criptografada garantindo assim maio seguran�a aos nossos usu�rios. Caso n�o tenha solicitado uma troca de senha, entre em contato com a nossa equipe atrav�s do email wishList@gmail.com");// Mensagem
			System.out.println(sp.send());// enviar
		} catch (EmailException e) {
			e.printStackTrace();
		}

	}

}
