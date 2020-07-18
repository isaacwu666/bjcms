package tomweb.xyz.bjcms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tomweb.xyz.bjcms.dao.ConfigMapper;
import tomweb.xyz.bjcms.pojo.Config;
import tomweb.xyz.bjcms.pojo.ConfigExample;

import java.util.*;

@Service
public class ConfigService {
    @Autowired
    ConfigMapper configMapper;

    public List<Config> queryByConfigType(String... type) {
        if (type==null||type.length<1){
            return new ArrayList<>();
        }
        ConfigExample example = new ConfigExample();
        example.createCriteria().andConfigTypeIn(Arrays.asList(type)).andIsDeleteEqualTo(false);
        return configMapper.selectByExample(example);
    }

    public Map<String, Config> queryMapByConfigType(String type) {
        List<Config> configs = queryByConfigType(type);
        Map<String, Config> map = new HashMap<>();
        for (Config config : configs) {
            map.put(config.getConfigKey(), config);
        }
        return map;
    }

    public Map<String, String> queryKeyValueMapByConfigType(String... type) {
        List<Config> configs = queryByConfigType(type);
        Map<String, String> map = new HashMap<>();
        for (Config config : configs) {
            map.put(config.getConfigKey(), config.getConfigValue());
        }
        return map;
    }

}
