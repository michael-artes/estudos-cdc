package br.com.loginapp.view;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginApp extends Application{
	
	private AnchorPane painel; 
	private Scene cena;
	private TextField txLogin;
	private PasswordField txSenha;
	private Text txErros;
	private Button btEntrar;
	private Button btSair;
	
	
	private static Stage stage;
	
	
	@Override
	public void start(Stage stage) throws Exception {

		initComponentes();
		initListeners();
		
		stage.setScene(cena);
		stage.setResizable(false);
		stage.setTitle("Login");
		stage.show();
		
		initLayout();
		
		LoginApp.stage = stage;
	}
	
	
	private void initComponentes(){
		
		painel = new AnchorPane();
		painel.setPrefSize(250, 300);
		
		cena = new Scene(painel);
		
		txLogin = new TextField();
		txLogin.setPromptText("Digite o login");
		
		txSenha = new PasswordField();
		txSenha.setPromptText("Digite a senha");
		
		txErros = new Text("É um test");
		
		btEntrar = new Button("Entrar");
		btSair = new Button("Sair");	
		
		painel.getChildren().addAll(txLogin,txSenha,btEntrar,btSair,txErros);
		
		painel.setStyle("-fx-background-color: #034f84;");
	}
	
	
	private void initLayout(){
		
		txLogin.setLayoutX( (painel.getWidth() - txLogin.getWidth()) / 2 );
		txLogin.setLayoutY(15);
		
		txSenha.setLayoutX( (painel.getWidth() - txSenha.getWidth()) / 2 );
		txSenha.setLayoutY(75);
		
		btEntrar.setLayoutX( 39.5 );
		btEntrar.setLayoutY(180);
		
		btSair.setLayoutX( 160 );
		btSair.setLayoutY(180);
		
		txErros.setLayoutX( btSair.getWidth() );
		txErros.setLayoutY(250);
		txErros.setFill(Color.WHITE);
		txErros.setVisible(false);
		txErros.setStyle("-fx-stroke: #d5f4e6;-fx-stroke-width: 1;");
		
	}
	
	private void initListeners(){

		btEntrar.setOnAction((event) -> {
			logar();
		});
		
		txSenha.setOnAction((event) -> {
			logar();
		});
		
		btSair.setOnAction((event) -> {
			fecharAplicacao();
		});
	}


	private void logar() {
		
		String login = txLogin.getText();
		String senha = txSenha.getText();
		
		if (login.equals("admin") && senha.equals("admin")) {
			
			try {
				new VitrineApp().start(new Stage());
				LoginApp.stage.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			
			txLogin.setText("");
			txSenha.setText("");
			
			txErros.setText("login e senha inválidos");
			txErros.setVisible(true);
		}
	}
	
	
	private void fecharAplicacao(){
		System.exit(0);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
