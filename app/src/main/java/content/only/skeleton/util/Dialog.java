package content.only.skeleton.util;

public class Dialog
{
  private String check = "";
  private String message = "";
  private String negativeAnswer = "";
  private String positiveAnswer = "";
  private String status = "";
  private String title = "App Lock";
  
  public Dialog(String paramString)
  {
    this.message = paramString;
  }
  
  public Dialog(String paramString1, String paramString2)
  {
    this.message = paramString1;
    this.check = paramString2;
  }
  
  public Dialog(String paramString1, String paramString2, String paramString3)
  {
    this.message = paramString1;
    this.positiveAnswer = paramString2;
    this.negativeAnswer = paramString3;
  }
  
  public Dialog(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.message = paramString1;
    this.positiveAnswer = paramString2;
    this.negativeAnswer = paramString3;
    this.status = paramString4;
  }
  
  public Dialog(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    this.title = paramString1;
    this.message = paramString2;
    this.positiveAnswer = paramString3;
    this.negativeAnswer = paramString4;
    this.status = paramString5;
  }
  
  public String getCheck()
  {
    return this.check;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public String getNegativeAnswer()
  {
    return this.negativeAnswer;
  }
  
  public String getPositiveAnswer()
  {
    return this.positiveAnswer;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public void setCheck(String paramString)
  {
    this.check = paramString;
  }
  
  public void setMessage(String paramString)
  {
    this.message = paramString;
  }
  
  public void setNegativeAnswer(String paramString)
  {
    this.negativeAnswer = paramString;
  }
  
  public void setPositiveAnswer(String paramString)
  {
    this.positiveAnswer = paramString;
  }
  
  public void setStatus(String paramString)
  {
    this.status = paramString;
  }
  
  public void setTitle(String paramString)
  {
    this.title = paramString;
  }
}
