package tomweb.xyz.bjcms.dto;

import lombok.Data;

@Data
public class QueryArticleList {

    String  app_token ;
    String  app_id ;
    String  start_time ;
    String  end_time ;
    int  page_no ;
    int  page_size ;
    String  article_type ;
    String  collection ;
}
