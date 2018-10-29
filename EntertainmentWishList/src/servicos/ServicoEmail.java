package servicos;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import entity.Usuario;

public class ServicoEmail {

	public void servicoEmail(Usuario user) {
		SimpleEmail sp = new SimpleEmail();
		sp.setHostName("smtp.googlemail.com");
		sp.setSmtpPort(1000);
		sp.setAuthenticator(new DefaultAuthenticator("adsfateczl18@gmail.com", "ads2018fatectudo"));
		sp.setSSLOnConnect(true);
		try {
			sp.addTo(user.getEmail(), user.getPrimeiroNome() + " " + user.getSegundoNome()); // Destinario
			sp.setFrom("adsfateczl18@gmail.com");// remetente
			sp.setSubject("Sua Nova Senha");// Assunto
			sp.setMsg("Ol�/r/n" + user.getPrimeiroNome() + "" + user.getSegundoNome()
					+ " voc� solicitou a troca de senha, sua nova senha �" + user.getSenha()
					+ " realize o acesso ao site com esta nova senha e altere para a uma nova senha "
					+ "/r/n Esta mensagem � gerada automaticamente, assim como a nova senha, sua senha � segura e de responsabilidade de nossos usu�rios");// Mensagem
			System.out.println(sp.send());// enviar
		} catch (EmailException e) {
			e.printStackTrace();
		}

	}

}
