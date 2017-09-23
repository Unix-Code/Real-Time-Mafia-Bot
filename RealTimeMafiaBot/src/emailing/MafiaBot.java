package emailing;

/**
 *
 * @author David
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MafiaBot {

    public static void main(String[] args) {
        MafiaBot mfb = new MafiaBot();
        mfb.sendAllEmails();
    }

    public void sendAllEmails() {
        ArrayList<Email> emails = this.pickMafia(this.getAllEmails());
        String mafiaList = this.getMafiaListText(emails);
        for (Email email : emails) {
            this.sendEmail(email, mafiaList);
        }
    }

    public String getMafiaListText(ArrayList<Email> emails) {
        String mafiaListText = "";
        String separator = "";

        for (Email email : emails) {
            if (email.isMafia()) {
                mafiaListText += separator + email.getName();
                separator = ", ";
            }
        }

        return mafiaListText;
    }

    public ArrayList<Email> pickMafia(ArrayList<Email> emails) {
        int numMafia = 0;
        while (numMafia < 3) {
            Email picked = emails.get((int) (Math.random() * (emails.size())));
            if (!picked.isMafia()) {
                picked.setIsMafia(true);
                numMafia++;
            }
        }

        return emails;
    }

    public ArrayList<Email> getAllEmails() {
        ArrayList<Email> emails = new ArrayList<>();
        System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
        try (BufferedReader br = new BufferedReader(new FileReader(".\\emails.txt"))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String name = sCurrentLine.split(" : ")[0];
                String email = sCurrentLine.split(" : ")[1];
                emails.add(new Email(email, name));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return emails;
    }

    public void sendEmail(Email to, String mafiaList) {
        Session session = this.getSession();
        Message message = getMessage(to, this.getSession(), mafiaList);

        try {
            Transport.send(message);
            System.out.println("Sent message successfully...."); // Diagnostic
            System.out.println(message.getContent() + "\n"); // Diagnostic
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Message getMessage(Email to, Session session, String mafiaList) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("RealTimeMafiaBot@gmail.com"));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to.getEmail()));

            message.setSubject("Real Time Mafia Role");

            message.setText(to.getName() + ", you are " + ((to.isMafia())
                    ? "a Mafia!\nPrepare to brutally murder some innocents!\nThe mafia are: " + mafiaList + "."
                    : "an innocent!\nGood luck surviving!"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public Session getSession() {
        String username = "RealTimeMafiaBot@gmail.com";
        String password = "kQTzE3WX3R5XcL7f";

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        return session;
    }
}
