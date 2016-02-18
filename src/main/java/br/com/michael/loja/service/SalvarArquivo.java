package br.com.michael.loja.service;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import br.com.michael.loja.models.Produto;

@Component
public class SalvarArquivo {
	
	private static String bucket = "s3Amazon";
	

	/*@Autowired
	private HttpServletRequest request;*/

	public String writer(Produto produto, MultipartFile sumario) {
		
		PutObjectRequest putObjectRequest = null;
		
		try {
			putObjectRequest = new PutObjectRequest(bucket, "prod-"+produto.getId()+"-img.png", sumario.getInputStream(), new ObjectMetadata());
			
			AmazonS3Client client = client();
			client.putObject(putObjectRequest);
			
			return "https://s3.amazonaws.com/casadocodigo/"+sumario.getOriginalFilename()+"?noAuth=true";
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		

		
		//Salvando no projeto
		/*String realPath = request.getServletContext().getRealPath("/" + pastaArquivo);
		String path = realPath + "/" + sumario.getOriginalFilename();*/
		
		/*try {
			
			System.out.println("O arquivo sera gravado em: " + path);
			File dest = new File(path);
			sumario.transferTo(dest);
			return pastaArquivo + "/" + sumario.getOriginalFilename();
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		
		return null;
	}

	private AmazonS3Client client() {
		
		AWSCredentials credentials = new BasicAWSCredentials("AKIAIOSFODNN7EXAMPLE","wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY");
		
		AmazonS3Client client = new AmazonS3Client(credentials, new ClientConfiguration());
		client.setS3ClientOptions(new S3ClientOptions().withPathStyleAccess(true));
		
		client.setEndpoint("http://localhost:9444/s3");
		return client; 
	}
	
	
	
}
