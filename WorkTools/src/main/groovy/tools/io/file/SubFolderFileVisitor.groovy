/**
 *
 */
package tools.io.file

import groovy.util.logging.Log4j

import java.io.IOException;
import java.nio.file.FileVisitResult
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes

/**
 * �t�@�C���̕����R�[�h��ϊ����邽�߂�FileVisitor�N���X�B
 *
 */
@Log4j
class SubFolderFileVisitor extends SimpleFileVisitor<Path> {

	//	private String folderName

	//	/* (�� Javadoc)
	//	 * @see java.nio.file.SimpleFileVisitor#visitFile(java.lang.Object, java.nio.file.attribute.BasicFileAttributes)
	//	 */
	//	@Override
	//	public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs) throws IOException {
	////		log.debug "#visitFile(): $filePath"
	////		def folderName = '�e�V���Ή�'
	//		def file = filePath.toFile()
	//		FileVisitResult result = FileVisitResult.CONTINUE
	//
	//		if (!(file.parentFile.name ==~ /201\d�N�x/)) {
	//			result = FileVisitResult.SKIP_SUBTREE
	//		} else if (file.parentFile.name == '�e�V���Ή�') {
	//			result = FileVisitResult.SKIP_SUBTREE
	//			println file.path
	//		}
	//
	//		return result
	//	}

	/* (�� Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#preVisitDirectory(java.lang.Object, java.nio.file.attribute.BasicFileAttributes)
	 */
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

		def file = dir.toFile()
		FileVisitResult result = FileVisitResult.CONTINUE

		def fPattern = /�e�V��?�Ή�/

		if (file.path =~ /20\d{2}�N�x/ ) {
			if (file.path =~ /201\d�N�x/) {
				if (file.name ==~ /201\d�N�x/ || file.name ==~ fPattern) {
					log.debug "SKIP1 :${file.path}"
				} else if (file.path =~ fPattern) {
					if (file.parentFile.name ==~ fPattern) {
						log.info file.path
					} else {
						log.debug "SKIP_SUBTREE3 :${file.path}"
						result = FileVisitResult.SKIP_SUBTREE
					}
				} else {
					log.debug "SKIP_SUBTREE2 :${file.path}"
					result = FileVisitResult.SKIP_SUBTREE
				}
			} else {
				log.debug "SKIP_SUBTREE1 :${file.path}"
				result = FileVisitResult.SKIP_SUBTREE
			}

//			def path = file.path.replaceAll('\\\\', '/')
//			def path = file.path
//			if ( file.parentFile.name == '�e�V���Ή�' || file.parentFile.parentFile.name ==~ /201\d�N�x/) {
//				println file.path
//			} else {
//				println "SKIP1 :${file.path}"
//				result = FileVisitResult.SKIP_SUBTREE
//			}
//
//			if ( file.name ==~ /20\d{2}�N�x/ ) {
//				if  (!(file.name ==~ /201\d�N�x/)) {
//					println "SKIP1 :${file.path}"
//					result = FileVisitResult.SKIP_SUBTREE
//				}
//			} else if (file.parentFile.name ==~ /20\d{2}�N�x/) {
//				if (file.name != '�e�V���Ή�') {
//					println "SKIP2 :${file.path}"
//					result = FileVisitResult.SKIP_SUBTREE
//				}
//			} else if (file.parentFile.parentFile.name ==~ /20\d{2}�N�x/) {
//			} else {
//				println "SKIP3 :${file.path}"
//				result = FileVisitResult.SKIP_SUBTREE
//			}
		} else {
			log.debug "ROOT :${file.path}"
		}

		return result
	}

	/* (�� Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#postVisitDirectory(java.lang.Object, java.io.IOException)
	 */
	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		if (exc) log.error exc.message, exc

		return super.postVisitDirectory(dir, exc);
	}

	/* (�� Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#visitFileFailed(java.lang.Object, java.io.IOException)
	 */
	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		if (exc) log.error exc.message, exc

		return super.visitFileFailed(file, exc)
	}
}
