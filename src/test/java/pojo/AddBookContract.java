package pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddBookContract {

    private String name;
    private String author;
    private String published_year;
    private String book_summary;
}
