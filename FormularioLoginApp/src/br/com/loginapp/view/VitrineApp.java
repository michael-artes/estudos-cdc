package br.com.loginapp.view;

import java.util.List;
import java.util.stream.Collectors;

import com.login.app.controller.VitrineController;
import com.login.app.model.Produto;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VitrineApp extends Application{

	private AnchorPane painel;
	private Scene cena;
	private TextField txPesquisa;
	private TableView<ItensProperty> tbVitrine;
	private TableColumn<ItensProperty, String> colunaProduto;
	private TableColumn<ItensProperty, Double> colunaPreco;
	
	private static ObservableList<ItensProperty> listItens = FXCollections.observableArrayList();
	
//	private static CarrinhoController carrinhoController;
	
	private static Stage stage;
	
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage stage) {
		VitrineApp.stage = stage;
	}


	@Override
	public void start(Stage stage) throws Exception {
		
		initComponentes();
		initItens();
		initListeners();
		
		stage.setScene(cena);
		stage.setResizable(false);
		stage.setTitle("Vitrine Produtos");
		stage.show();
		
		initLayout();
		
		setStage(stage);
		
	}
	
	
	private void initLayout() {

		txPesquisa.setLayoutX((painel.getWidth() - txPesquisa.getWidth()) / 2);
		txPesquisa.setLayoutY(30);
		
		tbVitrine.setLayoutX((painel.getWidth() - tbVitrine.getWidth()) / 2);
		tbVitrine.setLayoutY(80);
		
	}


	@SuppressWarnings("static-access")
	private void initItens(){
		VitrineController vitrineController = new VitrineController();
		vitrineController.mockProdutos(3);
		
		vitrineController.getProdutos().forEach(p -> {
			listItens.add(new ItensProperty(p.getProduto(), p.getPreco(), p.getPathImg()));
		});
		
		tbVitrine.setItems(listItens);
	}
	
	private <T> void initListeners() {

		txPesquisa.setOnAction((event) -> {
			
			String text = txPesquisa.getText();
			if (text != null && !text.trim().isEmpty()) {
				tbVitrine.setItems(findItens(text));
				
				if (tbVitrine.getItems().isEmpty()) {
				}
				
			} else {
				tbVitrine.setItems(listItens);
			}
			
		});
		
		
		
		tbVitrine.getSelectionModel().selectedItemProperty()
			.addListener((observable,oldValue,newValue) -> {
			
				Produto produto = new Produto(newValue.getProduto(), 
						newValue.getPreco(), newValue.getPathImg());
				
				ItemApp.setProduto(produto);
				
				try {
					new ItemApp().start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			});
		
	}	

	
	private ObservableList<ItensProperty> findItens(String txPesquisa) {

		List<ItensProperty> list = listItens.stream()
				.filter(i -> i.getProduto().contains(txPesquisa))
				.collect(Collectors.toList());
		
		if (list != null && !list.isEmpty()) {
			
			ObservableList<ItensProperty> observableArrayList = FXCollections.observableArrayList();
			observableArrayList.addAll(list);
			
			return observableArrayList;
		}
		
		return FXCollections.observableArrayList();
	}


	@SuppressWarnings("unchecked")
	private void initComponentes(){
		
		painel = new AnchorPane();
		painel.setPrefSize(400, 600);
		
		cena = new Scene(painel);
		
		txPesquisa = new TextField();
		txPesquisa.setPromptText("Pesquisar...");
		
		tbVitrine = new TableView<ItensProperty>();
		tbVitrine.setPrefSize(380, 450);
		tbVitrine.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		colunaProduto = new TableColumn<ItensProperty, String>();
		colunaProduto.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("produto"));
		colunaProduto.setText("Produto");
		
		colunaPreco = new TableColumn<ItensProperty, Double>();
		colunaPreco.setCellValueFactory(new PropertyValueFactory<ItensProperty, Double>("preco"));
		colunaPreco.setText("Preço");
		
		tbVitrine.getColumns().addAll(colunaProduto,colunaPreco);
		
		painel.getChildren().addAll(txPesquisa,tbVitrine);
	}
	

	public class ItensProperty{
		
		private SimpleStringProperty produto;
		private SimpleDoubleProperty preco;
		private SimpleStringProperty pathImg;
		
		public ItensProperty(String produto, double preco, String pathImg) {
			this.produto = new SimpleStringProperty(produto);
			this.preco = new SimpleDoubleProperty(preco);
			this.pathImg = new SimpleStringProperty(pathImg);
		}

		
		public String getProduto() {
			return produto.get();
		}

		public void setProduto(String produto) {
			this.produto.set(produto);
		}

		public double getPreco() {
			return preco.get();
		}

		public void setPreco(double preco) {
			this.preco.set(preco);
		}


		public String getPathImg() {
			return this.pathImg.get();
		}

		public void setPathImg(String pathImg) {
			this.pathImg.set(pathImg);
		}
		
	}
	
}
