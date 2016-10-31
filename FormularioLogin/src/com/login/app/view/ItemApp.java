package com.login.app.view;

import com.login.app.model.Produto;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ItemApp extends Application{

	private AnchorPane painel;
	private Scene cena;
	private ImageView imageView;
	private Label lbPreco, lbDescricao;
	private Button btCarrinho;
	
	private static Produto produto;
	private static Stage stage;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		initComponentes();
		initListeners();
		
		stage.setScene(cena);
		stage.setResizable(false);
		stage.setTitle("Vitrine Produtos");
		stage.show();
		
		initLayout();
		
		setStage(stage);
	}
	
	

	private void initLayout() {
		
		imageView.setLayoutX((painel.getWidth() - 400) / 2);
		imageView.setLayoutY(20);
		
		lbPreco.setLayoutX(100);
		lbPreco.setLayoutY(350);
		
		lbDescricao.setLayoutX(250);
		lbDescricao.setLayoutY(350);
		
		btCarrinho.setLayoutX((painel.getWidth() - btCarrinho.getWidth()) / 2);
		btCarrinho.setLayoutY(400);
		
	}

	private void initListeners() {
		// TODO Auto-generated method stub
	}

	private void initComponentes() {
		
		painel = new AnchorPane();
		painel.setPrefSize(600, 450);
		
		cena = new Scene(painel);
		
		
		Image image = new Image(produto.getPathImg(),450, 300, false, false);
//		Image image = new Image("file:resources/img-1.png",450, 300, false, false);
		
		imageView = new ImageView(image);
		
		lbPreco = new Label();
		lbPreco.setText("Preco: " + String.valueOf(produto.getPreco()));
		
		lbDescricao = new Label();
		lbDescricao.setText("Descrição: " + produto.getProduto());
		
		btCarrinho = new Button("Add Carrinho");
		
		painel.getChildren().addAll(imageView,lbPreco,lbDescricao,btCarrinho);
	}



	public static Produto getProduto() {
		return produto;
	}

	public static void setProduto(Produto produto) {
		ItemApp.produto = produto;
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		ItemApp.stage = stage;
	}
	
	
	/*public static void main(String[] args) {
		launch(args);
	}*/
}
