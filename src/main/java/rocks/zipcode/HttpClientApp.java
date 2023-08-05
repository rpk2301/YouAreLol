package rocks.zipcode;

import com.sun.nio.sctp.MessageInfo;
import org.json.simple.JSONObject;

import java.net.http.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class HttpClientApp {

    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws IOException, InterruptedException {

    WelcomeMessage();
    getFirst(getInput());




    }


    public static void WelcomeMessage() {
        System.out.println("Welcome to Ricky's HTTP Client!");
        System.out.println("Please enter a command.");
    }

    public static String getInput()
    {
        String ret = scan.nextLine();
        return ret;
    }

    public static void getFirst(String ret) throws IOException, InterruptedException
    {
            String[] args = ret.split(" ");
            if(args[0].equals("cmd?"))
            {
                if(args.length > 1) {
                    getCommand(args);
                }
                else
                    System.out.println("You need to list a command. C'mon.");
            }
            else
            {
            System.out.println("I am unsure of what command you are trying to call.");
            }
        }


    public static void getCommand(String[] args) throws IOException, InterruptedException
    {
        if (args.length == 2 && args[1].equals("ids"))
        {
            getIDs();
        }
        else if (args.length == 4 && args[1].equals("ids"))
        {
            PostID(args[2],args[3]);
        }
        else if(args.length ==5 && args[1].equals("change") && args[2].equals("ids"))
        {
            ChangeID(args[3],args[4]);
        }
        else if(args.length == 2 && args[1].equals("messages") || args[1].equals("Messages"))
        {
        getMessages();
        }
        else if(args[1].equals("send"))
        {
         String ret = ExtractMessage(args);
          sendMessage(args[2],args[args.length-1],ret);

          //send xt0fer 'Hello old buddy!' to torvalds
        }
    }


    public static String ExtractMessage(String[] input) {
        String ret = "";
        for (int i = 0; i < input.length; i++) {
            if (input[i].charAt(0) == '\'') {

                while (input[i].charAt(input[i].length() - 1) != '\'') {
                    ret = ret+ input[i] + " ";
                    i++;
                }
                ret = ret+input[i];
                break;
            }
        }
        return ret;
    }

    public static void sendMessage(String from, String to, String message ) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        JSONObject SendObj = new JSONObject();
        SendObj.put("sequence","-");
        SendObj.put("timestamp","_");
        SendObj.put("fromid",from);
        SendObj.put("toid",to);
        SendObj.put("message",message);
        String jsonbody = SendObj.toString();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://zipcode.rocks:8085/ids/" + from + "/messages"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonbody))
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode()==200)
        {
            System.out.println(response.body().toString());
            System.out.println("Code: 200");
            System.out.println("Message to " + to+ " Successfully Posted.");
        }
        else
        {
            System.out.println("Uh oh. That didn't work.");
            System.out.println(response.statusCode());
        }
    }



    public static String getMessages() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://zipcode.rocks:8085/messages"))
                .GET()
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        File fileobj = new File("playingAround.html");
        fileobj.createNewFile();
        FileWriter fileWriterobj = new FileWriter("playingAround.html");
        String ret = delimitByBracket(response.body().toString());
        fileWriterobj.write(ret);
        System.out.println(ret);
        System.out.println(response.statusCode());
        return ret;

    }

    public static void ChangeID(String name, String id) throws IOException, InterruptedException {
            HttpClient client = HttpClient.newHttpClient();
            JSONObject account = new JSONObject();
            account.put("userid","-");
            account.put("name",name);
            account.put("github",id);
            String jsonbody = account.toString();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://zipcode.rocks:8085/ids"))
                    .PUT(HttpRequest.BodyPublishers.ofString(jsonbody))
                    .build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode()==200)
            {
                System.out.println(response.body().toString());
                System.out.println("Code: 200");
                System.out.println("Account Successfully Altered.");
            }
            else
            {
                System.out.println("Uh oh. That didn't work.");
            }
        }
    public static void PostID(String name, String hub) throws IOException, InterruptedException
    {
        HttpClient client = HttpClient.newHttpClient();
        JSONObject account = new JSONObject();
                account.put("userid","-");
                account.put("name",name);
                account.put("github",hub);
        String jsonbody = account.toString();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://zipcode.rocks:8085/ids"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonbody))
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode()==200)
        {
            System.out.println(response.body().toString());
            System.out.println("Code: 200");
            System.out.println("Account Successfully Posted.");
        }
    }




    public static String getIDs() throws IOException, InterruptedException
    {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://zipcode.rocks:8085/ids"))
                .GET()
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        File fileobj = new File("playingAround.html");
        fileobj.createNewFile();
        FileWriter fileWriterobj = new FileWriter("playingAround.html");
        String ret = delimitByBracket(response.body().toString());
        fileWriterobj.write(ret);
        System.out.println(ret);
        System.out.println(response.statusCode());
        return ret;
    }


    public static String delimitByBracket(String in)
    {
        String ret="";
        for(Character s: in.toCharArray())
        {
            if (s!='}')
            {
                ret+=s;
            }
            else
            {
                ret+='}';
                ret+='\n';
            }
        }
        return ret;
    }



}