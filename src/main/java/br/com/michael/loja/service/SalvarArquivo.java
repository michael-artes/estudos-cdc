package br.com.michael.loja.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.michael.loja.models.Produto;

@Component
public class SalvarArquivo {
	
//	private static String bucket = "s3Amazon";
	
	@Autowired
	private HttpServletRequest request;
	
	private String fileName = "prod-%s-img.png";
	
	
	private static final Logger LOGGER = Logger.getLogger(SalvarArquivo.class);
	

	
	public String writer(Produto produto, MultipartFile sumario) {
		
		/*PutObjectRequest putObjectRequest = null;
		
		try {
			putObjectRequest = new PutObjectRequest(bucket, "prod-"+produto.getId()+"-img.png", sumario.getInputStream(), new ObjectMetadata());
			
			AmazonS3Client client = client();
			client.putObject(putObjectRequest);
			
			return "https://s3.amazonaws.com/casadocodigo/"+sumario.getOriginalFilename()+"?noAuth=true";
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		

		
		//Salvando no projeto
		String realPath = request.getServletContext().getRealPath("/resources/imagens/uploads");
		
		String path = realPath + "/" + String.format(fileName, produto.getId());
		
		LOGGER.debug("path do arquivo: " + path);
		
		try {
			
			
			File dest = new File(realPath);
			
			if (!dest.exists()) {
				boolean mkdirs = dest.mkdirs();
				
				LOGGER.info("isCriado diretorio: " + mkdirs);
			}
			
			BufferedOutputStream os = new BufferedOutputStream( new FileOutputStream( new File(path) ) );
			os.write(sumario.getBytes());
			os.close();
			
			return path;
			
		} catch (IllegalStateException | IOException e) {
			LOGGER.error("Nao foi possivel salvar o arquivo.", e);
		}
		
		
		return null;
	}

	
	
	
	/*private AmazonS3Client client() {
		
		AWSCredentials credentials = new BasicAWSCredentials("AKIAIOSFODNN7EXAMPLE","wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY");
		
		AmazonS3Client client = new AmazonS3Client(credentials, new ClientConfiguration());
		client.setS3ClientOptions(new S3ClientOptions().withPathStyleAccess(true));
		
		client.setEndpoint("http://localhost:9444/s3");
		return client; 
	}*/
	
	
	
}
