package forestdemo.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DemoRestController {

	@GetMapping("/upload")
	public void upload() {
		// TODO
		Path savePath = Paths.get("C:/ローカルドキュメント/work space/spring_practice/forest-demo/input/upload.txt");
		try (OutputStream os = Files.newOutputStream(savePath, StandardOpenOption.CREATE)) {
//			os.write(file.getBytes());
			byte b1 = 74; //ASCII：(J)
			byte b2 = 65; //ASCII：(A)
			byte b3 = 86; //ASCII：(V)
			byte b4 = 65; //ASCII：(A)
			os.write(b1);
			os.write(b2);
			os.write(b3);
			os.write(b4);
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
