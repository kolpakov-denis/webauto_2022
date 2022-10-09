package api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiSearchResults {
    private List<ApiSearchResultItem> results;
    private Integer offset;
    private Integer number;
    private Integer totalResults;

}