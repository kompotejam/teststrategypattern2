import java.util.Scanner;

// my first take on a strategy pattern. More algorithm encryption could be (and should be) added.
public class Main {

    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("What sending method would you use ? EMAIL, SMS or SLACK");
        String choosenmethod  = input.nextLine();
        System.out.println("All right, would you like to encrypt your message ? YES or NO");
        String choosingencryption = input.nextLine();


        MessageSender sender = new MessageSender();

        if("EMAIL".equals(choosenmethod.toUpperCase())) {
            sender.setMethod(new EMAILsendingmethod());
            if(choosingencryption.toUpperCase().equals("YES")){
                sender.setEncryption(new firstencryptionmethod());
                sender.Encryption("","","");
            }else{
                sender.send("","","");
            }
        }else if ("SLACK".equals(choosenmethod.toUpperCase())) {
            sender.setMethod(new SLACKsendingmethod());
            if(choosingencryption.toUpperCase().equals("YES")){
                sender.setEncryption(new firstencryptionmethod());
                sender.Encryption("","","");
            }else{
                sender.send("","","");
            }
        }else {
            sender.setMethod(new SMSsendingmethod());
            if(choosingencryption.toUpperCase().equals("YES")){
                sender.setEncryption(new firstencryptionmethod());
                sender.Encryption("","","");
            }else{
                sender.send("","","");
            }        }
    }
}
interface SendingMethod {
      void send(String from, String to, String msg);


}

class SMSsendingmethod implements SendingMethod {
    @Override
    public void send(String from, String to, String msg) {
        System.out.println(String.format("Send SMS from %s to %s : %s ", from, to, msg));

    }
}

class EMAILsendingmethod implements SendingMethod{
    @Override
    public void send(String from, String to, String msg) {
        System.out.println(String.format("Email from %s to %s : '%s " , from, to, msg));
    }
}

class SLACKsendingmethod implements SendingMethod {


    @Override
    public void send(String from, String to, String msg) {
        System.out.println(String.format("Slack message from %s to %s : %s", from, to, msg));
    }
}
interface Encryption {
    String Encrypted (String msg);
}

class firstencryptionmethod implements Encryption {
    static final char firsChar = ' '; // this is 32
    static final char lastChar = '~'; // this is 126
    static final int alphabetLen = lastChar - firsChar + 1; // 95
    // by default the encryption key will be 8
    @Override
    public String Encrypted(String msg) {
        Scanner input = new Scanner (System.in);
        System.out.println("Choose your encryption key : ");
        int key = input.nextInt();
        char c;
        String cArray = "";
        for (int i = 0; i < msg.length(); i++) {
            c = (char) ((msg.charAt(i) + key - firsChar) % alphabetLen + firsChar);
            cArray += c;
        }
        return cArray;
    }
}

class MessageSender {
    private SendingMethod method;
    private Encryption encryption;
    final Scanner input = new Scanner(System.in);


    public void setMethod (SendingMethod method){
        this.method = method;
    }

    public void setEncryption (Encryption encryption){
        this.encryption = encryption;
    }
    public void send (String from, String to, String msg){
        System.out.println("From where do you wand to send ?");
        from = input.nextLine();
        System.out.println("To where do you wand to send it ?");
        to = input.nextLine();
        System.out.println("What do you wand to send it ?");
        msg = input.nextLine();
        this.method.send(from, to, msg);
    }

    public void Encryption (String from, String to, String msg){
        System.out.println("Type your message to be encrypted : ");
        msg = input.nextLine();
        System.out.println("From where do you wand to send it ?");
        from = input.nextLine();
        System.out.println("To where do you wand to send it ?");
        to = input.nextLine();
        this.method.send(from, to, encryption.Encrypted(msg));

    }
}
