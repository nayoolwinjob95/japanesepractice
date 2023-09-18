package com.app.japanesepractice.model.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.japanesepractice.model.domain.entity.Ebook;
import com.app.japanesepractice.model.domain.form.EbookForm;
import com.app.japanesepractice.model.repository.EbookRepository;

@Service
public class EbookService {

	@Autowired
	private EbookRepository ebookRepository;

	@Transactional
	public void save(EbookForm ebookForm) {
		int id = ebookRepository.getCurrentIdSeq().orElse(0);
		ebookRepository.save(new Ebook(ebookForm));
		ebookForm.setId(String.valueOf(id + 1));
		try {
			this.saveFile(ebookForm);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void update(EbookForm ebookForm) {
		ebookRepository.update(new Ebook(ebookForm));
		try {
			this.saveFile(ebookForm);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveFile(EbookForm ebookForm) throws IllegalStateException, IOException {
		File file = new File("");
		String absolutePath = file.getAbsolutePath();
		String imgDir = absolutePath + "\\target\\classes\\static\\image\\ebooks\\";
		String pdfDir = absolutePath + "\\target\\classes\\static\\pdf\\ebooks\\";

		MultipartFile bookImage = ebookForm.getBookImage();
		if (!bookImage.isEmpty()) {
			String[] ext = bookImage.getOriginalFilename().split("\\.");
			bookImage.transferTo(new File(imgDir + ebookForm.getId() + "." + ext[ext.length - 1]));
		}

		MultipartFile pdf = ebookForm.getBookPdf();
		if (!pdf.isEmpty()) {
			pdf.transferTo(new File(pdfDir + ebookForm.getId() + ".pdf"));
		}

	}

	public void deleteFile(int id) {
		Path filePNG = Paths.get("target/classes/static/image/ebooks/" + id + ".png");
		Path fileJPG = Paths.get("target/classes/static/image/ebooks/" + id + ".jpg");
		Path filePDF = Paths.get("target/classes/static/pdf/ebooks/" + id + ".pdf");

		File fpng = new File(filePNG.toString());
		File fjpg = new File(fileJPG.toString());
		File fpdf = new File(filePDF.toString());

		try {
			if (fpng.exists()) {
				Files.delete(filePNG);
			}

			if (fjpg.exists()) {
				Files.delete(fileJPG);
			}

			if (fpdf.exists()) {
				Files.delete(filePDF);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<Ebook> getEbooks() {
		return ebookRepository.getEbooks();
	}
	
	public Optional<Ebook> getEbookWithId(int id) {
		return ebookRepository.getEbookWithId(id);
	}

	public List<Ebook> getEbooks(String bookTitle, String level) {
		return ebookRepository.getEbooks(bookTitle, level);
	}

	public EbookForm findOneById(Integer id) {
		return new EbookForm(ebookRepository.findOneById(id));
	}

	public void delete(Integer id) {
		ebookRepository.delete(id);
		Ebook ebook = ebookRepository.findOneById(id);
		if (ebook == null) {
			this.deleteFile(id);
		}
	}

}
