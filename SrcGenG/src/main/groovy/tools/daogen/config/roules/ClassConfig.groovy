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

	/** ベースパッケージ名 */
	def String basePackage

	/** パッケージ名（末尾） */
	def String packageSuffix
	/** クラスのテンプレート名 */
	def String template
	/**  クラスのサフィックス */
	def String suffix

	def LinkedHashSet<String> importList = []
	/** 基底クラス名 */
	def String baseClassName
	/** インターフェース一覧 */
	def LinkedHashSet<String> interfaceList = []
	/** ジェネリクスの有効 */
	def boolean enableGenerics
	/** アノテーション有効 */
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
	 * Dtoパッケージ名
	 * @return
	 */
	def String getPackageName(String basePackage = null) {
		if (!basePackage) {
			basePackage = this.basePackage
		}
		return "${basePackage}.${packageSuffix}"
	}

	/**
	 * クラス名を取得
	 * @param tableName テーブル名
	 * @param tablePrefix テーブルプレフィックス
	 * @return クラス名
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
