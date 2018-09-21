package controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ImagesView {

	private List<String> images;

	@PostConstruct
	public void init() {
		images = new ArrayList<String>();
		for (int i = 1; i <= 25; i++) {
			images.add("https://img.estadao.com.br/resources/jpg/0/1/1531082746710.jpg");
		}
	}

	public List<String> getImages() {
		return images;
	}
	
	public void imprimir() {
		System.out.println("oi");
	}
}