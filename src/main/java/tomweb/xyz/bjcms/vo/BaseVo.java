package tomweb.xyz.bjcms.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)/*链式调用*/
public class BaseVo<T> {

    public String msg;
    public String code;
    public Integer page;
    public Long total;
    public Integer totalPage;
    public Integer size;

    public T data;



}
