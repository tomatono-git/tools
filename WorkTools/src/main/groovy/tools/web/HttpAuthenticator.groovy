/**
 *
 */
package tools.web

/** Basic�F�؂̂��߂̃N���X */
class HttpAuthenticator extends Authenticator {

	def username
	def password

	/* (�� Javadoc)
	 * @see java.net.Authenticator#getPasswordAuthentication()
	 */
	def PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.username, this.password as char[])
	}

	def String getPrompt() {
		return super.getRequestingPrompt()
	}
}

