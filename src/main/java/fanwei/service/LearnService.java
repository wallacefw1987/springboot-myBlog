package fanwei.service;

import fanwei.pojo.LearnResource;
import fanwei.tools.Page;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/29.
 */
public interface LearnService {
    Integer add(LearnResource learnResource);
    Integer update(LearnResource learnResource);
    Integer deleteByIds(String ids);
    LearnResource queryLearnResourceById(Integer id);
    Page queryLearnResourceList(Map<String, Object> params);
}
