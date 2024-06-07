package connectionUtils;

import baseClasses.MusicBand;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;

public class Response implements Serializable {
    @Serial
    private static final long serialVersionUID = 123L;
    private ResponseStatus responseStatus;
    private String response = "";
    private HashSet<MusicBand> collection;
    private String type;
    private int amountOfElements;
    private LocalDateTime initializationTime;
    public Response(){
    }
    public Response(ResponseStatus responseStatus, String response){
        this.responseStatus = responseStatus;
        this.response = response.trim() + "\n";
    }


    public Response(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
    public String getType(){
        return this.type;
    }
    public int getAmountOfElements(){
        return this.amountOfElements;
    }

    public LocalDateTime getInitializationTime() {
        return initializationTime;
    }

    public ResponseStatus getStatus() {
        return responseStatus;
    }
    public String getResponse() {
        return response;
    }
    public HashSet<MusicBand> getCollection() {
        return collection;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Response response1)) return false;
        return responseStatus == response1.responseStatus && Objects.equals(response, response1.response) && Objects.equals(collection, response1.collection);
    }
    @Override
    public int hashCode() {
        return Objects.hash(responseStatus, response, collection);
    }
    @Override
    public String toString(){
        return "Response[" + responseStatus +
                (response.isEmpty()
                        ? ""
                        :',' + response) +
                (collection == null
                        ? ']'
                        : ',' + collection.toString() + ']');
    }
}
