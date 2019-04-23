package br.com.loginapp.view;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.login.app.controller.CarrinhoController;
import com.login.app.model.Produto;

import br.com.loginapp.converter.ItensProperty;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CarrinhoApp extends Application{
	
	private AnchorPane painel;
	private Scene cena;
	
	private TableView<ItensProperty> tbCarrinho;
	private TableColumn<ItensProperty, String> colunaProduto;
	private TableColumn<ItensProperty, Double> colunaPreco;
	
	private static Stage stage;
	
	private Button btExcluirItem;
	private Button btConfirmar;
	
	private ObservableList<ItensProperty> listItens = FXCollections.observableArrayList();

	@Override
	public void start(Stage stage) throws Exception {
		
		initComponentes();
		initItens();
		
		initListener();
		
		stage.setScene(cena);
		stage.setResizable(false);
		stage.setTitle("Carrinho");
		stage.show();
		
		initLayout();
		setStage(stage);
		
	}
	
	
	private void initListener() {
		
		btExcluirItem.setOnAction((event) -> {
			
			List<Produto> listRemove = tbCarrinho.getSelectionModel().getSelectedItems().stream()
					.map(ItensProperty::getProdutoPojo)
					.collect(Collectors.toList());
			
			CarrinhoController.getProdutos().removeIf(p -> {

				for (Produto produtoRemove : listRemove) {
					if (p.getProduto().equals(produtoRemove.getProduto())) {
						return true;
					}
				}
				
				return false;
			});
			
			listItens = FXCollections.observableArrayList();
			
			initItens();
		});
		
		
		btConfirmar.setOnAction((event) -> {
			
			Thread thread = new Thread() {
				
				public void run() {
					
					try {
						sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null,"Compra realizada com sucesso!");
					
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							CarrinhoApp.stage.close();
							ItemApp.getStage().close();
						}
					
					});
				};
			};
			thread.start();
			
		});
		
		
	}


	private void initItens() {

		CarrinhoController.getProdutos().forEach(p -> {
			ItensProperty itensProperty = new ItensProperty(p.getProduto(), p.getPreco(), p.getPathImg());
			listItens.add(itensProperty);
		});
		
		tbCarrinho.setItems(listItens);
	}


	private static void setStage(Stage stage) {
		CarrinhoApp.stage = stage;
	}


	@SuppressWarnings("unchecked")
	private void initComponentes(){
		
		painel = new AnchorPane();
		painel.setPrefSize(400, 600);
		
		cena = new Scene(painel);
		
		btExcluirItem = new Button("Excluir Item Selecionado"); 
		btConfirmar = new Button("Confirmar");
		
		tbCarrinho = new TableView<ItensProperty>();
		tbCarrinho.setPrefSize(380, 450);
		tbCarrinho.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tbCarrinho.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		colunaProduto = new TableColumn<ItensProperty, String>();
		colunaProduto.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("produto"));
		colunaProduto.setText("Produto");
		
		colunaPreco = new TableColumn<ItensProperty, Double>();
		colunaPreco.setCellValueFactory(new PropertyValueFactory<ItensProperty, Double>("preco"));
		colunaPreco.setText("Preço");
		
		tbCarrinho.getColumns().addAll(colunaProduto,colunaPreco);		
		
		painel.getChildren().addAll(btConfirmar,btExcluirItem,tbCarrinho);
	}	
	
	private void initLayout() {
		
		btConfirmar.setLayoutX(300);
		btConfirmar.setLayoutY(30);
		
		btExcluirItem.setLayoutX(10);
		btExcluirItem.setLayoutY(30);

		tbCarrinho.setLayoutX((painel.getWidth() - tbCarrinho.getWidth()) / 2);
		tbCarrinho.setLayoutY(80);
		
	}

}
