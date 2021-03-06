/**
 *
 */
package tools.web

/** Basic認証のためのクラス */
class HttpAuthenticator extends Authenticator {

	def username
	def password

	/* (非 Javadoc)
	 * @see java.net.Authenticator#getPasswordAuthentication()
	 */
	def PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.username, this.password as char[])
	}

	def String getPrompt() {
		return super.getRequestingPrompt()
	}
}

