package tomweb.xyz.bjcms.dto;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.Data;

@Data
public class BaseQuery {

    Integer page;
    Integer size;

    public Page startPage() {
        if (page==null){
            page=1;
        }
        size=size!=null?size:20;
        com.github.pagehelper.Page<Object> pageObj = PageHelper.startPage(this.page,this.size);

        return pageObj;
    }
}
