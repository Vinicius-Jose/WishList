package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

import entity.Usuario;

@ManagedBean
@SessionScoped
public class FileUploadUserBean {

	private UploadedFile file ;

	@ManagedProperty(value = "#{usuarioBean.usuarioLogado}")
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void upload() {
		FacesMessage message = new FacesMessage("Info", file.getFileName() + " foi carregado");
		FacesContext.getCurrentInstance().addMessage(null, message);

		BufferedImage imagem;
		try {
			imagem = ImageIO.read(file.getInputstream());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(imagem, "jpg", baos);
			usuario.setFoto(baos.toByteArray());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public DefaultStreamedContent getImagemUsuario() throws IOException {
		if (usuario.getFoto() != null) {
			return new DefaultStreamedContent(new ByteArrayInputStream(usuario.getFoto()), "image/jpg");
		} else {
			return null;
		}
	}

}