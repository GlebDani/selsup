package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;

import java.util.concurrent.TimeUnit;

public class CrptApi {

    private TimeUnit timeUnit;
    private  int requestLimit;
    private long refreshTime;
    private int pointer;


    public CrptApi(TimeUnit timeUnit, int requestLimit) {
        this.timeUnit = timeUnit;
        this.requestLimit = requestLimit;
    }

    synchronized public void getConnection(Document document)  {
        long timeOfCall = System.currentTimeMillis();
        if(refreshTime == 0 || refreshTime < timeOfCall){
            refreshTime = timeOfCall+ timeUnit.toMillis(1);
            pointer = 0;
        }
        pointer++;
        try {
            if (pointer > requestLimit) {
                Thread.sleep(refreshTime-timeOfCall+1);
                pointer=1;
                refreshTime = timeOfCall+ timeUnit.toMillis(1);
            }
                JSONObject jsonObject = new JSONObject(document);
                String docStr = jsonObject.toString();
            var client = HttpClient.newHttpClient();

            var request = HttpRequest
                    .newBuilder(URI.create("https://ismp.crpt.ru/api/v3/lk/documents/create"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(docStr))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            response.statusCode();
            System.out.println(response.statusCode());

        }

        catch (Exception e){
            e.getMessage();
        }

    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    static class Document {
        Desc description;
        String doc_id;
        String doc_status;
        DocType doc_type;
        boolean importRequest;
        String owner_inn;
        String participant_inn;
        String producer_inn;
        Date production_date;
        String production_type;
        Product product;
        Date reg_date;
        String reg_number;

    }
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    static class  Desc{
        String participantInn;
    }
    enum DocType{
        LP_INTRODUCE_GOODS
    }
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    static class Product{
        String certificate_document;
        Date certificate_document_date;
        String certificate_document_number;
        String owner_inn;
        String producer_inn;
        Date production_date;
        String tnved_code;
        String uit_code;
        String uitu_code;
    }



}
