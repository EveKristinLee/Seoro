package com.seoro.seoro.domain.dto.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookReportDto {
	// private Long bookReportId;
	private Long readBookId;
	private String bookReportContent;
	// private String photo;
}