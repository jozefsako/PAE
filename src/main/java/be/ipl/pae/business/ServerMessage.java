package be.ipl.pae.business;

public enum ServerMessage {
  CLIENT_SIGNED_IN(205, "CLIENT_SIGNED_IN"), SERVER_NOT_AVAILABLE(405,
      "SERVER_NOT_AVAILABLE"), CLIENT_NOT_SIGNED_IN(406, "CLIENT_NOT_SIGNED_IN"), LOGIN_FAILED(408,
          "LOGIN_FAILED"), DB_ERROR(409, "DB_ERROR"), MODULE_MISSING(500, "MODULE_MISSING");

  /**
   * Contains the message from the constructor, it can't be changed as soon as it's assigned.
   * 
   * @see ServerMessage(int messageCode, String message)
   */

  private final String message;
  private int messageCode;

  ServerMessage(int messageCode, String message) {
    this.messageCode = messageCode;
    this.message = message;
  }

  /**
   * Return the message code.
   * 
   * @return a Int that contains the message code from the server
   * 
   */
  public int getMessageCode() {
    return messageCode;
  }

  /**
   * Return the message.
   * 
   * @return a String that contains the message from the server
   * 
   */
  public String getMessage() {
    return message;
  }
}
