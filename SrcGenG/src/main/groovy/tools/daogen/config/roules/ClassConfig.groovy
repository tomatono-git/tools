/**
 *
 */
package tools.daogen.config.roules

import groovy.transform.ToString
import tools.util.Utils


/**
 *
 *
 */
@ToString(includeNames = true, includeFields = true)
abstract class ClassConfig implements Configuration {

	/** �x�[�X�p�b�P�[�W�� */
	def String basePackage

	/** �p�b�P�[�W���i�����j */
	def String packageSuffix
	/** �N���X�̃e���v���[�g�� */
	def String template
	/**  �N���X�̃T�t�B�b�N�X */
	def String suffix

	def LinkedHashSet<String> importList = []
	/** ���N���X�� */
	def String baseClassName
	/** �C���^�[�t�F�[�X�ꗗ */
	def LinkedHashSet<String> interfaceList = []
	/** �W�F�l���N�X�̗L�� */
	def boolean enableGenerics
	/** �A�m�e�[�V�����L�� */
	def boolean enableAnnotation

	def void setBaseClassName(String className) {
		if (className) {
			List temp = className.split(/\./)
			def name = temp.pop()
			assert className
			importList.add(className)
			baseClassName = name
		} else {
			baseClassName = className
		}
	}

//	private String toSimpleClassName(String className) {
//		if (!className) {
//			return className
//		}
//		List temp = className.split(/\./)
//		def name = temp.pop()
//		def packageName = temp.join(".")
//		if (packageName) {
//			importList.add(packageName)
//		}
//		return name
//	}

	/**
	 * Dto�p�b�P�[�W��
	 * @return
	 */
	def String getPackageName(String basePackage = null) {
		if (!basePackage) {
			basePackage = this.basePackage
		}
		return "${basePackage}.${packageSuffix}"
	}

	/**
	 * �N���X�����擾
	 * @param tableName �e�[�u����
	 * @param tablePrefix �e�[�u���v���t�B�b�N�X
	 * @return �N���X��
	 */
	def String getClassName(String tableName, String tablePrefix) {
		assert tableName
		return Utils.toPascalCase(tableName.replace(tablePrefix, "")) + Utils.toPascalCase(suffix)
	}

	/**
	 *
	 * @param element
	 * @return
	 */
	protected LinkedHashSet<String> getInterfaceList(element) {
		List interfaceList = element.collect {
			String className = it.className.text()
			if (!className) {
				return ""
			}
			List temp = className.split(/\./)
			def name = temp.pop()
			importList.add(className)
			return name
		}
		interfaceList.remove("");
		if (!interfaceList && !baseClassName) {
			interfaceList += Serializable.class.name
		}
		return interfaceList.toSet()
	}
}
