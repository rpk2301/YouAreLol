//package Controller;
//
//import org.json.simple.JSONObject;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//
//public class Request {
//
//    HttpClient client = HttpClient.newHttpClient();
//     HttpRequest request = HttpRequest.newBuilder()
//             .uri(URI.create("http://zipcode.rocks:8085/ids"))
//             .GET()
//             .build();
//    JSONObject account = new JSONObject();
//                account.put("userid","-");
//                account.put("name","RickyRickyTheThird");
//                account.put("github","rpk2301");
//    String jsonbody = account.toString();
//    HttpRequest request = HttpRequest.newBuilder()
//            .uri(URI.create("http://zipcode.rocks:8085/ids"))
//            .POST(HttpRequest.BodyPublishers.ofString(jsonbody))
//            .build();
//
//
//
//
//
//    HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//    public Request() throws IOException, InterruptedException {
//    }
//        File fileobj = new File("playingAround.html");
//        fileobj.createNewFile();
//         FileWriter fileWriterobj = new FileWriter("playingAround.html");
//         String ret = delimitByBracket(response.body().toString());
//         fileWriterobj.write(ret);
//         System.out.println(ret);
//         System.out.println(response.statusCode());
//}
//
//
//
//
//
//
//
//
//    public static String delimitByBracket(String in)
//    {
//        String ret="";
//        for(Character s: in.toCharArray())
//        {
//            if (s!='}')
//            {
//                ret+=s;
//            }
//            else
//            {
//                ret+='}';
//                ret+='\n';
//            }
//        }
//        return ret;
//    }
//
//
//}
//
//
//
//}
