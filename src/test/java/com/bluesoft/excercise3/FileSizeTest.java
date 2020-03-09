package com.bluesoft.excercise3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FileSizeTest {

	@Test
	public void shouldSizeFileInBytes() {
		 assertEquals(Double.valueOf(17489920), FilesizeUtils.getFilesizeInBytes("GitExtensions-3.0.2.5232.msi"));
	}

	@Test
	public void shouldSizeFileInKiloBytes() {
		assertEquals(Double.valueOf(17080), FilesizeUtils.getFilesizeInKiloBytes("GitExtensions-3.0.2.5232.msi"));
	}

	@Test
	public void shouldSizeFileInMegaBytes() {
		assertEquals(Double.valueOf(16.6796875), FilesizeUtils.getFilesizeInMegaBytes("GitExtensions-3.0.2.5232.msi"));
	}

	@Test
	public void shouldFileSizeEmpty() {
		assertThrows(NullPointerException.class, () -> FilesizeUtils.getFilesizeInMegaBytes(""));
	}

	@Test
	public void shouldFileSizeNull() {
		assertThrows(NullPointerException.class, () -> FilesizeUtils.getFilesizeInMegaBytes(null));
	}

}
