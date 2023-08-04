package rocks.zipcode;

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