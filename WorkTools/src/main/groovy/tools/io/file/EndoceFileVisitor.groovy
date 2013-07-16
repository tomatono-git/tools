/**
 *
 */
package tools.io.file

import groovy.util.logging.Log4j;

import java.nio.file.FileVisitResult
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes

/**
 * �t�@�C���̕����R�[�h��ϊ����邽�߂�FileVisitor�N���X�B
 *
 */
@Log4j
class EndoceFileVisitor extends SimpleFileVisitor<Path> {

	/** �ϊ����镶���R�[�h */
	def String encoding = 'UTF-8'

	/* (�� Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#visitFile(java.lang.Object, java.nio.file.attribute.BasicFileAttributes)
	 */
	@Override
	public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs) throws IOException {
		log.debug "#visitFile(): $filePath"

		def file = filePath.toFile()
		file.write(file.text, encoding)

		return FileVisitResult.CONTINUE
	}

	/* (�� Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#visitFileFailed(java.lang.Object, java.io.IOException)
	 */
	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		log.error exc.message, exc

		return FileVisitResult.CONTINUE;
	}
}
