package daag.model.v1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * Created by yq on 2018/3/14.
 */
public class ResultJson {
	
    private static final ObjectMapper MAPPER = new ObjectMapper();	// 定义jackson对象

    private Integer status;	// 响应业务状态

    private String msg;		// 响应消息
    
    private Object data;	// 响应中的数据
    
    public ResultJson() {
    }

    public ResultJson(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResultJson(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    
    public ResultJson(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public static ResultJson ok(Object data) {
    	return new ResultJson(data);
    }

    public static ResultJson ok() {
        return new ResultJson(null);
    }

    public static ResultJson build(Integer status, String msg, Object data) {
    	return new ResultJson(status, msg, data);
    }

    public static ResultJson build(Integer status, String msg) {
        return new ResultJson(status, msg, null);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为ITDragonResult对象
     * 
     * @param jsonData json数据
     * @param clazz ITDragonResult中的object类型
     * @return
     */
    public static ResultJson formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, ResultJson.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     * 
     * @param json
     * @return
     */
    public static ResultJson format(String json) {
        try {
            return MAPPER.readValue(json, ResultJson.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     * 
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static ResultJson formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

}
