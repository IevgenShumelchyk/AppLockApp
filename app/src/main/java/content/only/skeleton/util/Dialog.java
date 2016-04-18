package content.only.skeleton.util;

/**
 * Dialog type, used to set text to Dialogs.
 */
public class Dialog {

    private String title = Globals.APP_NAME;
    private String message = "";
    private String positiveAnswer = "";
    private String negativeAnswer = "";
    private String status = "";
    private String check = "";

    public Dialog(String title, String message, String positiveAnswer, String negativeAnswer, String status) {
        this.title = title;
        this.message = message;
        this.positiveAnswer = positiveAnswer;
        this.negativeAnswer = negativeAnswer;
        this.status = status;
    }

    public Dialog(String message, String positiveAnswer, String negativeAnswer, String status) {
        this.message = message;
        this.positiveAnswer = positiveAnswer;
        this.negativeAnswer = negativeAnswer;
        this.status = status;
    }

    public Dialog(String title) {
        this.message = title;
    }

    public Dialog(String message, String positiveAnswer, String negativeAnswer) {
        this.message = message;
        this.positiveAnswer = positiveAnswer;
        this.negativeAnswer = negativeAnswer;
    }

    public Dialog(String message, String check) {
        this.message = message;
        this.check = check;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPositiveAnswer() {
        return positiveAnswer;
    }

    public void setPositiveAnswer(String positiveAnswer) {
        this.positiveAnswer = positiveAnswer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNegativeAnswer() {
        return negativeAnswer;
    }

    public void setNegativeAnswer(String negativeAnswer) {
        this.negativeAnswer = negativeAnswer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }
}
