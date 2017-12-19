package com.ts.us.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	public static void upload(String path, MultipartFile file, String fileName) {
		System.out.println("Starting of the method upload");
		System.out.println("Current Path :" + Paths.get("").toFile());
		if (!file.isEmpty()) {

			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				File dir = new File(path);
				if (!dir.exists())
					dir.mkdirs();
				File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);

				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				System.out.println("Server File Location=" + serverFile.getAbsolutePath());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("Ending of the method upload");

	}

	// using nio
	public void fileCopy(String src, String dest) {

		Path sourcePath = Paths.get(src);
		Path destinationPath = Paths.get(dest);

		try {
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
