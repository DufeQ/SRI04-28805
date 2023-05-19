package s28805.sri04jms.model.exercise;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BolidCommunication {
    private static long idIndex = 0;
    public static long nextId(){
        return idIndex++;
    }

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;
    private long id;
    private String message;

//    public BolidCommunication(String str){
//        this.createdAt = LocalDateTime.now();
//        this.id = nextId();
//        this.message = str;
//    }
}
